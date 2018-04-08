package cn.umbrella.commons.utils.excel.bean.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.umbrella.commons.utils.excel.annotation.AnnExcelColumn;
import cn.umbrella.commons.utils.excel.annotation.AnnExcelSheet;
import cn.umbrella.commons.utils.excel.enums.ExcelColumnType;
import cn.umbrella.commons.utils.excel.exception.ExcelAnnotationParseException;

/**
 *  对注解进行解析， 拿到一些相关的数据;
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月29日
 */
public class ExcelAnnParse {
	@SuppressWarnings("rawtypes")
	private static Map<Class,ExcelAnnParse> excelAnnInfo = new LinkedHashMap<Class, ExcelAnnParse>();
	private Map<String,PropertyDescriptor> fieldInfo;
	private Map<String,AnnExcelColumn> fieldAnnInfo;
	private AnnExcelSheet sheetInfo; 
	private ArrayList<AnnExcelColumn> titleOrder; 
	
	private ExcelAnnParse(){}
	
	/**
	 * 对类注解的解析
	 * 
	 * @param clazz
	 * @return
	 * @throws ExcelAnnotationParseException
	 * @throws IntrospectionException
	 */
	@SuppressWarnings("rawtypes")
	public synchronized static <T> ExcelAnnParse parse(Class<T> clazz) throws ExcelAnnotationParseException, IntrospectionException{
		ExcelAnnParse eap = excelAnnInfo.get(clazz);
		if(eap != null){
			if(excelAnnInfo.size()>1000){//超过一定大小 就删除一些对象
				Iterator<Class> it =excelAnnInfo.keySet().iterator();
				for(int i = 0 ; i< 500 ; i++){
					excelAnnInfo.remove(it.next());
				}
			}
			excelAnnInfo.remove(clazz);//如果找到就放到链表尾部
		}else{
			eap = new ExcelAnnParse();
			eap.init(clazz);
		}
		excelAnnInfo.put(clazz, eap);//放置在最后
		return eap;
	}
	private  <T> void init(Class<T> clazz) throws ExcelAnnotationParseException, IntrospectionException{
		this.sheetInfo = clazz.getAnnotation(AnnExcelSheet.class);
		if(sheetInfo == null){
			throw new ExcelAnnotationParseException(clazz.getName()+"没有声明@AnnExcelSheet注解，不能进行解析");
		}
		fieldInfo = new HashMap<String, PropertyDescriptor>();
		fieldAnnInfo = new HashMap<String, AnnExcelColumn>();
		titleOrder = new ArrayList<AnnExcelColumn>();//有序的， 导出时候字段的顺序
		Field[] fields = clazz.getDeclaredFields();//所有私有属性
		for (Field field : fields) {
			AnnExcelColumn aec =field.getAnnotation(AnnExcelColumn.class);//获取注解信息
			if(aec == null){//忽略掉没有注解的字段
				continue;
			}
			fieldInfo.put(aec.title(), new PropertyDescriptor(field.getName(), clazz));//title对应的字段相关信息，用于获取get OR set 方法
			fieldAnnInfo.put(aec.title(), aec);
			if(aec.type() != ExcelColumnType.IMP){
				titleOrder.add(aec);
			}
		}
		 Collections.sort(titleOrder, new Comparator<AnnExcelColumn>() {
			 @Override
			public int compare(AnnExcelColumn o1, AnnExcelColumn o2) {
				 return o1.order()>o2.order()?1:0;
			}
		 });
		
	/*	titleOrder.sort(new Comparator<AnnExcelColumn>() {//进行字段排序 jdk8支持的写法
			public int compare(AnnExcelColumn o1, AnnExcelColumn o2) {
				return o1.order()>o2.order()?-1:0;
			};
		});*/
		
	}
	public Map<String, PropertyDescriptor> getFieldInfo() {
		return fieldInfo;
	}
	
	public Map<String, AnnExcelColumn> getFieldAnnInfo() {
		return fieldAnnInfo;
	}
	
	public ArrayList<AnnExcelColumn> getTitleOrder() {
		return titleOrder;
	}

	public AnnExcelSheet getSheetInfo() {
		return sheetInfo;
	}

}
