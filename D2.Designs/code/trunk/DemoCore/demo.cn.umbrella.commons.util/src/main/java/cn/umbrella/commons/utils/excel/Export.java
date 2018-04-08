package cn.umbrella.commons.utils.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import cn.umbrella.commons.utils.base.StringTool;
import cn.umbrella.commons.utils.validator.IdcardValidator;

public class Export {
	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
		final String userAgent = request.getHeader("USER-AGENT");
		try {
			String finalFileName = null;
			if (StringUtils.countOccurrencesOf(userAgent, "MSIE") > 0) {// IE浏览器
																		// MSIE
				finalFileName = URLEncoder.encode(fileName, "UTF8");
			} else if (StringUtils.countOccurrencesOf(userAgent, "Mozilla") > 0) {// google,火狐浏览器
																					// Mozilla
				finalFileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
			}
			response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");// 这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * 导出到excel 
	 *
	 * @Title: exportExcel 
	 * @param list
	 * @param fieldName
	 * @param columnIt
	 * @param sheetName
	 * @param sheetSize
	 * @param output
	 * @return boolean
	 */
	@SuppressWarnings({ "rawtypes" })
	public static boolean exportExcel(List list, String[] fieldName, Object[] columnIt, String sheetName, Integer sheetSize, OutputStream output) {
		// 产生工作薄对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (sheetSize >= 65000) {
			sheetSize = 65000;
		}
		int sum = 0;
		if(sheetSize == 0){
			sum = 1;
		}else{
			sum = list.size() / sheetSize;
			if(list.size() % sheetSize != 0){
				sum = sum + 1;
			}
		}
		double sheetNo = sheetSize == 0 ? 1 : Math.ceil(sum);// 计算需要几个sheet
		for (int index = 0; index < sheetNo; index++) {
			// 产生工作表对象
			HSSFSheet sheet = workbook.createSheet();
			// 设置工作表的名称.
			workbook.setSheetName(index, sheetName + (index + 1));
			// 产生一行
			HSSFRow row = sheet.createRow(0);
			// 产生单元格
			HSSFCell cell;

			CellStyle cellStyle = getTitleStyle(workbook);
			// 写入各个字段的名称
			for (int i = 0; i < fieldName.length; i++) {
				// 创建第一行各个字段名称的单元格
				cell = row.createCell(i);
				// 设置单元格内容为字符串型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(cellStyle);
				// 给单元格内容赋值
				cell.setCellValue(fieldName[i]);
			}

			int startNo = index * sheetSize;
			int endNo = Math.min(startNo + sheetSize, list.size());
			// 写入各条记录,每条记录对应excel表中的一行
			for (int i = startNo; i < endNo; i++) {
				row = sheet.createRow(i + 1 - startNo);
				HashMap map = (HashMap) list.get(i);
				for (int j = 0; j < columnIt.length; j++) {
					cell = row.createCell(j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					Object value = map.get(columnIt[j]);
					if (value != null) {
						cell.setCellValue(map.get(columnIt[j]).toString());
					} else
						cell.setCellValue("");
				}
			}
		}
		try {
			workbook.write(output);
			output.flush();
			output.close();
			workbook.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 导出到excel 到本地文件夹
	 *
	 * @Title: exportExcel 
	 * @param list
	 * @param fieldName
	 * @param columnIt
	 * @param sheetName
	 * @param sheetSize
	 * @param output
	 * @return boolean
	 */
	@SuppressWarnings({ "rawtypes" })
	public static boolean exportExcelLocal(List list, String[] fieldName, Object[] columnIt, String sheetName, Integer sheetSize,String tableNameCn,String excelDownPath) {
		// 产生工作薄对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (sheetSize >= 65000) {
			sheetSize = 65000;
		}
		int sum = 0;
		if(sheetSize == 0){
			sum = 1;
		}else{
			sum = list.size() / sheetSize;
			if(list.size() % sheetSize != 0){
				sum = sum + 1;
			}
		}
		double sheetNo = sheetSize == 0 ? 1 : Math.ceil(sum);// 计算需要几个sheet
		for (int index = 0; index < sheetNo; index++) {
			// 产生工作表对象
			HSSFSheet sheet = workbook.createSheet();
			// 设置工作表的名称.
			workbook.setSheetName(index, sheetName + (index + 1));
			// 产生一行
			HSSFRow row = sheet.createRow(0);
			// 产生单元格
			HSSFCell cell;

			CellStyle cellStyle = getTitleStyle(workbook);
			// 写入各个字段的名称
			for (int i = 0; i < fieldName.length; i++) {
				// 创建第一行各个字段名称的单元格
				cell = row.createCell(i);
				// 设置单元格内容为字符串型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(cellStyle);
				// 给单元格内容赋值
				cell.setCellValue(fieldName[i]);
			}

			int startNo = index * sheetSize;
			int endNo = Math.min(startNo + sheetSize, list.size());
			// 写入各条记录,每条记录对应excel表中的一行
			for (int i = startNo; i < endNo; i++) {
				row = sheet.createRow(i + 1 - startNo);
				HashMap map = (HashMap) list.get(i);
				for (int j = 0; j < columnIt.length; j++) {
					cell = row.createCell(j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					Object value = map.get(columnIt[j]);
					if (value != null) {
						String valueStr = value.toString();
						if (IdcardValidator.isValidatedAllIdcard(valueStr)) {
							valueStr = StringTool.partHidden(valueStr, 6, 4, "*", 8);
						}
						cell.setCellValue(valueStr);
					} else
						cell.setCellValue("");
				}
			}
		}
		
		 try {  
			 File file = new File(excelDownPath);  
	            if(!file.exists()){ 
					 file.mkdirs(); 
					}
             FileOutputStream fos = new FileOutputStream(excelDownPath + tableNameCn); 
             workbook.write(fos); 
             fos.close();  
             return true;
         } catch (Exception e) {  
             e.printStackTrace();  
             return false;
         }
	}

	/**
	 * 数据考核导出
	 * @param list
	 * @param fieldName
	 * @param columnIt
	 * @param sheetName
	 * @param sheetSize
	 * @param output
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static boolean exportExcel2(List list, String[] fieldName, Object[] columnIt, String sheetName, Integer sheetSize, OutputStream output) {
		// 产生工作薄对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (sheetSize >= 65000) {
			sheetSize = 65000;
		}
		int sum = 0;
		if(sheetSize == 0){
			sum = 1;
		}else{
			sum = list.size() / sheetSize;
			if(list.size() % sheetSize != 0){
				sum = sum + 1;
			}
		}
		double sheetNo = sheetSize == 0 ? 1 : Math.ceil(sum);// 计算需要几个sheet
		for (int index = 0; index < sheetNo; index++) {
			// 产生工作表对象
			HSSFSheet sheet = workbook.createSheet();
			// 设置工作表的名称.
			workbook.setSheetName(index, sheetName + (index + 1));
			// 产生一行
			HSSFRow row = sheet.createRow(0);
			// 产生单元格
			HSSFCell cell;
			CellStyle cellStyle = getTitleStyle(workbook);
			HSSFCellStyle style1 = workbook.createCellStyle();//数据样式设置  
			HSSFFont font1 = workbook.createFont();
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中  
			style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中  
			style1.setFont(font1);
			// 写入各个字段的名称
			for (int i = 0; i < fieldName.length; i++) {
				// 创建第一行各个字段名称的单元格
				cell = row.createCell(i);
				// 设置单元格内容为字符串型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(cellStyle);
				// 给单元格内容赋值
				cell.setCellValue(fieldName[i]);
			}

			int startNo = index * sheetSize;
			int endNo = Math.min(startNo + sheetSize, list.size());
			// 写入各条记录,每条记录对应excel表中的一行
			for (int i = startNo; i < endNo; i++) {
				row = sheet.createRow(i + 1 - startNo);
				HashMap map = (HashMap) list.get(i);
				for (int j = 0; j < columnIt.length; j++) {
					cell = row.createCell(j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					Object value = map.get(columnIt[j]);
					if (value != null) {
						String parameter = map.get(columnIt[j]).toString();
						boolean bool = isFloat(parameter);
						if(bool){
							BigDecimal bd = new BigDecimal(Double.parseDouble(String.valueOf(parameter)));
							Double d = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
							float dataCore = (float)(d+0f);
							cell.setCellValue(dataCore);
						}else{
							cell.setCellValue(parameter);
						}
					} else
						cell.setCellValue("");
					cell.setCellStyle(style1);
				}
				
			}
			//合并单元格 & 计算总分 & 等级
			int val = 1;
			float score = 0;
			float endScore = 0;
			float goodRows = 0;
			float endGoodRows = 0;
			boolean endBool = false;
			for(int k = 1;k<=sheet.getLastRowNum();k++){
				HSSFRow rows = sheet.getRow(k);
				HSSFCell cells = rows.getCell(0);
				String now = cells.toString();
				if(k>1){
					HSSFRow jRows = sheet.getRow(k-1);
					HSSFCell jCells = jRows.getCell(0);
					HSSFCell jScore = jRows.getCell(2);//数据分数
					HSSFCell fScore = jRows.getCell(3);//方式分数
					HSSFCell goodRow = jRows.getCell(4);//报送条数
					float score1 = Float.parseFloat(jScore.toString());
					float score0 = Float.parseFloat(fScore.toString());
					float scores = Float.parseFloat(goodRow.toString());
					score += score1 + score0;
					goodRows += scores;
					String retreat = jCells.toString();
					boolean bool = true;
					if(now.equals(retreat)){
						if(k<sheet.getLastRowNum()){
							bool = false;
						}else{
							HSSFCell score2 = rows.getCell(2);//数据分数
							HSSFCell score6 = rows.getCell(3);//方式分数
							HSSFCell goodRow1 = rows.getCell(4);//报送条数
							float score3 = Float.parseFloat(score2.toString());
							float score5 = Float.parseFloat(score6.toString());
							float scores1 = Float.parseFloat(goodRow1.toString());
							goodRows += scores1;
							score += score3 + score5;
							k++;
						}
					}else if(!now.equals(retreat) && k==sheet.getLastRowNum()){
						HSSFCell score2 = rows.getCell(2);//分数
						HSSFCell score6 = rows.getCell(3);//方式分数
						HSSFCell goodRow2 = rows.getCell(4);//报送条数
						float score3 = Float.parseFloat(score2.toString());
						float score5 = Float.parseFloat(score6.toString());
						float scores2 = Float.parseFloat(goodRow2.toString());
						endGoodRows += scores2;
						endScore += score3 + score5;
						endBool = true;
					}
					if(bool){
						HSSFRow dataRows = sheet.getRow(val);
						HSSFCell scoreCell = dataRows.getCell(9);
						BigDecimal bd = new BigDecimal(Double.parseDouble(String.valueOf(score)));
						Double d = bd.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();
						float dataCore = (float)(d+0f);
						if(dataCore > 100){
							dataCore = 100f;
						}
						scoreCell.setCellValue(dataCore);
						HSSFCell levelCell = dataRows.getCell(10);
						String level = isLevel(score,goodRows);
						levelCell.setCellValue(level);
						CellRangeAddress range = new CellRangeAddress(val,k-1,0,0);
						CellRangeAddress range9 = new CellRangeAddress(val,k-1,9,9);
						CellRangeAddress range10 = new CellRangeAddress(val,k-1,10,10);
						sheet.addMergedRegion(range);
						sheet.addMergedRegion(range9);
						sheet.addMergedRegion(range10);
						val = k;
						score = 0;
						goodRows = 0;
						if(endBool){
							HSSFRow endRows = sheet.getRow(sheet.getLastRowNum());
							HSSFCell endScoreCell = endRows.getCell(9);
							BigDecimal bd2 = new BigDecimal(Double.parseDouble(String.valueOf(endScore)));
							Double d2 = bd2.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();
							float endDataCore = (float)(d2+0f);
							BigDecimal bd3 = new BigDecimal(Double.parseDouble(String.valueOf(endGoodRows)));
							Double d3 = bd3.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();
							float endDataGoodRows = (float)(d3+0f);
							endScoreCell.setCellValue(endDataCore);
							HSSFCell endLevelCell = endRows.getCell(10);
							String endLevel = isLevel(endDataCore,endDataGoodRows);
							endLevelCell.setCellValue(endLevel);
						}
						
					}
				}
			}
		}
		try {
			workbook.write(output);
			output.flush();
			output.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 标题单元格样式 
	 *
	 * @Title: getTitleStyle 
	 * @param workbook
	 * @return CellStyle
	 */
	public static CellStyle getTitleStyle(Workbook workbook) {
		CellStyle titleStyel = workbook.createCellStyle();
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
		titleStyel.setFont(font);
		return titleStyel;
	}
	
	/**
	 * 判断值是否是数字
	 * @param value
	 * @return
	 */
	 public static boolean isFloat(String value) {
		  try {
			  Float.valueOf(value);
			  return true;
		  } catch (NumberFormatException e) {
			  return false;
		  }
	 }
	 
	 /**
	  * 根据分数评定等级
	  * @param score
	  * @return
	  */
	 public static String isLevel(float score,float goodRows){
		 String level = "不合格";
			if(score > 0 && goodRows == 0){
				level = "零报送";
			}else if(score >= 85){
				level = "优秀";
			}else if(score >= 70){
				level = "良好";
			}else if(score >= 60){
				level = "合格";
			}
			return level;
	 }

}
