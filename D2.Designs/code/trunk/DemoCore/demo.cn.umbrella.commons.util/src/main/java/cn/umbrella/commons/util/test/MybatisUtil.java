package cn.umbrella.commons.util.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MybatisUtil {
	protected static Logger logger = Logger.getLogger(MybatisUtil.class);
	
	private static String mybatisConfig = null;
	
	private static InputStream getMybatisConfig(){
		if(mybatisConfig == null){
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(Resources.getResourceAsStream("mybatis-config.xml"))); 
				StringBuffer buffer = new StringBuffer(); 
				String line = ""; 
				while ((line = in.readLine()) != null){ 
					buffer.append(line); 
				}
				mybatisConfig = buffer.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ByteArrayInputStream(mybatisConfig.getBytes());
	}
	
	public static synchronized List<Map<String, Object>> selectList(String sql, Map<String, Object> map){
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(getMybatisConfig());
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
		sb.append("<mapper namespace=\"mapper\">");
		sb.append("<select id=\"select\" resultType=\"map\">");
		sb.append(sql);
		sb.append("</select>");
		sb.append("</mapper>");
		logger.info(sb.toString());
		new XMLMapperBuilder(new ByteArrayInputStream(sb.toString().getBytes()), sqlSessionFactory.getConfiguration(), "", null).parse();
		return sqlSessionFactory.openSession().selectList("select", map);
	}

	public static synchronized int update(String sql, Map<String, Object> map){
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(getMybatisConfig());
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
		sb.append("<mapper namespace=\"mapper\">");
		sb.append("<update id=\"update\" parameterType=\"map\">");
		sb.append(sql);
		sb.append("</update>");
		sb.append("</mapper>");
		new XMLMapperBuilder(new ByteArrayInputStream(sb.toString().getBytes()), sqlSessionFactory.getConfiguration(), "", null).parse();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int updateNum = sqlSession.update("update", map);
		sqlSession.commit();
		sqlSession.close();
		return updateNum;
	}
	
	public static void main(String[] args) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "nick");
		List<Map<String, Object>> list = MybatisUtil.selectList("select * from "+"t_user"+" t <where><if test=\"username != null\">and t.username = #{username}</if></where>", map);
		for(Map<String, Object> e:list){
			System.out.println(e.get("USERNAME")+":"+e.get("PASSWORD"));
		}
		
		list = MybatisUtil.selectList("select * from "+"t_user_copy"+" t <where><if test=\"username != null\">and t.username = #{username}</if></where>", map);
		list = MybatisUtil.selectList("select * from "+"t_user"+" t <where><if test=\"username != null\">and t.username = #{username}</if></where>", map);
		list = MybatisUtil.selectList("select * from "+"t_user_copy"+" t <where><if test=\"username != null\">and t.username = #{username}</if></where>", null);
		for(Map<String, Object> e:list){
			System.out.println(e.get("USERNAME")+":"+e.get("PASSWORD"));
		}
	}
}
