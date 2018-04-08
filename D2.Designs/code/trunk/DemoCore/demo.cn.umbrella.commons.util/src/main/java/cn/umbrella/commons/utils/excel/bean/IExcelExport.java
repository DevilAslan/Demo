package cn.umbrella.commons.utils.excel.bean;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import cn.umbrella.commons.utils.excel.bean.impl.ExcelStyle;
/**
 * 导出接口规范
 * @author zhang.xiaolei
 * @createDate 2015年5月29日
 */
public interface IExcelExport {
	<T> void execute(OutputStream os, List<T> data ,Class<T> clazz) throws Exception;
	/**
	 * 为了保证顺序，可以使用 LinkedHashMap 
	 * @param titleAndField
	 */
	void setCustomTitleRow(Map<String,String> titleAndField);
	ExcelStyle getTitleStyel();
	void setTitleStyel(ExcelStyle titleStyel) ;

	ExcelStyle getDataStyel() ;
	void setDataStyel(ExcelStyle dataStyel) ;
}
