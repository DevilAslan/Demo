package cn.umbrella.commons.utils.excel.exception;
/**
 * ExcelTitle行解析错误
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月27日
 */
@SuppressWarnings("serial")
public class ExcelEnumTypeException extends Exception {

	public ExcelEnumTypeException() {
		super();
	}

	public ExcelEnumTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelEnumTypeException(String message) {
		super(message);
	}

	public ExcelEnumTypeException(Throwable cause) {
		super(cause);
	}
	
}
