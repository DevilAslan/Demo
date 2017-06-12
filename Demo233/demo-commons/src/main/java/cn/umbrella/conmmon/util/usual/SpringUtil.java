package cn.umbrella.conmmon.util.usual;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//import com.hx.llzq.service.LlzqService;

public class SpringUtil {

	private static ApplicationContext context;// spring 上下文

	/**
	 * 得到spring容器中的对象
	 * 
	 * @param id
	 *            spring中对象的id
	 * @return Object
	 */
	public static Object getBean(String id) {
		return context.getBean(id);
	}

	/**
	 * 初始化
	 * 
	 * @param sContext
	 */
	public static void setSC(ServletContext Context) {
		context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(sContext);
	}

	/**
	 * 获取 spring 上下文
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static void setApplicationContext(ApplicationContext ctx) {
		if (context == null)
			context = ctx;
	}

	public static void setApplicationContext(String[] paths) {
		if (context == null) {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
			context = ctx;
		}
	}

	// public static void main(String[] args){
	//
	// String[] paths = { "classpath:/WEB-INF/applicationContext-*.xml" };
	// SpringUtil.setApplicationContext(paths);
	// LlzqService llzqService = (LlzqService)SpringUtil.getBean("llzqService");
	// Map<String,String> map = new HashMap<String,String>();
	// map.put("sender", "18963603527");
	// map.put("accNbr", "13337707725");
	// map.put("offerSpecl", "300509010222");
	// map.put("family", "2");
	// map.put("goodName", "测试");
	//
	// try {
	// llzqService.tuhaoSendLl(map);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// System.out.println("aaa");
	// }
}