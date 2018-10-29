package cn.umbrella.commons.validate.parse;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.validation.constraints.Min;

import cn.umbrella.commons.validate.bean.Validate;

/**
 * Created by ZXL on 2015/11/9.
 */
public class MinParse implements IValidateParse {

    @Override
    public void parse(Validate validate, Field field, Annotation anno) {
        Min v = (Min) anno;
        validate.add(field.getName(), "min", v.value(), v.message());
    }

}
