package cn.umbrella.commons.util.base;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

/**
 * @author:Shaw
 * @date:2017年3月28日
 * @备注：集合工具类
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
}
