package cn.umbrella.oss.service.wechat;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.oss.wechat.bean.TwxcmsMsgBase;
import cn.umbrella.oss.wechat.bean.TwxcmsMsgNews;
import cn.umbrella.oss.wechat.bean.TwxcmsMsgText;
import cn.umbrella.oss.wechat.bean.WxMedia;
import cn.umbrella.oss.wechat.form.MaterialType;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;


public interface IWxMediaService  {
	// 扩展接口
	/**
	 * 通过关键字查询订阅信息/取消订阅信息
	 * @param eventTypeSubscribe
	 */
	TwxcmsMsgText getMsgTextByInputCode(String eventTypeSubscribe);

	/**
	 * 订阅消息
	 * @return TwxcmsMsgText
	 */
	TwxcmsMsgText getMsgTextBySubscribe();
	
	/**
	 * 多条图文消息
	 * @param idArr
	 * @return
	 */
	List<TwxcmsMsgNews> listMsgNewsByBaseId(String[] idArr);

	TwxcmsMsgBase getById(String baseIds);

	/**
	 * 文本消息
	 * @param baseIds
	 * @return
	 */
	TwxcmsMsgText getMsgTextByBaseId(String baseIds);
	int insert(WxMedia media);
	/**
	 * @param query 接口会根据素材的类型添加deleted条件，排序条件，不使用默认的可自己添加
	 * @param type 类型，使用枚举类
	 * @return
	 */
	PageInfo<WxMedia> queryPage(BaseQuery query, MaterialType... type);

	/**
	 * @param query 接口自动添加image类型查询条件
	 * @return mediaid 和wxMedia的对应关系，便于转换图文消息的封面图片id到图像地址的关系
	 */
	JSONObject queryImageMap(BaseQuery query);
	
	/**
	 * 添加临时素材
	 * @param medias
	 * @param wxApiUrl
	 * @param accessToken
	 * @param flag true:返回url,false:mediaId
	 * @return
	 */
	List<String> insertTmpMaterial(MultipartFile[] medias,String wxApiUrl,String accessToken,boolean flag);
	
	void deleteByMediaId(String id);
}