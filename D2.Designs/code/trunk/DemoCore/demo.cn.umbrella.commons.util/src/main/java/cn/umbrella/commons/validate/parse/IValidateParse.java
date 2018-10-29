package cn.umbrella.commons.validate.parse;



import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import cn.umbrella.commons.validate.bean.Validate;

/**
 * Created by dde on 2015/11/9.
 */
public interface IValidateParse {
    void parse(Validate validate, Field field, Annotation anno);
}
