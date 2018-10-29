package cn.umbrella.mss.service;

import java.util.Map;

import cn.umbrella.commons.bean.ResponseData;

public interface ICommitmentBookService {
	
	
	/**
	 * Description ：信用信息管理，信用承诺书管理处理导入Excel
	 * Author ：林吉前
	 * Create Date ： 2017年6月29日
	 * History: (Version) Author Date Annotation 
	 * @param params
	 * @return
	 */
	public ResponseData importExcel(Map<String,Object> params);
	
	
}