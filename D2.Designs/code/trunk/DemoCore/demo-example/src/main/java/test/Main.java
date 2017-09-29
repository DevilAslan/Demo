package test;


import java.util.ArrayList;
import java.util.List;


public class Main {
	private void mian() {
		List<String> good = new ArrayList<String>();// 守信
		List<String> bad = new ArrayList<String>();// 失信
		List<String> other = new ArrayList<String>();// 其他
		CreditCategoryUtil.getEnterpriseCategories(good, bad, other, null);
		System.out.println(good.toString());
	} 
}	
