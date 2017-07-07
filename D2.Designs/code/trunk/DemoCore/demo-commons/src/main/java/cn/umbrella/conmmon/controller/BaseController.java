package cn.umbrella.conmmon.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Aslan on 2016/5/11.
 */
public class BaseController {

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpSession session;

	private Gson gson = new GsonBuilder().disableHtmlEscaping()
			.setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	// 鏍规嵁json鍙傛暟杩斿洖Map
	public Map<String, String> fromJsonToMap(String data) {
		Map<String, String> result = gson.fromJson(data,
				new TypeToken<Map<String, String>>() {
				}.getType());
		if (result == null)
			result = new HashMap<String, String>();
		return result;
	}

	// 鏍规嵁json鍙傛暟杩斿洖Map
	public Map<String, Object> fromJsonToMapObj(String data) {
		Map<String, Object> result = gson.fromJson(data,
				new TypeToken<Map<String, String>>() {
				}.getType());
		if (result == null)
			result = new HashMap<String, Object>();
		return result;
	}
}