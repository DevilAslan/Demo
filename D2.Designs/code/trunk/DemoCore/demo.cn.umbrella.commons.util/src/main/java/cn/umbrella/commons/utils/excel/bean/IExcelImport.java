package cn.umbrella.commons.utils.excel.bean;

import java.io.InputStream;
import java.util.List;

public interface IExcelImport {
	<T> List<T> execute(InputStream is, Class<T> clazz) throws Exception ;
}
