package cn.umbrella.conmmon.util.web;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;

import cn.umbrella.commons.config.AccessConfig;
import cn.umbrella.commons.config.CryptConfig;
import cn.umbrella.conmmon.model.Page;
import cn.umbrella.conmmon.util.crypt.DESede;

public class AccessUtil {
	/**
	 * 有问题
	 * 
	 * @param errorMsg
	 * @return
	 */
	public static String getInvalidParaJson(String errorMsg) {
		JSONObject response = new JSONObject();
		response.put("ACCESS_RESULT", AccessConfig.RESULT_INVALID_CODE);
		response.put("ACCESS_MSG", AccessConfig.RESULT_INVALID_MSG);
		return response.toString();
	}

	public static JsonConfig getDefaultConfig(Class<?> objClass) {
		JsonConfig jsconfig = new JsonConfig();
		jsconfig.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {
			@Override
			public String transformToJavaIdentifier(String arg0) {
				if (arg0.equals("ACCESS_RESULT")) {
					return arg0;
				}
				if (arg0.equals("ACCESS_CODE")) {
					return arg0;
				}
				if (arg0.equals("ACCESS_MSG")) {
					return arg0;
				}
				char[] chars = arg0.toCharArray();
				chars[0] = Character.toLowerCase(chars[0]);
				return new String(chars);
			}
		});
		jsconfig.setRootClass(objClass);
		return jsconfig;
	}

	// Begin Get Access Json Tool

	public static JSONObject getPageObj(Page page) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(page.getRecordList());
		response.put("recordList", jsonArray);
		response.put("currentPageNum", page.getCurrentPage());
		response.put("pageSize", page.getPageSize());
		response.put("pageCount", page.getPageCount());
		response.put("totalCount", page.getTotalCount());
		return response;
	}

	public static JSONObject getListObj(List<?> list) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.put("recordList", jsonArray);
		response.put("totalCount", list.size());
		return response;
	}

	public static String getPageJson(Page page) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(page.getRecordList());
		response.put("recordList", jsonArray);
		response.put("currentPageNum", page.getCurrentPage());
		response.put("pageSize", page.getPageSize());
		response.put("pageCount", page.getPageCount());
		response.put("totalCount", page.getTotalCount());
		return response.toString();
	}

	public static String getListJson(List<?> list) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.put("recordList", jsonArray);
		response.put("totalCount", list.size());
		return response.toString();
	}

	public static String getAccessJson(String result, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put("ACCESS_RESULT", result);
		response.put("ACCESS_CODE", code);
		response.put("ACCESS_MSG", msg);
		return response.toString();
	}

	public static JSONObject getAccessObj(String result, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put("ACCESS_RESULT", result);
		response.put("ACCESS_CODE", code);
		response.put("ACCESS_MSG", msg);
		return response;
	}

	public static String getAccessJson(Page page, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put("ACCESS_RESULT", getPageJson(page));
		response.put("ACCESS_CODE", code);
		response.put("ACCESS_MSG", msg);
		return response.toString();
	}

	public static String getAccessJson(List<?> list, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put("ACCESS_RESULT", getListJson(list));
		response.put("ACCESS_CODE", code);
		response.put("ACCESS_MSG", msg);
		return response.toString();
	}

	public static String getAccessJson(Object obj, String code, String msg) {
		JSONObject response = new JSONObject();
		response.put("ACCESS_RESULT", JSONObject.fromObject(obj).toString());
		response.put("ACCESS_CODE", code);
		response.put("ACCESS_MSG", msg);
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
		response.put("ACCESS_RESULT", JSONObject.fromObject(obj).toString());
		response.put("ACCESS_CODE", code);
		response.put("ACCESS_MSG", msg);
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
