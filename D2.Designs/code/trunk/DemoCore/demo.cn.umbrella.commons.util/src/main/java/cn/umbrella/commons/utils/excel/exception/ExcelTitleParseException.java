package cn.umbrella.commons.utils.excel.exception;
/**
 * ExcelTitle行解析错误
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月27日
 */
@SuppressWarnings("serial")
public class ExcelTitleParseException extends Exception {

	public ExcelTitleParseException() {
		super();
	}

	public ExcelTitleParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelTitleParseException(String message) {
		super(message);
	}

	public ExcelTitleParseException(Throwable cause) {
		super(cause);
	}
	
}
