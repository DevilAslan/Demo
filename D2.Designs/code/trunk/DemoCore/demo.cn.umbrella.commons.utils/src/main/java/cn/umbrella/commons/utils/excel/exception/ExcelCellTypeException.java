package cn.umbrella.commons.utils.excel.exception;
/**
 * ExcelTitle行解析错误
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月27日
 */
@SuppressWarnings("serial")
public class ExcelCellTypeException extends Exception {

	public ExcelCellTypeException() {
		super();
	}

	public ExcelCellTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelCellTypeException(String message) {
		super(message);
	}

	public ExcelCellTypeException(Throwable cause) {
		super(cause);
	}
	
}
