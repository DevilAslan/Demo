package cn.umbrella.commons.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixianpeng on 2016/5/11.
 */
public class BaseController {

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpSession session;

	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
			.create();

	// 根据json参数返回Map
	public Map<String, String> fromJsonToMap(String data) {
		Map<String, String> result = gson.fromJson(data,
				new TypeToken<Map<String, String>>() {
				}.getType());
		if (result == null)
			result = new HashMap<String, String>();
		return result;
	}

	// 根据json参数返回Map
	public Map<String, Object> fromJsonToMapObj(String data) {
		Map<String, Object> result = gson.fromJson(data.trim(),
				new TypeToken<Map<String, String>>() {
				}.getType());
		if (result == null)
			result = new HashMap<String, Object>();
		return result;
	}

}
