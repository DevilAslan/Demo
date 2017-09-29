package cn.umbrella.finance.util.alipay.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.CollectionUtils;


/**
 * 
 * @author Gerrard
 * @Date 2016年1月20日上午10:28:47
 */
public class XmlToMapUtils {

	private static Logger log = Logger.getLogger(XmlToMapUtils.class);
	/**
	 * JDOM解析XML格式字符
	 * @param str
	 * @return
	 */
	public static Map<String,Object> xmlToMap(String str){
		Map<String,Object> map = new HashMap<String, Object>();
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			if(null == str || "".equals(str)){
				return map;
			}
			Document document = saxBuilder.build(new StringReader(str));
			Element element = document.getRootElement();
			xmlConvert(element, map);
		} catch (Exception e) {
			log.error("解析XML：", e);
		}
		return map;
	}
	
	public static void xmlConvert(Element element,Map<String,Object> map){
		List<Attribute> listAttributes = element.getAttributes();//节点属性
		for(Attribute a : listAttributes){
			map.put(a.getName(), a.getValue());
		}
		List<Element> list = element.getChildren();//子节点
		if(!CollectionUtils.isEmpty(list)){
			for (Element children : list) {
				xmlConvert(children, map);
			}
		}else{
			map.put(element.getName(), element.getText());
		}
		
	}
}
