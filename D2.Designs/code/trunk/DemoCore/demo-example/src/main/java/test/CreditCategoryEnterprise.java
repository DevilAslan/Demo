package test;

/**
 * 
 * 信用信息类别-企业 
 *
 * @ClassName: CreditCategoryEnterprise  
 * @author zhou.xy
 * @date 2016年7月27日 下午2:22:09  
 *
 */
public enum CreditCategoryEnterprise implements IGDCommonEnum<Integer> {
	//一位 1个人 2企业  
	//两位  00基础、01良好、02不良、09其他
	QUALIFICATION_INFO(20901, "资质信息"),
	HONOR_INFO(20102, "荣誉信息"),
	OWE_INFO(20203, "欠费信息"),
	ADMINISTRATIVE_PUNISHMENT_INFO(20204, "行政处罚信息"),
	EFFECTIVE_JUDGMENT_INFO(20205, "法院判决信息"),
	YEARLY_INSPECTION(20906, "年检信息"),
	QUALITY_INSPECTION(20907, "抽查、检查信息"),
	PAY_TAXES(20208, "欠税信息"),
	INVESTOR(20909, "出资人、投资者信息"),
	TAX_INFORMATION(20910, "纳税信息"),
	CLASSIFIED_REGULATION(20911, "分类监管"),
	CHANGED_INFORMATION(20912, "变更信息"),
	BAD_BEHAVIOR_INFO(20213, "不良行为信息"),
	GOOD_BEHAVIOR_INFO(20114, "良好行为信息"),
	BE_OVERDUE(20215, "逾期信息"),
	BREACH_OF_CONTRACT(20216, "违约信息"),
	INTELLECTUAL_PROPERTY_INFO(20917, "知识产权信息"),
	DONATE_INFORMATION(20118, "捐赠信息"),
	SOCIAL_SECURITY_DEPOSIT_INFO(20919, "社保缴存"),
	KEY_PERSONNEL(20920, "主要人员信息"),
	BLACKLIST(20221, "黑名单"),
	ACCIDENT_INFORMATION(20222, "事故信息"),
	PROJECT_INFORMATION(20923, "项目信息"),
	AFFILIATED_AGENCY(20924, "分支机构"),
	ACCUMULATION_FUND(20925, "公积金缴存");

	
	private int value;
	private String name;

	private CreditCategoryEnterprise(int value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public static String getNameByValue(Integer value) {
		for (IGDCommonEnum<Integer> item : values()) {
			if (item.getValue().equals(value)) {
				return item.getName();
			}
		}
		return null;
	}

	public static Integer getValueByName(String name) {
		for (IGDCommonEnum<Integer> item : values()) {
			if (item.getName().equals(name)) {
				return (Integer) item.getValue();
			}
		}
		return null;
	}

	public static Integer getValueByItemName(String itemName) {
		Integer res = null;
		try {
			res = valueOf(itemName).getValue();
		} catch (IllegalArgumentException | NullPointerException e) {
		}
		return res;
	}
}
