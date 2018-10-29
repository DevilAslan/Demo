package cn.umbrella.commons.validate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target({FIELD})
@Retention(RUNTIME)
public @interface AnnExtist {
	String url() ;
	String message() default "已经存在";
	//逗号分隔的name的名字
	String params() default "";
	//什么条件下才进行远程验证，可以是一端代码 ($("[name=methed]").val()=="add")
	String disable() default "";
}
