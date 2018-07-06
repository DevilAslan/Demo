package cn.umbrella.custom.utils.test;

import cn.umbrella.commons.enums.MethodType;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MainEnum {

	public static void main(String[] args) {
		JSONArray jsonArray = new JSONArray();
		for(MethodType item : MethodType.values()){
			JSONObject obj = new JSONObject();
			obj.put("key", item.getKey());
			obj.put("value", item.getValue());
			jsonArray.add(item);
		}
		System.out.println(MethodType.class.getSimpleName()+"0.0"+jsonArray.toString()+";");
	}

}
