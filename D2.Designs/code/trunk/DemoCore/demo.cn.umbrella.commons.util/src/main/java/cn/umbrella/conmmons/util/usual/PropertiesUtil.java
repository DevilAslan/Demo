package cn.umbrella.conmmons.util.usual;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * properties文件内容获取
 * 
 * @author Aslan
 * @create 2012-9-8
 */
public class PropertiesUtil {
	private static String filePath;
	private static Properties props;

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		PropertiesUtil.filePath = filePath;
	}

	public PropertiesUtil() {
	}

	@SuppressWarnings("static-access")
	public PropertiesUtil(String filePath) {
		this.setFilePath(filePath);
		readProperties();
	}

	/**
	 * 方法描述： （参数含义说明如下） 初始化
	 */
	private static void readProperties() {
		File configFile = new File(getFilePath());
		props = new Properties();
		try {
			props.load(new FileInputStream(configFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readProperties(String filePath) {
		setFilePath(filePath);
		readProperties();
	}

	/**
	 * 写入properties信息
	 */
	public static void writeProperties(String key, String value) {
		try {
			OutputStream fos = new FileOutputStream(getFilePath());
			props.setProperty(key, value);
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "『comments』Update key：" + key);
		} catch (IOException e) {
		}
	}

	/**
	 * 
	 * @param filePath
	 * @param key
	 * @param value
	 */
	public static void writeProperties(String filePath, String key, String value) {
		try {
			OutputStream fos = new FileOutputStream(filePath);
			props.setProperty(key, value);
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "『comments』Update key：" + key);
		} catch (IOException e) {
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String readValue(String key) {
		if (null == props) {
			readProperties();
		}
		return props.getProperty(key);
	}

	/**
	 * 
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String readValue(String filePath, String key) {
		readProperties(filePath);
		return readValue(key);
	}

	/**
	 * 获取所有属性，返回一个map,不常用 可以试试props.putAll(t)
	 * 
	 * @return
	 */
	public static Map<String, String> getAllProperty() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> enu = props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 在控制台上打印出所有属性，调试时用。
	 */
	public static void printProperties() {
		if (null == props) {
			readProperties();
		}
		props.list(System.out);
	}

	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public static Map<String, String> parseProperties(String filePath) {
		Map<String, String> hm = new ConcurrentHashMap<String, String>();
		readProperties(filePath);
		Set<Map.Entry<Object, Object>> set = props.entrySet();
		Iterator<Entry<Object, Object>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<Object, Object> e = it.next();
			hm.put(e.getKey().toString(), e.getValue().toString());
		}
		return hm;
	}

	public static void main(String[] args) {

		// String path = "/com/common/config/properties/memcached.properties";
		// PropertiesUtils.setFilePath(path);
		// PropertiesUtil PropertiesUtil = new PropertiesUtil(path);
		// PropertiesUtils.printProperties();

		// System.out.println(PropertiesUtil.readValue("version.add"));
		// System.out.println(PropertiesUtil.readValue(path, "version.add"));

		// PropertiesUtil.writeProperties("key", "value");
		// PropertiesUtil.writeProperties(path, "key", "value");

		String filePath = "/com/common/config/properties/oracle_map.properties";
		PropertiesUtil.setFilePath(filePath);
		System.out.println(PropertiesUtil.readValue(filePath, "db.flag"));
	}
}
