package cn.umbrella.mss.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.umbrella.commons.bean.ResponseData;
import cn.umbrella.commons.masterworker.Master;
import cn.umbrella.commons.masterworker.Worker;
import cn.umbrella.commons.utils.base.DateStringUtility;
import cn.umbrella.conmmons.genarator.IDGenarator;
import cn.umbrella.mss.demo.dao.CommitmentBookDao;
import cn.umbrella.mss.demo.po.CommitmentBook;
import cn.umbrella.mss.service.ICommitmentBookService;

@Service
public class CommitmentBookService implements ICommitmentBookService {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	CommitmentBookDao commitmentBookDao;

	@Override
	public ResponseData importExcel(final Map<String, Object> params) {
		ResponseData rsp = new ResponseData();

		final int process = Runtime.getRuntime().availableProcessors();
		logger.info("开启线程 process = "+process);
		
		final HashMap<String,Integer> errorMsg = new HashMap<String,Integer>();
		
		Workbook workbook = (Workbook) params.get("workbook");

		final ArrayList<CommitmentBook> list = new ArrayList<CommitmentBook>();

		int sheetNum = workbook.getNumberOfSheets();
		if (sheetNum > 0) {
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();

			Row row = sheet.getRow(0);
			if (null == row) {
				rsp.setMsg("导入失败，Excel格式错误。");
				return rsp;
			}
			
			//判断前3列 数据不能为空
/*			for(int i = 1;i <= rowNum; i++){
				Row rowValue = sheet.getRow(i);
				for(int j = 0;j < row.getLastCellNum()-1;j++){
					if(null != rowValue){
						Cell cell = rowValue.getCell(j);
						if(null == cell){
							rsp.setMsg("上传的内容有空项，请修改后重新上传。");
							return rsp;
						}
					}
				}
			}*/
			
			// 根据rowNum数量划分Worker的处理任务
			Master<ImportExcelTask, CommitmentBook> master = new Master<ImportExcelTask, CommitmentBook>(
					new Worker<ImportExcelTask, CommitmentBook>() {
						@Override
						public CommitmentBook dispose(ImportExcelTask task) {
							//处理Excel数据
							CommitmentBook commitmentBook = obsCommitmentBook(params,task.getSheet(),task.getIndex(),errorMsg);
							if(null == commitmentBook){
								CommitmentBook book = new CommitmentBook();
								book.setUid(null);
								return book;
							}
							return commitmentBook; 
						}
					}, process) {
				@Override
				public CommitmentBook result() {
					//处理结果集
	                for(Map.Entry<String,CommitmentBook> item : this.getResults().entrySet()){
	                	if(null != item.getValue().getUid()){
	                		list.add(item.getValue());
	                	}
	                }
					return null;
				}
			};

			// 提交任务
			for (int index = 1; index <= rowNum; index++) {
				master.submit(new ImportExcelTask(sheet,index));
			}

			// 执行任务
			long beginTime = System.currentTimeMillis();
			master.excute();

			try {
				master.getLatch().await();
				logger.info("耗时 :"
						+ (System.currentTimeMillis() - beginTime));
				logger.info("结果：" + master.result());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			};
			master.getResults();
//			rsp.setMsg(String.format("有%d条错误数据没有导入，时间格式请以1997-01-01 00:00:00", rowNum - list.size() -1));
			String msg = String.format("导入%d条数据",list.size());
			Integer e1 = errorMsg.get("e1");
			Integer e2 = errorMsg.get("e2");
			if(null != e1){
				msg = String.format("导入%d条数据，%d条数据列格式错误未成功导入",list.size(),e1);
			}
			if(null != e2){
				msg = String.format("导入%d条数据，%d条数据字符太长未成功导入",list.size(),e2);
			}
			if(null != e1 && null != e2){
				msg = String.format("导入%d条数据，%d条数据列错误未成功导入，%d条数据字符太长未成功导入",list.size(),e1,e2);
			}
			rsp.setMsg(msg);
		}
		if(list.size() > 0){
			final int size = 3000;
			if(list.size() < size){
				commitmentBookDao.insertBatch(list);
			}else{
				//分批入库
				commitmentBookDao.insertBatch(list.subList(0,size));
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						int count = (int) Math.ceil(Math.abs((float)list.size() / (float)size));
						int rows = 2;
						for(int c = 1;c < count;c++){
							int over = rows * size;
							commitmentBookDao.insertBatch(list.subList(c*size,over > list.size() ?list.size() : over ));
							rows++;
						}
					}
				}).start();
			}
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rsp;
	}
	
	/**
	 * Description ：解析excel为实体
	 * Author ：林吉前
	 * Create Date ： 2017年6月29日
	 * History: (Version) Author Date Annotation 
	 * @param params 基础参数
	 * @param sheet excel
	 * @param index 行索引
	 * @return
	 */
	private CommitmentBook obsCommitmentBook(Map<String, Object> params,Sheet sheet,int index,HashMap<String,Integer> errorMsg){
		Row dataRow = sheet.getRow(index);
		if (dataRow == null) {
			return null;
		}
		
		final String groupPath = (String) params.get("groupPath");
		final String tableOwnerName = (String) params.get("tableOwnerName");
		final String bookName = (String) params.get("bookName");
		final String domainType = (String) params.get("domainType");
		final String domainName = (String) params.get("domainName");
		final String bookTem = (String) params.get("bookTem");
		final int userID = (int) params.get("userID");
		final String userName = (String) params.get("userName");

		String creditEntityName = null;
		String legalPerson = null;
		String businessLicense = null;
		String commTime = null;

		Cell c1 = dataRow.getCell(0);// 1、企业名称
		if (null != c1) {
			creditEntityName = parseExcel(c1);
		}

		Cell c2 = dataRow.getCell(1); // 2、法人代表
		if (null != c2) {
			legalPerson = parseExcel(c2);
		}

		Cell c3 = dataRow.getCell(2); // 3、承诺时间
		if (null != c3) {
			commTime = parseExcel(c3);
		}

		Cell c4 = dataRow.getCell(3); // 4、注册工商号
		if (null != c4) {
			parseExcel(c4);
		}

		
		if (StringUtils.isBlank(creditEntityName) || StringUtils.isBlank(commTime)) {
			int e1 = errorMsg.get("e1") == null ? 0 : errorMsg.get("e1");
			errorMsg.put("e1", ++e1);
			return null;
		}
		
		if(creditEntityName.length() > 255 || (StringUtils.isNotBlank(legalPerson) && legalPerson.length() > 255) || commTime.length() > 32 || (StringUtils.isNotBlank(businessLicense) && businessLicense.length() > 255)){
			int e2 = errorMsg.get("e2") == null ? 0 : errorMsg.get("e2");
			errorMsg.put("e2", ++e2);			
			return null;
		}

		CommitmentBook book = new CommitmentBook();
		
		book.setUid(IDGenarator.getUUID32());
		book.setCreditEntityName(creditEntityName);
		book.setLegalPerson(legalPerson);
		book.setBusinessLicense(businessLicense);
		book.setContent(bookTem);
		book.setDomainType(domainType);
		book.setDomainName(domainName);
		book.setTableOwnerName(tableOwnerName);
		book.setBookName(bookName);
		book.setTableOwnerId(groupPath);
		book.setIsPublish(2);
		book.setCreaterId(userID);
		book.setCreater(userName);
		book.setCommTime(commTime);
		
		book.setCreditEntityCode("");
		book.setUnifySocietyCode("");
		book.setBusinessLicense("");
		book.setTaxRegisterNumber("");

		return book;
	}
	
	private String parseExcel(Cell cell) {  
        String result = new String();  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型  
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
                SimpleDateFormat sdf = null;
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// 日期  
                    sdf = new SimpleDateFormat(DateStringUtility.YYYY_MM_DD);  
                }  
                Date date = cell.getDateCellValue();  
                result = sdf.format(date);  
            } else if (cell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat(DateStringUtility.YYYY_MM_DD);  
                double value = cell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil  
                        .getJavaDate(value);  
                result = sdf.format(date);  
            } else {  
                double value = cell.getNumericCellValue();  
                CellStyle style = cell.getCellStyle();  
                DecimalFormat format = new DecimalFormat();  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("#");  
                }  
                result = format.format(value);  
            }  
            break;  
        case HSSFCell.CELL_TYPE_STRING:// String类型  
            result = cell.getRichStringCellValue().toString();  
            break;  
        case HSSFCell.CELL_TYPE_BLANK:  
            result = "";  
        default:  
            result = "";  
            break;  
        }  
        return result;  
    }  
}
