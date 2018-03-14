package cn.umbrella.commons.base.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.umbrella.commons.base.dao.BaseDao;
import cn.umbrella.commons.base.model.BaseModel;
import cn.umbrella.commons.base.model.BaseQuery;
import cn.umbrella.commons.tool.test.BeanTool;
import cn.umbrella.commons.util.web.SessionUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @Description: BaseService 定义一些基础的业务，并且提供扩展支持  
 * @ClassName: BaseService  
 * @author zhou.xy
 * @date 2015年12月23日 下午3:30:52  
 *  
 * @param <Model> BEAN对象
 * @param <KEY_TYPE> 表主键类型
 */
public class BaseService<MODEL extends BaseModel<KEY_TYPE>, KEY_TYPE> implements IBaseService<MODEL, KEY_TYPE> {
	@Autowired
	SqlSessionFactory sessionFactory;
	@Autowired
	BaseDao<MODEL, KEY_TYPE> baseDao;
	
	private final BaseDao<MODEL, KEY_TYPE> getBaseDao() {
		return baseDao;
	}
	
	public int add(MODEL model) {
		List<IPluginBeforeService> beforeService = SessionUtil.getBeansOfType(IPluginBeforeService.class);
		List<IPluginAfterService> afterService = SessionUtil.getBeansOfType(IPluginAfterService.class);
		for (IPluginBeforeService plugin : beforeService) {
			plugin.process(model);
		}
		int insertOne = getBaseDao().insertOne(model);
		for (IPluginAfterService plugin : afterService) {
			plugin.process(model);
		}
		return insertOne;
	}
	public int add(List<MODEL> models) {
		List<IPluginBeforeService> beforeService = SessionUtil.getBeansOfType(IPluginBeforeService.class);
		List<IPluginAfterService> afterService = SessionUtil.getBeansOfType(IPluginAfterService.class);
		for(MODEL model : models) {
			for (IPluginBeforeService plugin : beforeService) {
				plugin.process(model);
			}
		}
		int insertBatch = getBaseDao().insertBatch(models);
		for(MODEL model : models) {
			for (IPluginAfterService plugin : afterService) {
				plugin.process(model);
			}
		}
		return insertBatch;
	}
	public int update(MODEL model) {
		List<IPluginBeforeService> beforeService = SessionUtil.getBeansOfType(IPluginBeforeService.class);
		List<IPluginAfterService> afterService = SessionUtil.getBeansOfType(IPluginAfterService.class);
		for (IPluginBeforeService plugin : beforeService) {
			plugin.process(model);
		}
		int updateNum = getBaseDao().update(model);
		for (IPluginAfterService plugin : afterService) {
			plugin.process(model);
		}
		return updateNum;
	}
	public int update(List<MODEL> models) {
		List<IPluginBeforeService> beforeService = SessionUtil.getBeansOfType(IPluginBeforeService.class);
		List<IPluginAfterService> afterService = SessionUtil.getBeansOfType(IPluginAfterService.class);
		for(MODEL model : models) {
			for (IPluginBeforeService plugin : beforeService) {
				plugin.process(model);
			}
		}
		int updateBatch = getBaseDao().updateBatch(models);
		for(MODEL model : models) {
			for (IPluginAfterService plugin : afterService) {
				plugin.process(model);
			}
		}
		return updateBatch;
	}
	public int delete(KEY_TYPE id) {
		return getBaseDao().delete(id);
	}
	public int delete(List<KEY_TYPE> ids) {
		return getBaseDao().deleteBatch(ids);
	}
	public MODEL query(KEY_TYPE id) {
		return getBaseDao().queryById(id);
	}
	public List<MODEL> query(Map<String, Object> map) {
		return getBaseDao().query(map);
	}
	public PageInfo<MODEL> queryPage(BaseQuery query) {
		PageHelper.startPage(query.getPageNum(), query.getPageSize(), query.getOrderBy());
		return new PageInfo<MODEL>(query(query.getQueryData()));
	}
	// **************************************以下方法为通用数据库操作方法***BEGIN************************************************************//
	/**
	 * 
	 * @Description: 新增（批量）
	 * @Title: add 
	 * @param sqlId 映射sql的标识字符串
	 * @param models
	 * @return
	 * @throws Exception int
	 * @throws
	 */
	public int add(String sqlId, List<MODEL> models) throws Exception {
		List<IPluginBeforeService> beforeService = SessionUtil.getBeansOfType(IPluginBeforeService.class);
		List<IPluginAfterService> afterService = SessionUtil.getBeansOfType(IPluginAfterService.class);
		for(MODEL model : models) {
			for (IPluginBeforeService plugin : beforeService) {
				plugin.process(model);
			}
		}
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		int insertNum = sqlSession.insert(sqlId, models);
		sqlSession.close();
		for(MODEL model : models) {
			for (IPluginAfterService plugin : afterService) {
				plugin.process(model);
			}
		}
		return insertNum;				
	}
	
	/**
	 * 
	 * @Description: 新增（单条记录）
	 * @Title: add 
	 * @param sqlId 映射sql的标识字符串
	 * @param model
	 * @return
	 * @throws Exception int
	 * @throws
	 */
	public int add(String sqlId, MODEL model) throws Exception {
		List<IPluginBeforeService> beforeService = SessionUtil.getBeansOfType(IPluginBeforeService.class);
		List<IPluginAfterService> afterService = SessionUtil.getBeansOfType(IPluginAfterService.class);
		for (IPluginBeforeService plugin : beforeService) {
			plugin.process(model);
		}
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		int insertNum = sqlSession.insert(sqlId, model);
		sqlSession.close();
		for (IPluginAfterService plugin : afterService) {
			plugin.process(model);
		}
		return insertNum;				
	}
	
