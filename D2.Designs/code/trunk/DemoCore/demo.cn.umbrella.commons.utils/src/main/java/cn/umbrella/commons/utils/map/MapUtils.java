package cn.umbrella.commons.utils.map;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import cn.umbrella.commons.utils.map.MapParamater.MapDetails;

import com.alibaba.fastjson.JSON;

public class MapUtils {
	protected static final String AK = "Yj1xXXkvFZXEoWXUG1qDZMZmFxQOGK3G";

	protected static final String CITY = "温州";

	protected static StringBuffer URL = new StringBuffer("http://api.map.baidu.com/place/v2/search?output=json&page_size=1&ak=" + AK);

	/**
	 * 根据地址获取经纬度
	 *
	 * @Title: getPointByAddress
	 * @param address
	 * @throws Exception
	 * @return Map<String,Object> :如 {lng=120.667931, lat=28.018465}
	 */
	public static Map<String, Object> getPointByAddress(final String address) throws Exception {
		if (address == null || address.length() < 2) {
			return null;
		}
		URL.append("&query=" + URLEncoder.encode(address, "UTF-8"));
		URL.append("&region=" + URLEncoder.encode("中国", "UTF-8"));
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(URL.toString());
		client.executeMethod(getMethod);
		String responseBody = getMethod.getResponseBodyAsString();
		MapParamater obj = JSON.parseObject(responseBody, MapParamater.class);
		if (obj.getResults() != null && obj.getResults().size() > 0) {
			MapDetails mapDetails = obj.getResult();
			if (mapDetails != null) {
				return mapDetails.getLocation();
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> map = getPointByAddress("浙江省丽水地区遂昌县");
		System.out.println(map);
	}

}
