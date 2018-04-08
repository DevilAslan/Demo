package cn.umbrella.commons.utils.excel.bean.impl;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.umbrella.commons.utils.excel.annotation.AnnExcelColumn;
import cn.umbrella.commons.utils.excel.annotation.AnnExcelSheet;
import cn.umbrella.commons.utils.excel.bean.IExcelExport;
import cn.umbrella.commons.utils.excel.enums.ExcelNullEnum;
import cn.umbrella.commons.utils.excel.enums.IExcelCommonEnum;
import cn.umbrella.commons.utils.excel.exception.ExcelEnumTypeException;
/**
 * excel导出
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月27日
 */
public class SimpleExcelExport implements IExcelExport{
	/**
	 * title以及对应的字段描述信息
	 */
/*	private Map<String,PropertyDescriptor> fieldInfo;
	private Map<String,AnnExcelColumn> fieldAnnInfo;
	private ArrayList<AnnExcelColumn> titleOrder;*/
	 //处理对象的所有的注解相关的信息
	private ExcelAnnParse eap;
	//标题样式
	private ExcelStyle titleStyel;
	//正文样式 
	private ExcelStyle dataStyel;
	
	public SimpleExcelExport() {
		initStyle();
	}
	
	/**
	 * 初始化导出时候的默认样式
	 */
	@SuppressWarnings("static-access")
	private void initStyle(){
		titleStyel=new ExcelStyle();
		titleStyel.setFontSize((short)11);
		titleStyel.setBold(true);
		titleStyel.setAlign(ExcelStyle.style.ALIGN_CENTER);
		titleStyel.setvAlign(ExcelStyle.style.VERTICAL_CENTER);
		dataStyel = new ExcelStyle();
	}
	/**
	 * 工作薄解析
	 * 
	 * @param is
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> void execute(OutputStream os, List<T> data ,Class<T> clazz) throws Exception  {
		/**
		 * 1.获取实例的注解信息
		 * 2.获取实例字段注解信息
		 * 3.准备导出的字段
		 * 4.创建工作薄
		 * 5.创建工作表
		 * 6.输出标题行
		 * 7.输出数据
		 */
		
		//@1 @2 @3
		this.eap = ExcelAnnParse.parse(clazz);
		//AnnExcelSheet sheetInfo = parseAnnotationExcelSheet(clazz);
		//根据注解的名字来获取需要解析哪些数据@2 @3
		//parseAnnotationExcelColumn(clazz);
		
		AnnExcelSheet sheetInfo = eap.getSheetInfo();
		ArrayList<AnnExcelColumn> titleOrder = eap.getTitleOrder();
		//@4
		Workbook workbook =new XSSFWorkbook();//HSSFWorkbook 97-2003 版本的 , XSSFWorkbook 2007以上版本的
		//@5
		Sheet sheet =workbook.createSheet("".equals(sheetInfo.name())?"工作表1":sheetInfo.name());
		//样式
		CellStyle titleStyle = this.getTitleStyel().getPOIFont(workbook);
		CellStyle dataStyle = this.getDataStyel().getPOIFont(workbook);
		
		//@6
		Row titleRow = sheet.createRow(sheetInfo.titleNum());
		for (int i = 0 , size = titleOrder.size(); i < size; i++) {//只会调用一次 .size() 方法，据说性能会好点
			Cell titleCell =titleRow.createCell(i);
			titleCell.setCellValue(titleOrder.get(i).title());
			titleCell.setCellStyle(titleStyle);
		}
		//对数据进行处理 @7
		//不适用for循环，因为里面需要计算一个当前行
		int i=sheetInfo.titleNum()+1;
		for (T t : data) {
			Row row = sheet.createRow(i++);//数据行
			for (int j = 0 , size = titleOrder.size(); j < size; j++) {//创建数据列
				Cell cell =row.createCell(j);
				
				String title = titleOrder.get(j).title();
				cell.setCellStyle(dataStyle);
				cellSetValue(cell,t,workbook,title);
			}
		}
		i=0;
		
