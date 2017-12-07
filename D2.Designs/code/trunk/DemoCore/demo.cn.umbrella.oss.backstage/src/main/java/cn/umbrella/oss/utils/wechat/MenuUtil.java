package cn.umbrella.oss.utils.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import cn.umbrella.oss.wechat.bean.WxMenu;

import com.alibaba.fastjson.JSON;

public class MenuUtil {
	
	/**
	 * 获取微信公众账号的菜单
	 * @param menus	菜单列表
	 * @param matchrule	个性化菜单配置
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	public static String prepareMenus(List<WxMenu> menus) {
		if(!CollectionUtils.isEmpty(menus)){
			Map<String, Object> map = new HashMap<>();
			List<Object> menulists = new ArrayList<>();
			for(WxMenu menu : menus){
				if(menu.getMenuPid().equals("0")){//一级菜单
					Map<String, Object> menuMap = new HashMap<>();
					menuMap.put("pMenu", menu);
					menulists.add(menuMap);
				}
			}
			
			for (Object object : menulists) {
				Map<String, Object> maps = (Map<String, Object>) object;
				List<WxMenu> cMenu = new ArrayList<WxMenu>();
				WxMenu pMenu = (WxMenu) maps.get("pMenu");
				for(WxMenu menu : menus){
					Boolean bool = menu.getMenuPid().equals(pMenu.getMenuId()+"");
					System.out.println(bool);
					if(menu.getMenuPid().equals(pMenu.getMenuId()+"")){
						cMenu.add(menu);
					}
				}
				maps.put("cMenu", cMenu);
			}
			
			
			Map<String, Object> jsonMap = new HashMap<>();
			List<Object> jsonMapList = new ArrayList<>();
			for (Object object : menulists) {
				Map<String, Object> maps = (Map<String, Object>) object;
				WxMenu pMenu = (WxMenu) maps.get("pMenu");
				List<WxMenu> cMenu = (List<WxMenu>) maps.get("cMenu");
				
				if(cMenu.size() > 0){
					List<Map<String, Object>> mapList = new ArrayList<>();
					for (WxMenu wxMenu : cMenu) {
						mapList.add(getMenuMap(wxMenu));
					}
					jsonMapList.add(getParentMenuMap(pMenu, mapList));
				}else{
					jsonMapList.add(getMenuMap(pMenu));
				}
			}
			jsonMap.put("button", jsonMapList);
			
			return JSON.toJSONString(jsonMap);
		}
		return "error";
	}
	
	
	/**
	 * 此方法是构建菜单对象的；构建菜单时，对于  key 的值可以任意定义；
	 * 当用户点击菜单时，会把key传递回来；对已处理就可以了
	 * @param menu
	 * @return
	 */
	private static Map<String, Object> getMenuMap(WxMenu menu){
		Map<String, Object> map = new HashMap<>();
		map.put("name", menu.getMenuName());
		map.put("type", menu.getMenuType());
		if("click".equals(menu.getMenuType())){//事件菜单
			if("fix".equals(menu.getEventType())){//fix 消息
				map.put("key", "_fix_" + menu.getMsgId());//以 _fix_ 开头
			}else{
				if(StringUtils.isEmpty(menu.getInputCode())){//如果inputcode 为空，默认设置为 subscribe，以免创建菜单失败
					map.put("key", "subscribe");
				}else{
					map.put("key", menu.getInputCode());
				}
			}
		}else{//链接菜单-view
			map.put("url", menu.getUrl());
		}
		return map;
	}
	
	private static Map<String, Object> getParentMenuMap(WxMenu menu,List<Map<String, Object>> mapList){
		Map<String, Object> map = new HashMap<>();
		map.put("name", menu.getMenuName());
		map.put("sub_button", mapList);
		return map;
	}
    
}
