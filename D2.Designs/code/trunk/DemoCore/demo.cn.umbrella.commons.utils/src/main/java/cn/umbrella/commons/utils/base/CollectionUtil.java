package cn.umbrella.commons.utils.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 
* @ClassName: CollectionUtil 
* @Description: 集合工具类
* @date 2017年3月28日
*
 */
public class CollectionUtil {
	/**
	 * 集合的所有元素是否都为空
	 * @param collection
	 */
	public static boolean isAllEmpty(Collection<String> collection){
		boolean flag = false;
		for(String str : collection){
			if(StringUtils.isNotBlank(str)){
				return true;
			}else{
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 集合去重
	 *
	 * @Title: removeDuplicateWithOrder 
	 * @param list 
	 * @return void
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		if(list != null && list.size() > 0) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (set.add(element))
					newList.add(element);
			}
			list.clear();
			list.addAll(newList);
		}
	 }
}
