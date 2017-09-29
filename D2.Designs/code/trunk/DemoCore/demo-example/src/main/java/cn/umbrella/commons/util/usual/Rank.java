package cn.umbrella.commons.util.usual;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rank {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, List> getRankMap(String key,Map<String, List> rank,Map<String, Object> temp){
		//key 存在判断,null赋值
		if(!rank.containsKey(key)){
			List rankList = new ArrayList<Object>();
			rankList.add(temp);
			rank.put(key, rankList);
			return rank;
		}
		
		//val 值比较
		Map<String, Object> item = (Map<String, Object>) rank.get(key).get(0);
		Double newVal = Double.valueOf(temp.get(key).toString());
		Double oldVal = Double.valueOf(item.get(key).toString());
		int retval =  newVal.compareTo(oldVal);
		if(retval > 0){
			List rankList = new ArrayList<Object>();
			rankList.add(temp);
			rank.put(key, rankList);
		}else if(retval < 0){
		}else{
			List rankList = rank.get(key);
			rankList.add(temp);
			rank.put(key, rankList);
		}
		return rank;
	}
}
