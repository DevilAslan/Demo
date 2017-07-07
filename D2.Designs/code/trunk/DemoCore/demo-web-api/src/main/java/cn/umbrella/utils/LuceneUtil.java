package cn.umbrella.utils;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/**
 * @author:saker
 * @date:2017年3月24日
 * @备注：索引工具类
 */
public class LuceneUtil {
	/**
	 * 用索引查询的结果进行分页
	 */
	public static PageInfo<Map<String, Object>> queryPage(
			List<Map<String, Object>> list, int pageNumber, int pageSize) {
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>();
		int total = list.size();

		pageInfo.setTotal(total);// 总记录数
		pageInfo.setPageNum(pageNumber);// 当前页数
		pageInfo.setPageSize(pageSize);// 当前每页显示数
		int subStart = (pageNumber - 1) * pageSize;// 分页开始记录数
		int subEnd = pageNumber * pageSize;// 分页结束记录数
		if (total > subEnd) {
			pageInfo.setList(list.subList(subStart, subEnd));
		} else {
			pageInfo.setList(list.subList(subStart, total));
		}
		return pageInfo;
	}
}
