package cn.umbrella.oss.wechat.form;

/**
 * 素材类型的枚举类
 * @author zx
 */
public enum MaterialType {
	IMAGE("image"), NEWS("news"),MPNEWS("mpnews"), TEXT("text"),THUMB("thumb"),IMAGE_OR_THUMB("imageOrThumb");
	private final String type;
	private MaterialType(String type) {
		this.type = type;
	}
	public String getType(){
		return type;
	}
}
