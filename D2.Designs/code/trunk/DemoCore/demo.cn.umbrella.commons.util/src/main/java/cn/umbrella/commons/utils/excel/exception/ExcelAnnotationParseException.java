package cn.umbrella.commons.utils.excel.exception;
/**
 * ExcelTitle行解析错误
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月27日
 */
@SuppressWarnings("serial")
public class ExcelAnnotationParseException extends Exception {

	public ExcelAnnotationParseException() {
		super();
	}

	public ExcelAnnotationParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelAnnotationParseException(String message) {
		super(message);
	}

	public ExcelAnnotationParseException(Throwable cause) {
		super(cause);
	}
	
}