	/**
	 * 
	 * @Description: 查询（多条记录） 
	 * @Title: query 
	 * @param sqlId  映射sql的标识字符串
	 * @param map
	 * @return List<Model>
	 * @throws
	 */
	public List<MODEL> query(String sqlId, Map<String, Object> map) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		List<MODEL> list = sqlSession.selectList(sqlId, map);
		sqlSession.close();
		return list;
	}
	public <T> List<T> queryOther(String sqlId, Map<String, Object> map) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		List<T> list = sqlSession.selectList(sqlId, map);
		sqlSession.close();
		return list;
	}
	/**
	 * 
	 * 查询分页  
	 * @Title: query 
	 * @param sqlId
	 * @param query
	 * @return PageInfo<MODEL>
	 * @throws
	 */
	public PageInfo<MODEL> query(String sqlId, BaseQuery query) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		PageHelper.startPage(query.getPageNum(), query.getPageSize(), query.getOrderBy());
		List<MODEL> list = sqlSession.selectList(sqlId, query.getQueryData());
		sqlSession.close();
		return new PageInfo<MODEL>(list);
	}
	public <T> PageInfo<T> queryPageOther(String sqlId, BaseQuery query) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		PageHelper.startPage(query.getPageNum(), query.getPageSize(), query.getOrderBy());
		List<T> list = sqlSession.selectList(sqlId, query.getQueryData());
		sqlSession.close();
		return new PageInfo<T>(list);
	}
	
	public <T> T queryOneOther(String sqlId,Map<String, Object> param) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		
		T result = sqlSession.selectOne(sqlId, param);
		sqlSession.close();
		return result;
	}
	/**
	 * 
	 * @Description: 查询（一条记录）
	 * @Title: queryOne 
	 * @param sqlId
	 * @param map
	 * @return
	 * @throws Exception Model
	 * @throws
	 */
	public MODEL queryOne(String sqlId, Map<String, Object> map) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		MODEL model = sqlSession.selectOne(sqlId, map);
		sqlSession.close();
		return model;
	}
	
	/**
	 * 
	 * @Description: 根据Bean查询（一条记录）
	 * @Title: query 
	 * @param sqlId
	 * @param modelParameter
	 * @return
	 * @throws Exception Model
	 * @throws
	 */
	public MODEL query(String sqlId, MODEL modelParameter) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		MODEL model = sqlSession.selectOne(sqlId, BeanTool.pojoToMap(modelParameter));
		sqlSession.close();
		return model;
	}

	/**
	 * 
	 * @Description: 更新（单条、批量）
	 * @Title: update 
	 * @param sqlId
	 * @param models
	 * @return
	 * @throws Exception int
	 * @throws
	 */
	public int update(String sqlId, List<MODEL> models) throws Exception {
		List<IPluginService> plugins = SessionUtil.getBeansOfType(IPluginService.class);
		for (MODEL model : models) {
			for (IPluginService plugin : plugins) {
				plugin.process(model);
			}
		}
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		int updateNum = sqlSession.update(sqlId, models);
		sqlSession.close();
		return updateNum;
	}
	
	/**
	 * 
	 * @Description: 更新（单条记录）
	 * @Title: update
	 * @param sqlId
	 * @param model
	 * @return
	 * @throws Exception int
	 * @throws
	 */
	public int update(String sqlId, MODEL model) throws Exception {
		List<IPluginService> plugins = SessionUtil.getBeansOfType(IPluginService.class);
		for (IPluginService plugin : plugins) {
			plugin.process(model);
		}
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		int updateNum = sqlSession.update(sqlId, BeanTool.pojoToMap(model));
		sqlSession.close();
		return updateNum;
	}
	
	/**
	 * 
	 * @Description: 删除 （单条、批量）
	 * @Title: delete
	 * @param sqlId
	 * @param map
	 * @return
	 * @throws Exception int
	 * @throws
	 */
	public int delete(String sqlId, Map<String, Object> map) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		int deleteNum =sqlSession.delete(sqlId, map);
		sqlSession.close();
		return deleteNum;
	}
	
	/**
	 * 
	 * @Description: 删除（单条记录）
	 * @Title: delete
	 * @param sqlId
	 * @param model
	 * @return
	 * @throws Exception int
	 * @throws
	 */
	public int delete(String sqlId, MODEL model) throws Exception {
		SqlSession sqlSession = sessionFactory.openSession();
		sqlId = renovateSqlId(sqlId);
		int deleteNum =sqlSession.delete(sqlId, BeanTool.pojoToMap(model));
		sqlSession.close();
		return deleteNum;
	}
	
	/**
	 * 
	 * @Description: 调整映射sql的标识字符串，如：cn.com.guangduo.fgwpm.dao.TestDao.queryById 
	 * @Title: renovateSqlId 
	 * @param sqlId
	 * @throws Exception void
	 * @throws
	 */
	@SuppressWarnings({"rawtypes" })
	private String renovateSqlId(String sqlId) throws Exception {
		
		Field h = getBaseDao().getClass().getSuperclass().getDeclaredField("h");
		h.setAccessible(true);
		MapperProxy mapperProxy = (MapperProxy) h.get(getBaseDao());
		Field mapperInterface = mapperProxy.getClass().getDeclaredField("mapperInterface");
		mapperInterface.setAccessible(true);
		Object obj = mapperInterface.get(mapperProxy);
		Field declaredField = obj.getClass().getDeclaredField("name");
		declaredField.setAccessible(true);
		String namespace = (String) declaredField.get(obj);
		return namespace + "." + sqlId;
	}
	
	// *************************************END**************END**************END**************END******************************************//
}