		for (int j = 0; j < titleOrder.size(); j++) {
			AnnExcelColumn aec =titleOrder.get(j);
			if(aec.width() == -1){//自动调整宽度
				sheet.autoSizeColumn(j);
			}else if(aec.width() >0){//固定宽度
				sheet.setColumnWidth(j, aec.width()*36);
			}
		}
		workbook.write(os);
		workbook.close();
	}
	
	@SuppressWarnings("rawtypes")
	private void cellSetValue(Cell cell,Object t ,Workbook workbook,String title) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ExcelEnumTypeException{
		//获取对象的值
		Method method = eap.getFieldInfo().get(title).getReadMethod();
		AnnExcelColumn fieldAnn = eap.getFieldAnnInfo().get(title);
		Object value = method.invoke(t);
		
		if(value == null){
			cell.setCellType(Cell.CELL_TYPE_BLANK);
			return ;
		}
		//对类型处理然后赋值
		Class valueClazz =value.getClass();
		if(valueClazz == Boolean.class){//布尔值
			cell.setCellValue(Boolean.valueOf(value.toString()));
		}else if(valueClazz == Integer.class ||
				valueClazz == Long.class ||
				valueClazz == Double.class ||
				valueClazz == BigDecimal.class ||
				valueClazz == BigInteger.class){//数值型
			if(fieldAnn.enumType()!=ExcelNullEnum.class){
				cell.setCellValue(enumProcess(value,fieldAnn.enumType()));
			}else{
				cell.setCellValue(Double.valueOf(value.toString()));
				if(!"".equals(fieldAnn.format())){//格式化字符串
					CellStyle cs   = workbook.createCellStyle();
					short format =workbook.createDataFormat().getFormat(fieldAnn.format());
					cs.setDataFormat(format);
					cell.setCellStyle(cs);
				}	
			}
		}else if(valueClazz == Date.class){
			cell.setCellValue((Date)value);
			String formatPatten ="yyyy-MM-dd HH:mm:ss";
			if(!"".equals(fieldAnn.format())){
				formatPatten = fieldAnn.format();
			}
			short format = workbook.createDataFormat().getFormat(formatPatten);
			CellStyle cs   = workbook.createCellStyle();
			cs.setDataFormat(format);
			cell.setCellStyle(cs);
		}else if(valueClazz == Calendar.class){
			cell.setCellValue((Calendar)value);
		}else if(valueClazz == String.class){
			cell.setCellValue((String)value);
		}else{
			cell.setCellValue(value.toString());
		}
	}
	@SuppressWarnings("rawtypes")
	private  String  enumProcess(Object source,Class enumtype) throws ExcelEnumTypeException{
		if(!enumtype.isEnum()){
			throw new ExcelEnumTypeException(enumtype.getName()+" 不是枚举对象");
		}
		if(ClassUtil.isImplement(enumtype, IExcelCommonEnum.class)){
			//遍历枚举值，找到对应的 value
			for (Object o : enumtype.getEnumConstants()) {
				IExcelCommonEnum ce = (IExcelCommonEnum)o;
				if(source.equals(ce.getValue())){
					return ce.getName();
				}
			}
			throw new ExcelEnumTypeException(enumtype.getName()+" 没有找到枚举值："+source);
		}
		if(enumtype!=ExcelNullEnum.class){
			throw new ExcelEnumTypeException(enumtype.getName()+" 没有实现 ICommonEnum 接口");
		}
		return null;
	}

	public ExcelStyle getTitleStyel() {
		return titleStyel;
	}

	public void setTitleStyel(ExcelStyle titleStyel) {
		this.titleStyel = titleStyel;
	}

	public ExcelStyle getDataStyel() {
		return dataStyel;
	}

	public void setDataStyel(ExcelStyle dataStyel) {
		this.dataStyel = dataStyel;
	}
	@Override
	public void setCustomTitleRow(Map<String,String> titleAndField) {
		//抛弃否？
	}
}
