package cn.umbrella.commons.validate.parse;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.constraints.Email;

import cn.umbrella.commons.validate.bean.Validate;

/**
 * Created by ZXL on 2015/11/9.
 */
public class EmailParse implements IValidateParse{

    @Override
    public void parse(Validate validate, Field field, Annotation anno) {
        Email v = (Email) anno;

        validate.add(field.getName(),"email",true,v.message());
    }
}
