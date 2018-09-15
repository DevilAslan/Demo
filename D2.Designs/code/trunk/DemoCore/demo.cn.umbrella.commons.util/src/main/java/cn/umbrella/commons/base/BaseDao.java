package cn.umbrella.commons.base;

import java.util.List;
import java.util.Map;
/**
 * 
 * @Description: 定义一些基础的数据库操作  
 * @ClassName: BaseDao  
 * @author zhou.xy
 * @date 2015年12月24日 下午5:40:14  
 *  
 * @param <Model>
 * @param <KEY_TYPE>
 */
public interface BaseDao<Model, KEY_TYPE> {
	int insertOne(Model model);
	int insertBatch(List<Model> models);
	int update(Model model);
	int updateBatch(List<Model> models);
	int delete(KEY_TYPE id);
	int deleteBatch(List<KEY_TYPE> ids);
	Model queryById(KEY_TYPE id);
	List<Model> query(Map<String, Object> map);
}
