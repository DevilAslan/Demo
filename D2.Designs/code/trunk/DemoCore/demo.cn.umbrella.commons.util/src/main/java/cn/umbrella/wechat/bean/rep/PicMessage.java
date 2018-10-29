/**
 * 
 */
package cn.umbrella.wechat.bean.rep;

/**
 * 图片消息实体类
 * 
 * @author chu.feifei
 *
 */
public class PicMessage extends BaseMessage{
	// 通过素材管理接口上传多媒体文件，得到的id
	private String Content;

	/**
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		Content = content;
	}

}
