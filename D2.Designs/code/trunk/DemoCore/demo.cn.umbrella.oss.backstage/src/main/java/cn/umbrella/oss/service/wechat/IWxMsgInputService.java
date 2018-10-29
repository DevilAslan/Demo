package cn.umbrella.oss.service.wechat;

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.oss.wechat.bean.WxMsgInput;

import com.github.pagehelper.PageInfo;


public interface IWxMsgInputService {
	// 扩展接口
	
	void insert(WxMsgInput wxMsgInput);

	PageInfo<WxMsgInput> queryPage(BaseQuery query);

	int update(WxMsgInput wxMsgInput);
}