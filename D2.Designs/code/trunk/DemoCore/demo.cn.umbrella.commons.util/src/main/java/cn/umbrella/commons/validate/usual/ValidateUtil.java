package cn.umbrella.commons.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import cn.umbrella.commons.validate.bean.Validate;
import cn.umbrella.commons.validate.parse.DigitsParse;
import cn.umbrella.commons.validate.parse.EmailParse;
import cn.umbrella.commons.validate.parse.ExtistParse;
import cn.umbrella.commons.validate.parse.IValidateParse;
import cn.umbrella.commons.validate.parse.LengthParse;
import cn.umbrella.commons.validate.parse.MaxParse;
import cn.umbrella.commons.validate.parse.MinParse;
import cn.umbrella.commons.validate.parse.NotEmptyParse;
import cn.umbrella.commons.validate.parse.NotNullParse;
import cn.umbrella.commons.validate.parse.PatternParse;
import cn.umbrella.commons.validate.parse.RangeParse;
import cn.umbrella.commons.validate.parse.SizeParse;

/**
 * Created by ZXL on 2015/11/9.
 */
public class ValidateUtil {
    private static Map<String, IValidateParse> parse = null;
    static {
        initParse();
    }
    private static void initParse() {
        if (parse == null) {
            parse = new HashMap<String, IValidateParse>();

            parse.put(Email.class.getName(), new EmailParse());
            parse.put(Length.class.getName(), new LengthParse());
            parse.put(Max.class.getName(), new MaxParse());
            parse.put(Min.class.getName(), new MinParse());
            parse.put(NotEmpty.class.getName(), new NotEmptyParse());
            parse.put(Pattern.class.getName(), new PatternParse());
            parse.put(Range.class.getName(), new RangeParse());
            parse.put(Size.class.getName(), new SizeParse());
            parse.put(NotNull.class.getName(), new NotNullParse());
            parse.put(Digits.class.getName(), new DigitsParse());
            parse.put(AnnExtist.class.getName(), new ExtistParse());
        }
    }
    
    public static Validate getValidate(Class<?> formClazz) {
        Validate validate = new Validate();
        for (Field field : formClazz.getDeclaredFields()) {
            Annotation[] fAnn = field.getAnnotations();
            if (fAnn.length > 0) {
                for (Annotation ann : fAnn) {
                    IValidateParse parseObj = parse.get(ann.annotationType().getName());
                    if (parseObj != null) {
                        parseObj.parse(validate, field, ann);
                    }
                }
            }
        }

        return validate;
    }

    public static void main(String[] args) {
//        System.out.println(ValidateUtil.getValidate(BbsEntryForm.class));
    }
    
    /**
	 * 验证表单是否重复提交 
	 *
	 * @Title: isRepeatSubmit 
	 * @param client_token
	 * @param server_token
	 * @return boolean
	 */
	public static boolean isRepeatSubmit(String client_token, String server_token) {
		// 1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
		if (client_token == null) {
			return true;
		}
		// 取出存储在Session中的token
		// 2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
		if (server_token == null) {
			return true;
		}
		// 3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
		if (!client_token.equals(server_token)) {
			return true;
		}

		return false;
	}
}