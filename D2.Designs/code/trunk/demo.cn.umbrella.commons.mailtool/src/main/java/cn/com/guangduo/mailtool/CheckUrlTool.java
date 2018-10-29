package cn.com.guangduo.mailtool;

import java.net.HttpURLConnection;
import java.net.URL;

import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

public class CheckUrlTool {
	
	public static int check(String url) throws Exception {
		HttpURLConnection uc = (HttpURLConnection) new URL(url).openConnection();
		uc.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		uc.setRequestProperty("Accept-Encoding","gzip, deflate");
		uc.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
		uc.setRequestProperty("Cache-Control","max-age=0");
		uc.setRequestProperty("Connection","keep-alive");
		uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		uc.setRequestProperty("Upgrade-Insecure-Requests","1");
		uc.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
		
		uc.setConnectTimeout(10000);
		uc.setDoOutput(true);
		uc.setRequestMethod("GET");
		uc.setUseCaches(false);
		
		Parser parser = new Parser(uc);
		NodeList list = parser.parse(null);
		String content = list.toHtml();
		TagNameFilter filter = new TagNameFilter("title");
		parser = new Parser(content);
		NodeList nodes = parser.parse(filter);
		
		if(nodes.size()>0){
			return 0;
		}
		return 1;
	}
}
