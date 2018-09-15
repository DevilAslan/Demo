package cn.umbrella.commons.base;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.github.pagehelper.PageInfo;

@WebService
public interface IBaseService<MODEL, KEY_TYPE> {

	public int add(MODEL model);

	public int add(List<MODEL> models);

	public int update(MODEL model);

	public int update(List<MODEL> models);

	public int delete(KEY_TYPE id);

	public int delete(List<KEY_TYPE> ids);

	public MODEL query(KEY_TYPE id);

	public List<MODEL> query(Map<String, Object> map);

	public PageInfo<MODEL> queryPage(BaseQuery query);
	

	// **************************************以下方法为通用数据库操作方法***BEGIN************************************************************//
	public <T> List<T> queryOther(String sqlId, Map<String, Object> map) throws Exception;

	public <T> PageInfo<T> queryPageOther(String sqlId, BaseQuery query) throws Exception;
	
	public <T> T queryOneOther(String sqlId,Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Description: 新增（批量）
	 * @Title: add
	 * @param sqlId
	 *            映射sql的标识字符串
	 * @param models
	 * @return
	 * @throws Exception
	 *             int
	 * @throws
	 */
	public int add(String sqlId, List<MODEL> models) throws Exception;

	/**
	 * 
	 * @Description: 新增（单条记录）
	 * @Title: add
	 * @param sqlId
	 *            映射sql的标识字符串
	 * @param model
	 * @return
	 * @throws Exception
	 *             int
	 * @throws
	 */
	public int add(String sqlId, MODEL model) throws Exception;

	/**
	 * 
	 * @Description: 查询（多条记录）
	 * @Title: query
	 * @param sqlId
	 *            映射sql的标识字符串
	 * @param map
	 * @return List<Model>
	 * @throws
	 */
	public List<MODEL> query(String sqlId, Map<String, Object> map)
			throws Exception;

	/**
	 * 
	 * 查询分页
	 * 
	 * @Title: query
	 * @param sqlId
	 * @param query
	 * @return PageInfo<MODEL>
	 * @throws
	 */
	public PageInfo<MODEL> query(String sqlId, BaseQuery query)
			throws Exception;

	/**
	 * 
	 * @Description: 查询（一条记录）
	 * @Title: queryOne
	 * @param sqlId
	 * @param map
	 * @return
	 * @throws Exception
	 *             Model
	 * @throws
	 */
	public MODEL queryOne(String sqlId, Map<String, Object> map)
			throws Exception;

	/**
	 * 
	 * @Description: 根据Bean查询（一条记录）
	 * @Title: query
	 * @param sqlId
	 * @param modelParameter
	 * @return
	 * @throws Exception
	 *             Model
	 * @throws
	 */
	public MODEL query(String sqlId, MODEL modelParameter)
			throws Exception;

	/**
	 * 
	 * @Description: 更新（单条、批量）
	 * @Title: update
	 * @param sqlId
	 * @param models
	 * @return
	 * @throws Exception
	 *             int
	 * @throws
	 */
	public int update(String sqlId, List<MODEL> models) throws Exception;

	/**
	 * 
	 * @Description: 更新（单条记录）
	 * @Title: update
	 * @param sqlId
	 * @param model
	 * @return
	 * @throws Exception
	 *             int
	 * @throws
	 */
	public int update(String sqlId, MODEL model) throws Exception;

	/**
	 * 
	 * @Description: 删除 （单条、批量）
	 * @Title: delete
	 * @param sqlId
	 * @param map
	 * @return
	 * @throws Exception
	 *             int
	 * @throws
	 */
	public int delete(String sqlId, Map<String, Object> map)
			throws Exception;

	/**
	 * 
	 * @Description: 删除（单条记录）
	 * @Title: delete
	 * @param sqlId
	 * @param model
	 * @return
	 * @throws Exception
	 *             int
	 * @throws
	 */
	public int delete(String sqlId, MODEL model) throws Exception;

}