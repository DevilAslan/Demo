package cn.umbrella.oss.utils.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.umbrella.oss.model.wechat.TextMessage;
import cn.umbrella.oss.wechat.bean.TwxcmsMsgText;
import cn.umbrella.oss.wechat.form.Material;
import cn.umbrella.oss.wechat.form.MaterialArticle;
import cn.umbrella.oss.wechat.form.MaterialItem;
import cn.umbrella.wechat.bean.WXInputMessage;
import cn.umbrella.wechat.bean.rep.Article;
import cn.umbrella.wechat.bean.rep.NewsMessage;
import cn.umbrella.wechat.util.MessageUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 消息builder工具类
 */
public class WxMessageBuilder {
	
	//获取 MsgResponseText 对象
	public static TextMessage getMsgResponseText(WXInputMessage msgRequest,TwxcmsMsgText msgText){
		if(msgText != null){
			TextMessage reponseText = new TextMessage();
			reponseText.setToUserName(msgRequest.getFromUserName());
			reponseText.setFromUserName(msgRequest.getToUserName());
			reponseText.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			reponseText.setCreateTime(new Date().getTime());
			reponseText.setContent(msgText.getContent());
			return reponseText;
		}else{
			return null;
		}
	}
	
	//获取 MsgResponseNews 对象
	public static NewsMessage getMsgResponseNews(WXInputMessage msgRequest,TwxcmsMsgText text,JSONObject imageSrcMap){
		if(text != null){
			NewsMessage responseNews = new NewsMessage();
			responseNews.setToUserName(msgRequest.getFromUserName());
			responseNews.setFromUserName(msgRequest.getToUserName());
			responseNews.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			responseNews.setCreateTime(new Date().getTime());
			String content = text.getContent();
			JSONObject jsonObject = JSONObject.parseObject(content);
			JSONArray arr = jsonObject.getJSONArray("articles");
			int articleSize = arr.size();
			responseNews.setArticleCount(articleSize);
			List<Article> articles = new ArrayList<Article>(articleSize);
			for (int i = 0; i < arr.size(); i++) {
				JSONObject article = arr.getJSONObject(i);
				Article a = new Article();
				a.setTitle(article.getString("title"));
				a.setDescription(article.getString("description"));
				a.setPicUrl(imageSrcMap.getString(article.getString("thumb_media_id")));//图片需要指定
				a.setUrl(article.getString("content_source_url"));
				articles.add(a);
			}
			responseNews.setArticles(articles);
			return responseNews;
		}else{
			return null;
		}
	}
	
	public static List<MaterialArticle> getMsgResponseNewsToList(String content,JSONObject imageSrcMap){
		List<MaterialArticle> newsItems = new ArrayList<MaterialArticle>();
		String imageSuffix = "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=";
		if(content!=null && content.length()>0){
			JSONObject jsonObj = JSONObject.parseObject(content);
			JSONArray arr = jsonObj.getJSONArray("articles");
			if(arr != null && arr.size() > 0){
				for (int i = 0; i < arr.size(); i++) {
					JSONObject article = arr.getJSONObject(i);
					MaterialArticle ma = new MaterialArticle();
					ma.setTitle(article.getString("title"));
					ma.setThumb_media_id(article.getString("thumb_media_id"));
					ma.setShow_cover_pic(article.getString("show_cover_pic"));
					ma.setAuthor(article.getString("author"));
					ma.setContent_source_url(article.getString("content_source_url"));
					ma.setContent(article.getString("content"));
					ma.setUrl(article.getString("url"));
					if(imageSrcMap.getString(article.getString("thumb_media_id"))!=null){
						String pic = imageSrcMap.getString(article.getString("thumb_media_id"))!=null?imageSrcMap.getString(article.getString("thumb_media_id")):"";
						ma.setThumb_media_src(imageSuffix+pic);
					}else{
						ma.setThumb_media_src("");
					}
					newsItems.add(ma);
				}
			}
		}
		return newsItems;
	}
	
	public static Material getMsgResponseNews(String msgJsonNews){
		if(msgJsonNews!=null && msgJsonNews.length()>0){
			JSONObject jsonObj = JSONObject.parseObject(msgJsonNews);
			
			Material material = new Material();
			material.setTotalCount(jsonObj.getInteger("total_count"));
			material.setItemCount(jsonObj.getInteger("item_count"));
			JSONArray arr = jsonObj.getJSONArray("item");
			if(arr != null && arr.size() > 0){
				List<MaterialItem> itemList = new ArrayList<MaterialItem>();
				for(int i = 0; i < arr.size(); i++){
					JSONObject item = arr.getJSONObject(i);
					MaterialItem materialItem = new MaterialItem();
					materialItem.setMediaId(item.getString("media_id"));
					materialItem.setUpdateTime(item.getLong("update_time")*1000L);
					if(item.containsKey("content")){//mediaType=news （图文消息）
						JSONArray articles = item.getJSONObject("content").getJSONArray("news_item");
						List<MaterialArticle> newsItems = new ArrayList<MaterialArticle>();
						for(int j = 0; j < articles.size(); j++){
							JSONObject article = articles.getJSONObject(j);
							MaterialArticle ma = new MaterialArticle();
							ma.setTitle(article.getString("title"));
							ma.setThumb_media_id(article.getString("thumb_media_id"));
							ma.setShow_cover_pic(article.getString("show_cover_pic"));
							ma.setAuthor(article.getString("author"));
							ma.setContent_source_url(article.getString("content_source_url"));
							ma.setContent(article.getString("content"));
							ma.setUrl(article.getString("url"));
							newsItems.add(ma);
						}
						materialItem.setNewsItems(newsItems);
					}else{
						materialItem.setName(item.getString("name"));
						materialItem.setUrl(item.getString("url"));
					}
					itemList.add(materialItem);
				}
				material.setItems(itemList);
			}
			
			return material;
		}
		return null;
	}

}
