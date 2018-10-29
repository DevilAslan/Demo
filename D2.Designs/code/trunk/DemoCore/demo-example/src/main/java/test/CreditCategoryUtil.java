package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 
 * 信用分类工具类  
 *
 * @ClassName: CreditCategoryUtil  
 * @author zhou.xy
 * @date 2017年3月8日 上午9:33:25  
 *
 */
public class CreditCategoryUtil {
	/**
	 * 获取企业不良分类 
	 *
	 * @Title: getBadCategory 
	 * @return List<Integer>
	 */
	public static List<Integer> getEnterpriseBadCategory() {
		List<Integer> categories = new ArrayList<Integer>();
		for(CreditCategoryEnterprise cp : CreditCategoryEnterprise.values()) {
			Integer value = cp.getValue();
			String category = Objects.toString(value, "");
			if (category.startsWith("202")) {
				categories.add(value);
			}
		}
		return categories;
	}
	
	/**
	 * 法人（其他组织）信用分类归类  
	 *
	 * @Title: getEnterpriseCategories 
	 * @param goodCreditCategories
	 * @param badCreditCategories
	 * @param otherCreditCategories
	 * @param bothThreeCreditCategories 
	 * @return void
	 */
	public static void getEnterpriseCategories(List<String> goodCreditCategories, List<String> badCreditCategories, 
			List<String> otherCreditCategories, List<String> bothThreeCreditCategories) {
		if (null == bothThreeCreditCategories) {
			bothThreeCreditCategories = new ArrayList<String>();
		}
		for (CreditCategoryEnterprise category : CreditCategoryEnterprise.values()) {
			Integer categoryValue = category.getValue();
			String value = Objects.toString(categoryValue);
			char c = value.charAt(2);
			if (Objects.equals(c, '9')) {// 其他
				otherCreditCategories.add(value);
				bothThreeCreditCategories.add(value);
			} else if (Objects.equals(c, '2')) {// 不良
				badCreditCategories.add(value);
				bothThreeCreditCategories.add(value);
			} else if (Objects.equals(c, '1')) {// 良好
				goodCreditCategories.add(value);
				bothThreeCreditCategories.add(value);
			}
		}
	}
}
