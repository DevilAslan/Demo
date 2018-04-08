package cn.umbrella.commons.utils.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 工作薄的一些设置
 * @author zhang.xiaolei
 * @createDate 2015年5月27日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnExcelSheet {
	/**
	 * 工作薄索引
	 * @defalut  "工作表{i}"
	 */
	String name() default "";//工作薄名称
	/**
	 * 工作薄索引
	 * @defalut  0
	 */
	int sheetIndex() default 0;
	/**
	 * 标题所在行号
	 * @defalut  0
	 */
	int titleNum() default 0;
	/**
	 * 忽略一些没有必要的异常，一般为对值进行类型转换时候会出现<br/>
	 * 一旦出现异常，程序不会中断，相应字段的值会以默认值得方式被赋予<br/>
	 * @defalut  false
	 */
	boolean ignoreException() default false;
}
