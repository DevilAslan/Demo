package cn.umbrella.commons.validate.parse;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.validation.constraints.Pattern;

import cn.umbrella.commons.validate.bean.Validate;

/**
 * Created by ZXL on 2015/11/9.
 */
public class PatternParse implements IValidateParse {

    @Override
    public void parse(Validate validate, Field field, Annotation anno) {
        Pattern v = (Pattern) anno;
        validate.add(field.getName(), "regexp", v.regexp(), v.message());
    }

}
