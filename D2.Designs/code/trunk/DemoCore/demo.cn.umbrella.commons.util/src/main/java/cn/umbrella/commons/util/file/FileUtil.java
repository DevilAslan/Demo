package cn.umbrella.commons.util.file;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

/**
 * 
 * @date:2016年10月24日
 * @备注：文件及文件夹基本操作 读写 判断
 */
public class FileUtil {
	/* 写文件 */
	public static boolean writeFile(String str, String filePath, String fileName) {
		boolean flag = false;
		FileOutputStream fos = null;
		try {
			File file = new File(filePath, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file, false);
			StringBuffer sb = new StringBuffer();
			sb.append(str + "\n");
			fos.write(sb.toString().getBytes("UTF-8"));
			flag = true;
		} catch (IOException e) {
			flag = false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/* 读文件 */
	public static String readFile(String filePath, String fileName) {
		StringBuffer sb = new StringBuffer();
		String tempStr = null;
		BufferedReader br = null;
		try {
			File file = new File(filePath, fileName);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			br = new BufferedReader(isr);
			while ((tempStr = br.readLine()) != null) {
				sb.append(tempStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * word转html
	 * @param input 输入文件流
	 * @param path 存放路径+文件名.后缀
	 * @throws IOException
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	public static void WordToHtml(InputStream input,final String path) throws IOException,
	TransformerException, ParserConfigurationException {
	HWPFDocument wordDocument = new HWPFDocument(input);
	WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	// 设置图片存放的位置
	wordToHtmlConverter.setPicturesManager(new PicturesManager() {
		public String savePicture(byte[] content, PictureType pictureType,
				String suggestedName, float widthInches, float heightInches) {
			
			String imagepath = path.substring(0,path.lastIndexOf("\\"));
			imagepath = path.substring(0,path.lastIndexOf("\\"))+"\\image\\";
			File imgPath = new File(imagepath);
			if (!imgPath.exists()) {// 图片目录不存在则创建
				imgPath.mkdirs();
			}
			File file = new File(imagepath + suggestedName);
			try {
				OutputStream os = new FileOutputStream(file);
				os.write(content);
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return imagepath + suggestedName;
		}
	});

	// 解析word文档
	wordToHtmlConverter.processDocument(wordDocument);
	Document htmlDocument = wordToHtmlConverter.getDocument();
	
	File htmlFile = new File(path);
	OutputStream outStream = new FileOutputStream(htmlFile);
	
	// 也可以使用字符数组流获取解析的内容
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// OutputStream outStream = new BufferedOutputStream(baos);
	
	DOMSource domSource = new DOMSource(htmlDocument);
	StreamResult streamResult = new StreamResult(outStream);
	
	TransformerFactory factory = TransformerFactory.newInstance();
	Transformer serializer = factory.newTransformer();
	serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	serializer.setOutputProperty(OutputKeys.METHOD, "html");
	
	serializer.transform(domSource, streamResult);
	
	// 也可以使用字符数组流获取解析的内容
	// String content = baos.toString();
	// System.out.println(content);
	// baos.close();
	outStream.close();
	}
	
	/**
	 * html转图片
	 * @param readPath 文件读取路径
	 * @param savaPath 文件存储路径（包含保存的文件名及后缀（后缀为.png））
	 * @throws Exception
	 */
	public static void htmlToPic(String readPath,String savePath) throws Exception {
		JEditorPane ed = new JEditorPane(new URL(readPath));
		ed.setSize(1000, 1900);
		Thread.sleep(100);
		// 创建一个新的图片
		BufferedImage image = new BufferedImage(ed.getWidth(), ed.getHeight(),BufferedImage.TYPE_INT_ARGB);
		// 往图片上绘制内容
		SwingUtilities.paintComponent(image.createGraphics(), ed, new JPanel(),0, 0, image.getWidth(), image.getHeight());
		// 保存图片文件
		ImageIO.write((RenderedImage) image, "png", new File(savePath));
	}
	
	/**
	 * 
	* @Title: copyFolder
	* @Description: 复制整个文件夹（所有内容）到指定目录
	* @param sourceFolder 源文件夹
	* @param targetFolder 目标文件夹
	* @return void
	 */
	public static void copyFolder(String sourceFolder, String targetFolder) {
		File sourceFile = new File(sourceFolder);
		if (sourceFile.exists() && sourceFile.isDirectory()) {// 原文件夹存在并且是文件夹
			try {
				File targetFile = new File(targetFolder);
				targetFile.mkdirs();// 目标文件夹不存在则新建文件夹
				
				String[] files = sourceFile.list();
				File tempFile = null;
				for (int i = 0; i < files.length; i++) {
					String file = files[i];
					if (StringUtils.endsWith(sourceFolder, File.separator)) {
						tempFile = new File(sourceFolder + file);
					} else {
						tempFile = new File(sourceFolder + File.separator + file);
					}
					
					if (tempFile.isFile()) {
						FileInputStream input = new FileInputStream(tempFile);
						FileOutputStream output = new FileOutputStream(targetFolder + File.separator + Objects.toString(tempFile.getName()));
						byte[] b = new byte[2014 * 5];
						int len;
						while ((len = input.read(b)) != -1) {
							output.write(b, 0, len);
						}
						output.flush();
						output.close();
						input.close();
					} else if (tempFile.isDirectory()) {
						copyFolder(sourceFolder + File.separator + file, targetFolder + File.separator + file);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		copyFolder("D:\\ruian_credit\\cms\\src\\main\\webapp\\WEB-INF\\ftl\\1934", "D:\\ruian_credit\\cms\\src\\main\\webapp\\WEB-INF\\ftl\\888");
	}
}
