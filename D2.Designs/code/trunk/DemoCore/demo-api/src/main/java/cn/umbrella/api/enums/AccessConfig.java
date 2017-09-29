package cn.umbrella.api.enums;

import java.util.Map;

/**
 * 
 * @author Aslan
 * 
 */
public class AccessConfig {
	
	/**
	 * 返回值分离
	 */
	public static final String RESULT_SUCCESS_CODE = "101";
	public static final String RESULT_SUCCESS_MSG = "SUCCESS";

	public static final String RESULT_INVALID_CODE = "401";
	public static final String RESULT_INVALID_MSG = "INVALID";

	public static final String RESULT_INVALID_FORBIDDEN_CODE = "403";
	public static final String RESULT_INVALID_FORBIDDEN_MSG = "FORBIDDEN";

	public static final String RESULT_INVALID_NOT_FOUND_CODE = "404";
	public static final String RESULT_INVALID_NOT_FOUND_MSG = "NOT FOUND";

	public static final String RESULT_INVALID_OVERDUE_CODE = "405";
	public static final String RESULT_INVALID_OVERDUE_MSG = "OVERDUE";

	public static final String RESULT_INVALID_EXIST_CODE = "406";
	public static final String RESULT_INVALID_EXIST_MSG = "EXIST";

	public static final String RESULT_INVALID_SERVICE_CODE = "407";
	public static final String RESULT_INVALID_SERVICE_MSG = "OUT OF SERVICE";

	public static final String RESULT_ERROR_CODE = "801";
	public static final String RESULT_ERROR_MSG = "PARAMETER ERROR";

	public static final String RESULT_ERROR_CHECK_CODE = "802";
	public static final String RESULT_ERROR_CHECK_MSG = "CHECK ERROR";

	public static final String RESULT_FAIL_CODE = "901";
	public static final String RESULT_FAIL_MSG = "FAIL";
	
	public static Map<String, String> DATA_CODE_OPERABLE_ROLE = null;

}
