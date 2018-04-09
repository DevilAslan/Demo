package cn.umbrella.commons.enums;

/*
 * 是否锁定
 */
public enum IsLocked implements ICommonEnum<Integer> {
	// 1.是，0否
	DISABLE(0, "否"), 
	ENABLE(1, " 是");

	private Integer key;
	private String value;

	private IsLocked(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Integer getKey() {
		return this.key;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
}
