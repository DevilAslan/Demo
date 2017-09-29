package cn.umbrella.commons.bean;

import java.util.HashMap;
import java.util.Map;

import cn.umbrella.commons.enums.ResponseStatus;

/**
 * 
 * @ClassName: AjaxResponseData
 * @Description: ajax请求返回对象
 * @author zhou.xy
 * @date 2015年9月18日 上午11:48:42
 *
 */
public class ResponseData {
	private ResponseStatus status = null;
	private String code = null;
	private String msg = null;
	private String token = null;
	private String md5 = null;
	private Map<String, Object> data = null;
	
	public ResponseData() {
		this("请求成功");
	}
	
	public ResponseData(String msg) {
		this(ResponseStatus.SUCCESS,msg);
	}
	public ResponseData(ResponseStatus status, String msg) {
		this(status,msg,new HashMap<String, Object>());
	}
	public ResponseData(ResponseStatus status, String msg,
			Map<String, Object> data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	
	public void put(String key, Object value) {
		data.put(key, value);
	}
	
	/**
	 * 链式操作
	 * @param key
	 * @param value
	 * @return
	 */
	public ResponseData addData(String key, Object value) {
		data.put(key, value);
		return this;
	}
	public int getStatus() {
		return status.getValue();
	}
	
	public ResponseStatus getResponseStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
