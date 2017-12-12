package cn.umbrella.business.service;

import java.util.List;
import java.util.Map;

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.commons.model.BaseModel;

import com.github.pagehelper.PageInfo;

public interface IDemoService {
	public int add(BaseModel model);

	public int addBatch(List<BaseModel> list);

	public int edit(BaseModel BaseModel);

	public int editBatch(List<BaseModel> list);

	public int delete(String id);

	public int deleteBatch(List<String> ids);

	public BaseModel findById(String id);

	public List<BaseModel> query(Map<String, Object> map);

	public int queryCount(Map<String, Object> map);
	
	public List<BaseModel> queryBatch(BaseQuery query);
	
	public PageInfo<BaseModel> queryPageModel(BaseQuery query);
	
	public PageInfo<Map<String, Object>> queryPage(BaseQuery query);
	

	/**
	 * 根据TradeCode分组查询
	 */
	public List<Map<String,Object>> queryGroupByTradeCode();
	
}