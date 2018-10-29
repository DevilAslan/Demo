package cn.umbrella.commons.util.web;

import java.util.List;

import cn.umbrella.commons.config.Constant;
import cn.umbrella.commons.config.CryptConfig;
import cn.umbrella.commons.enums.AccessCode;
import cn.umbrella.commons.model.Page;
import cn.umbrella.commons.util.crypt.DESede;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;
//import net.sf.json.util.JavaIdentifierTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AccessUtil {
	/**
	 * 有问题
	 * 
	 * @param errorMsg
	 * @return
	 */
	public static String getInvalidParaJson(String errorMsg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, AccessCode.INVALID.getKey());
		response.put(Constant.ACCESS_MSG, AccessCode.INVALID.getValue());
		return response.toString();
	}

//	public static JsonConfig getDefaultConfig(Class<?> objClass) {
//		JsonConfig jsconfig = new JsonConfig();
//		jsconfig.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {
//			@Override
//			public String transformToJavaIdentifier(String arg0) {
//				if (arg0.equals(Constant.ACCESS_RESULT)) {
//					return arg0;
//				}
//				if (arg0.equals(Constant.ACCESS_CODE)) {
//					return arg0;
//				}
//				if (arg0.equals(Constant.ACCESS_MSG)) {
//					return arg0;
//				}
//				char[] chars = arg0.toCharArray();
//				chars[0] = Character.toLowerCase(chars[0]);
//				return new String(chars);
//			}
//		});
//		jsconfig.setRootClass(objClass);
//		return jsconfig;
//	}

	// Begin Get Access Json Tool

	public static JSONObject getPageObj(Page page) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = (JSONArray) JSON.toJSON(page);
//		JSONArray.fromObject(page.getRecordList());
		response.put(Constant.RECORD_LIST, jsonArray);
		response.put(Constant.CUR_PAGE_NUM, page.getCurrentPage());
		response.put(Constant.PAGE_SIZE, page.getPageSize());
		response.put(Constant.PAGE_COUNT, page.getPageCount());
		response.put(Constant.TOTAL_COUNT, page.getTotalCount());
		return response;
	}

	public static JSONObject getListObj(List<?> list) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
		response.put(Constant.RECORD_LIST, jsonArray);
		response.put(Constant.TOTAL_COUNT, list.size());
		return response;
	}

	public static String getPageJson(Page page) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = (JSONArray) JSON.toJSON(page.getRecordList());
		response.put(Constant.RECORD_LIST, jsonArray);
		response.put(Constant.CUR_PAGE_NUM, page.getCurrentPage());
		response.put(Constant.PAGE_SIZE, page.getPageSize());
		response.put(Constant.PAGE_COUNT, page.getPageCount());
		response.put(Constant.TOTAL_COUNT, page.getTotalCount());
		return response.toString();
	}

	public static String getListJson(List<?> list) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
		response.put(Constant.RECORD_LIST, jsonArray);
		response.put(Constant.TOTAL_COUNT, list.size());
		return response.toString();
	}

	public static String getAccessJson(String result, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, result);
		response.put(Constant.ACCESS_CODE, code);
		response.put(Constant.ACCESS_MSG, msg);
		return response.toString();
	}

	public static JSONObject getAccessObj(String result, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, result);
		response.put(Constant.ACCESS_CODE, code);
		response.put(Constant.ACCESS_MSG, msg);
		return response;
	}

	public static String getAccessJson(Page page, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, getPageJson(page));
		response.put(Constant.ACCESS_CODE, code);
		response.put(Constant.ACCESS_MSG, msg);
		return response.toString();
	}

	public static String getAccessJson(List<?> list, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, getListJson(list));
		response.put(Constant.ACCESS_CODE, code);
		response.put(Constant.ACCESS_MSG, msg);
		return response.toString();
	}

	public static String getAccessJson(Object obj, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, JSON.toJSON(obj));
		response.put(Constant.ACCESS_CODE, code);
		response.put(Constant.ACCESS_MSG, msg);
		return response.toString();
	}

	/**
	 * flag doing
	 * 
	 * @param obj
	 * @param code
	 * @param msg
	 * @return
	 */
	public static String getAccessJsonEnTest(Object obj, String code,
			String msg, String keyCode) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, JSON.toJSON(obj));
		response.put(Constant.ACCESS_CODE, code);
		response.put(Constant.ACCESS_MSG, msg);
		String res = EnTest(response.toString(), keyCode);
		boolean flag = false;
		flag = ParamUtil.DeTest(response.toString(), res, keyCode);
		System.out.println(flag);
		return res;
	}

	static String EnTest(String content, String keyCode) {
		String encryptText = content + "";
		String enContent = null;
		if (CryptConfig.DESEDE.equals(keyCode)) {
			String encryptKey = "2012PinganVitality075522628888ForShenZhenBelter075561869839";// 分配得到
			enContent = DESede.encryptMode(encryptKey, encryptText);
		}
		return enContent;
	}
	// End Get Access Json Tool
}
