package cn.umbrella.commons.config;

/**
 * 
 * @author Aslan
 * 
 */
public class StateConfig {

	// Begin common config
	public static long ONE_GB = 1024;
	public static long ONE_MB = 1024;
	public static long ONE_KB = 1024;
	// --------------------------------------------------------------------------------

	public static final String STATUS_CODE = "Data Status Code";
	public static final String STATUS_MSG = "数据状态码";

	public static final String DATA_INIT_CODE = "101";
	public static final String DATA_INIT_MSG = "INIT SUCCESS";

	public static final String DATA_READY_CODE = "102";
	public static final String DATA_READY_MSG = "BE READY";

	public static final String DATA_NOT_READY_CODE = "103";
	public static final String DATA_NOT_READY_MSG = "NOT READY";

	public static final String DATA_DELETE_CODE = "201";
	public static final String DATA_DELETE_MSG = "DELETE";

	public static final String DATA_DELETE_OUT_CODE = "202";
	public static final String DATA_DELETE_OUT_MSG = "OUT";

	public static final String DATA_EXHIBITION_CODE = "301";
	public static final String DATA_EXHIBITION_MSG = "EXHIBITION";

	public static final String DATA_DELIVER_CODE = "701";
	public static final String DATA_DELIVER_MSG = "DELIVER";

	public static final String DATA_DELIVER_REJECT_CODE = "702";
	public static final String DATA_DELIVER_REJECT_MSG = "REJECT";

	public static final String DATA_DELIVER_REJECTED_CODE = "703";
	public static final String DATA_DELIVER_REJECTED_MSG = "REJECTED";

	public static final String DATA_CLOSURE_CODE = "801";
	public static final String DATA_CLOSURE_MSG = "CLOSURE";

	public static final String DATA_CLOSURE_CANCEL_CODE = "802";
	public static final String DATA_CLOSURE_CANCEL_MSG = "CANCEL";

	public static final String DATA_CLOSURE_CANCELLED_CODE = "803";
	public static final String DATA_CLOSURE_CANCELLED_MSG = "CANCELLED";

	/**
	 * 可操作数据码
	 */
	public static final String DATA_CODE_OPERABLE = DATA_INIT_CODE + ","
			+ DATA_READY_CODE;
	/**
	 * 可查看数据码
	 */
	public static final String DATA_CODE_QUERY = DATA_INIT_CODE + ","
			+ DATA_EXHIBITION_CODE;

	/**
	 * 数据初始化
	 */
	public static final String DATA_CODE_INIT = DATA_INIT_CODE + ","
			+ DATA_READY_CODE;
	/**
	 * 数据关闭
	 */
	public static final String DATA_CODE_CLOSURE = DATA_CLOSURE_CODE;

}
