package cn.umbrella.commons.validate.parse;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import cn.umbrella.commons.validate.AnnExtist;
import cn.umbrella.commons.validate.bean.Validate;

/**
 * Created by ZXL on 2015/11/9.
 */
public class ExtistParse implements IValidateParse {

    @Override
    public void parse(Validate validate, Field field, Annotation anno) {
    	AnnExtist v = (AnnExtist) anno;
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("url", v.url());
    	map.put("disable", v.disable());
    	map.put("params", v.params());
        validate.add(field.getName(), "extis", map, v.message());
    }

}
