package cn.umbrella.oss.model.wechat;

public class News {
	
	private String title;//标题
	private String thumb_media_id;//素材ID
	private String author;//作者
	private String digest;//描述
	private boolean show_cover_pic;
	private String content;//内容
	private String content_source_url;//图文消息被点击后跳转的链接
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public boolean isShow_cover_pic() {
		return show_cover_pic;
	}
	public void setShow_cover_pic(boolean show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_source_url() {
		return content_source_url;
	}
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
	
}
