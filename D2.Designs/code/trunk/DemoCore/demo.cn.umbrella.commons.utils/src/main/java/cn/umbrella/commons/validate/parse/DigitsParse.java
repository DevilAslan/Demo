package cn.umbrella.commons.validate.parse;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.validation.constraints.Digits;

import cn.umbrella.commons.validate.bean.Validate;

/**
 * Created by ZXL on 2015/11/9.
 */
public class DigitsParse implements IValidateParse {

    @Override
    public void parse(Validate validate, Field field, Annotation anno) {
    	Digits v = (Digits) anno;
        validate.add(field.getName(), "decimal", new int[]{v.integer(), v.fraction()}, v.message());
    }

}
