package cn.umbrella.commons.validate.parse;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.constraints.Length;

import cn.umbrella.commons.validate.bean.Validate;

/**
 * Created by ZXL on 2015/11/9.
 */
public class LengthParse implements IValidateParse {

    @Override
    public void parse(Validate validate, Field field, Annotation anno) {
        Length v = (Length) anno;
        validate.add(field.getName(), "rangelength", new int[]{v.min(), v.max()}, v.message());
    }

}
