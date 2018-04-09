package cn.umbrella.commons.utils.web;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.io.File;

/**
 * 
 * html转成图片工具类  
 *
 * @ClassName: Html2ImageUtil  
 * @author zhou.xy
 * @date 2016年11月16日 下午4:41:14  
 *
 */
public class Html2ImageUtil {
	public static void html2Image(String htmlStr, String filePath) {
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadHtml(htmlStr);
		imageGenerator.saveAsImage(filePath);
	}
	public static void html2Image(String htmlStr, File file) {
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadHtml(htmlStr);
		imageGenerator.saveAsImage(file);
	}
	public static void html2ImageWithUrl(String url, String filePath) {
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadUrl(url);
		imageGenerator.saveAsImage(filePath);
	}
	public static void image2Html(String htmlPath, String imagePath) {
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.saveAsHtmlWithMap(htmlPath, imagePath);
	}
	public static void image2Html(File file, String imagePath) {
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.saveAsHtmlWithMap(file, imagePath);
	}
	
	public static void main(String[] args) {
		String htmlStr = "<div width='500' height='500' cellpadding='0' cellspacing='0' bordercolor='#FFFFFF'>"
				+ "<img src='file:///D://data//snapshoot//20161117//2016111700014-19149.png'>"
				+ "</div>";
		html2Image(htmlStr, "D:\\data\\snapshoot\\a.png");
//		image2Html("D:\\data\\snapshoot\\a.html", "D:\\data\\snapshoot\\a.png");
	}
}
