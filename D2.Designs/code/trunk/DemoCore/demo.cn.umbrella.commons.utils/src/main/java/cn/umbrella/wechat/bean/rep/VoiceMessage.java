/**
 * 
 */
package cn.umbrella.wechat.bean.rep;

/**
 * 语音消息实体类
 * @author chu.feifei
 *
 */
public class VoiceMessage extends BaseMessage {
	// 通过素材管理接口上传多媒体文件，得到的id
	private String MediaId;

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return MediaId;
	}

	/**
	 * @param mediaId
	 *            the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
