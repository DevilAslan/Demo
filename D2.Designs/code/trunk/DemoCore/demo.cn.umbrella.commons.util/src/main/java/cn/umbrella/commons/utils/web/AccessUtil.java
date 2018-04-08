package cn.umbrella.commons.utils.web;

import java.util.List;

import cn.umbrella.commons.config.Constant;
import cn.umbrella.commons.config.CryptConfig;
import cn.umbrella.commons.enums.AccessCode;
import cn.umbrella.commons.model.Page;


import cn.umbrella.commons.utils.base.StringUtil;

import cn.umbrella.commons.utils.crypt.DESede;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;
//import net.sf.json.util.JavaIdentifierTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AccessUtil {
	
	/**
	 * 
	 * @param errorMsg
	 * @return
	 */
	
	public static String getSuccessAccessJson(String errorMsg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, AccessCode.SUCCESS.getKey());
		if(StringUtil.isEmpty(errorMsg)){response.put(Constant.ACCESS_MSG, AccessCode.SUCCESS.getValue());}
		else{response.put(Constant.ACCESS_MSG, errorMsg);}
		return response.toString();
	}
	
	public static String getSuccessAccessJson() {
		return getSuccessAccessJson(null);
	}
	
	public static JSONObject getSuccessAccessObj(String errorMsg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, AccessCode.SUCCESS.getKey());
		if(StringUtil.isEmpty(errorMsg)){response.put(Constant.ACCESS_MSG, AccessCode.SUCCESS.getValue());}
		else{response.put(Constant.ACCESS_MSG, errorMsg);}
		return response;
	}
	
	public static JSONObject getSuccessAccessObj() {
		return getSuccessAccessObj(null);
	}
	
	public static String getInvalidAccessJson(String errorMsg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, AccessCode.INVALID.getKey());
		if(StringUtil.isEmpty(errorMsg)){response.put(Constant.ACCESS_MSG, AccessCode.INVALID.getValue());}
		else{response.put(Constant.ACCESS_MSG, errorMsg);}
		return response.toString();
	}
	
	public static String getFailAccessJson(String errorMsg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, AccessCode.FAIL.getKey());
		if(StringUtil.isEmpty(errorMsg)){response.put(Constant.ACCESS_MSG, AccessCode.FAIL.getValue());}
		else{response.put(Constant.ACCESS_MSG, errorMsg);}
		return response.toString();
	}

	public static String getFailAccessJson() {
		return getFailAccessJson(null);
	}
	
	public static JSONObject getFailAccessObj(String errorMsg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, AccessCode.FAIL.getKey());
		if(StringUtil.isEmpty(errorMsg)){response.put(Constant.ACCESS_MSG, AccessCode.FAIL.getValue());}
		else{response.put(Constant.ACCESS_MSG, errorMsg);}
		return response;
	}

	public static JSONObject getFailAccessObj() {
		return getFailAccessObj(null);
	}
	
	//----------------------------------------------------------------------------------------------
	public static JSONObject getPageObj(Page page) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = (JSONArray) JSON.toJSON(page);
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

	public static String getAccessJson(String result,String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, result);
		response.put(Constant.ACCESS_CODE, AccessCode.SUCCESS);
		if(StringUtil.isEmpty(msg)){response.put(Constant.ACCESS_MSG, AccessCode.SUCCESS.getValue());}
		else{response.put(Constant.ACCESS_MSG, msg);}
		return response.toString();
	}

	public static JSONObject getAccessObj(String result, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, result);
		response.put(Constant.ACCESS_CODE, AccessCode.SUCCESS);
		if(StringUtil.isEmpty(msg)){response.put(Constant.ACCESS_MSG, AccessCode.SUCCESS.getValue());}
		else{response.put(Constant.ACCESS_MSG, msg);}
		return response;
	}

	public static String getAccessJson(Page page, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, getPageJson(page));
		response.put(Constant.ACCESS_CODE, AccessCode.SUCCESS);
		if(StringUtil.isEmpty(msg)){response.put(Constant.ACCESS_MSG, AccessCode.SUCCESS.getValue());}
		else{response.put(Constant.ACCESS_MSG, msg);}
		return response.toString();
	}

	public static String getAccessJson(List<?> list, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, getListJson(list));
		response.put(Constant.ACCESS_CODE, AccessCode.SUCCESS);
		if(StringUtil.isEmpty(msg)){response.put(Constant.ACCESS_MSG, AccessCode.SUCCESS.getValue());}
		else{response.put(Constant.ACCESS_MSG, msg);}
		return response.toString();
	}

	public static String getAccessJson(Object obj, String msg) {
		JSONObject response = new JSONObject();
		response.put(Constant.ACCESS_RESULT, JSON.toJSON(obj));
		response.put(Constant.ACCESS_CODE, AccessCode.SUCCESS);
		if(StringUtil.isEmpty(msg)){response.put(Constant.ACCESS_MSG, AccessCode.SUCCESS.getValue());}
		else{response.put(Constant.ACCESS_MSG, msg);}
		return response.toString();
	}

	/**getAccessJsonToken
	 * flag doing
	 * @param obj
	 * @param code
	 * @param msg
	 * @param keyCode
	 * @return
	 */
	public static String getAccessJsonEnTest(Object obj
			,String msg
			, String keyCode) {
		JSONObject response = new JSONObject();
		
		String res = EnTest(response.toString(), keyCode);
		boolean isDecrypt = false;
		isDecrypt = ParamUtil.DeTest(response.toString(), res, keyCode);
		
		if(isDecrypt){
			response.put(Constant.ACCESS_RESULT, JSON.toJSON(obj));
			response.put(Constant.ACCESS_CODE, AccessCode.SUCCESS);
			if(StringUtil.isEmpty(msg)){response.put(Constant.ACCESS_MSG, AccessCode.SUCCESS.getValue());}
			else{response.put(Constant.ACCESS_MSG, msg);}
		}else{
			
		}
		return res;
	}
	public final static String encryptKey = "2012PinganVitality075522628888ForShenZhenBelter075561869839";// 分配得到
	static String EnTest(String content, String keyCode) {
		String encryptText = content + "";
		String enContent = null;
		if (StringUtil.isEquals(CryptConfig.DESEDE, keyCode)) {
			enContent = DESede.encryptMode(encryptKey, encryptText);
		}
		return enContent;
	}
}
