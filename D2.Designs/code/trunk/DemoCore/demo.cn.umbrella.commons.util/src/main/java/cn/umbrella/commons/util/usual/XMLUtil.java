/** 
 * XMLUtil.java Created on Feb 1, 2010
 * Copyright 2010@Aslan. 
 * All right reserved. 
 */
package cn.umbrella.commons.util.usual;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * XML 工具类
 * 
 * @Time 12:22:26 PM
 */
public class XMLUtil {

	public static String DEFAULT_ENCODING = "utf-8";

	// -------------------- load --------------------//
	public Document parse(File file) throws DocumentException {
		if (!file.exists())
			return null;
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}

	public Document parse(String fileName) throws DocumentException {
		File file = new File(fileName);
		return parse(file);
	}

	public Document parse(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}

	public Document parseXml(String strXml) throws DocumentException {
		return isNull(strXml) ? null : DocumentHelper.parseText(strXml);
	}

	// -------------------- create --------------------//
	public static Document createDoc() {
		Document document = DocumentHelper.createDocument();
		// document.addElement("");
		return document;
	}

	public static Document createDoc(String rootName) {
		Document document = DocumentHelper.createDocument();
		if (!isNull(rootName))
			document.addElement(rootName);
		return document;
	}

	// -------------------- write --------------------//
	/**
	 * Document写入文件
	 * 
	 * @param document
	 * @throws IOException
	 */
	public void write(Document document, String fileName) throws IOException {
		write(document, fileName, DEFAULT_ENCODING);
	}

	/**
	 * 格式化XML文档,并按指定字符集输出
	 * 
	 * @param document
	 * @param fileName
	 * @param encoding
	 *            编码格式
	 * @return 返回操作结果, 0表失败, 1表成功
	 */
	public static int write(Document document, String fileName, String encoding)
			throws UnsupportedEncodingException, FileNotFoundException,
			IOException {
		int returnValue = 0;
		XMLWriter output = null;
		// 格式化输出,指定了格式化的方式为缩进式(非紧凑式)
		OutputFormat format = OutputFormat.createPrettyPrint();
		// format = OutputFormat.createCompactFormat();
		// 指定XML字符集编码
		encoding = isNull(encoding) ? DEFAULT_ENCODING : encoding;
		format.setEncoding(encoding);
		output = new XMLWriter(new FileOutputStream(new File(fileName)), format);
		// writer = new XMLWriter(new FileWriter(new File(filename)), format);
		output.write(document);
		output.close();
		// 执行成功,需返回1
		returnValue = 1;
		return returnValue;
	}

	public Element addElement(Document document, String lable) {
		return document.addElement(lable);
	}

	public Element addElement(Element element, String lable) {
		return element.addElement(lable);
	}

	public Element addAttribute(Element element, String name, String value) {
		return element.addAttribute(name, value);
	}

	// -------------------- read --------------------//
	public String getText(Document document) {
		return document == null ? "" : document.asXML();
	}

	public String getText(Element element) {
		return element == null ? "" : element.asXML();
	}

	public Node selectNode(Document document, String xpath) {
		return document.selectSingleNode(xpath);
	}

	@SuppressWarnings("unchecked")
	public List<Node> selectNodes(Document document, String xpath) {
		return document.selectNodes(xpath);
	}

	// -------------------- tree --------------------//

	/**
	 * 如果你处理的XML文件很大，这个时候建议你最好使用dom4j提供的快速遍历方法，这个可以满足你对性能的需求，
	 * 因为dom4j快速遍历不会为每层循环都创建一个Iterator对象
	 * 
	 * @param document
	 */
	public void treeWalk(Document document) {

		treeWalk(document.getRootElement());

	}

	/**
	 * Node node = element.node(i);关键是这一句，我们直接通过element下标就可以遍历节点，
	 * 而避免了为此创建Iterator对象，这就节省了大量遍历时间和资源
	 * 
	 * @param element
	 */
	public void treeWalk(Element element) {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
			} else {
				// do something....
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// XMLUtil xu = new XMLUtil();
		// Document doc = xu.createDoc();
		// String str = xu.getText(doc);
		// p(str);
		// doc = xu.createDoc("root");
		// p(xu.getText(doc));

		Document document = DocumentHelper.createDocument();
		Element catalogElement = document.addElement("catalog");
		document.addComment("XML Comment!");
		catalogElement.addComment("An XML Catalog");
		catalogElement.addProcessingInstruction("target", "text");
		Element journalElement = catalogElement.addElement("journal");
		journalElement.addAttribute("title", "XML Zone");
		journalElement.addAttribute("publisher", "IBM developerWorks");
		Element articleElement = journalElement.addElement("article");
		articleElement.addAttribute("level", "Intermediate");
		articleElement.addAttribute("date", "December-2001");
		Element titleElement = articleElement.addElement("title");
		titleElement.setText("Java configuration with XML Schema");
		Element authorElement = articleElement.addElement("author");
		Element firstNameElement = authorElement.addElement("firstname");
		firstNameElement.setText("Marcello");
		Element lastNameElement = authorElement.addElement("lastname");
		lastNameElement.setText("Vitaletti");
		document.addDocType("catalog", null, "file://c:/Dtds/catalog.dtd");
		XMLUtil xu = new XMLUtil();
		try {
			xu.write(document, "c:/temp/catalog1.xml");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			p(e1.getMessage());
		}
		p("S!");
		// Element employees = document.getRootElement();
		// for (Iterator i = employees.elementIterator(); i.hasNext();) {
		// Element employee = (Element) i.next();
		// for (Iterator j = employee.elementIterator(); j.hasNext();) {
		// Element node = (Element) j.next();
		// System.out.println(node.getName() + ":" + node.getText());
		// }
		//
		// }

	}

	public static void p(Object o) {
		System.out.println(o);
	}

	public static boolean isNull(String str) {
		return (str == null || "".equals(str));
	}

	/**
	 * string2Document 将字符串转为Document
	 * 
	 * @return
	 * @param s
	 *            xml格式的字符串
	 */
	public static Document string2Document(String s) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(s);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return doc;
	}

}
