package cn.umbrella.conmmons.util.seo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.lucene.search.Query;

import cn.umbrella.conmmons.util.lucene.seo.LuceneAnalyzerUtil;


public class LuceneUtil {
	private static String indexDir;//lucene索引文件存储根目录
	private static Integer hintNum;//lucene 单次搜索最多数据
	static{
		Properties properties = new Properties();
		try {
			properties.load(LuceneUtil.class.getResourceAsStream("/config_params.properties"));
			indexDir = properties.getProperty("indexDir");
			hintNum = Integer.parseInt(properties.getProperty("hintNum"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * 根据tableName从lucene索引中获取数据后转换成map类型的list返回
	 * @param tableName
	 * @param query
	 * @return
	 */
	public static List<Map<String,String>> getLuceneList(String tableName,Query query){
		String dir = indexDir + File.separator + tableName;
		return LuceneAnalyzerUtil.searchConvertToMap(query, dir, hintNum);
	}
	
	/**
	 * 
	* @Title: getLuceneList
	* @Description: 根据tableName从lucene索引中获取数据后转换成map类型的list返回
	* @param tableName
	* @param query
	* @param count 限制命中数量
	* @return List<Map<String,String>>
	 */
	public static List<Map<String,String>> getLuceneList(String tableName, Query query, Integer count){
		String dir = indexDir + File.separator + tableName;
		return LuceneAnalyzerUtil.searchConvertToMap(query, dir, count);
	}
	
}

