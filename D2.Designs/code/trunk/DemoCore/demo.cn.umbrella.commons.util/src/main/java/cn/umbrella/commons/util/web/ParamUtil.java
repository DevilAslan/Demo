package cn.umbrella.commons.util.web;

import java.util.HashMap;
import java.util.Map;

import cn.umbrella.commons.config.AccessConfig;
import cn.umbrella.commons.config.CryptConfig;
import cn.umbrella.commons.util.base.StringUtil;
import cn.umbrella.commons.util.crypt.DESede;

public class ParamUtil {
	
	public static final String CALLBACK_KEY = "callback";
	
	public static Map<String, String> getPostParaDe(String content,
			String enContent, String callback, String keyCode) {
		if (!StringUtil.isEmpty(keyCode))
			keyCode = CryptConfig.DESEDE;
		if (!DeTest(content, enContent, keyCode))
			return null;
		Map<String, String> paras = new HashMap<String, String>();
		try {
			if (!StringUtil.isEmpty(content)) {
				if (content.indexOf(";") != -1) {
					String[] rs = content.split(";");
					for (int i = 0; i < rs.length; i++) {
						if (rs[i].indexOf("=") == -1) {
							continue;
						}
						String[] temp = rs[i].split("=");
						if (temp.length > 1) {
							paras.put(temp[0], temp[1]);
						}
					}
				} else {
					if (content.indexOf("=") != -1) {
						String[] temp = content.split("=");
						if (temp.length > 1) {
							paras.put(temp[0], temp[1]);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paras;
	}
	
	public static boolean DeTest(String content, String enContent,
			String keyCode) {
		String decryptText = enContent + "";
		String deContent = null;
		if (CryptConfig.DESEDE.equals(keyCode)) {
			String decryptKey = "2012PinganVitality075522628888ForShenZhenBelter075561869839";// 分配得到
			deContent = DESede.decryptMode(decryptKey, decryptText);
			System.out.println("content:" + content);
			System.out.println("deContent:" + deContent);
			if (content.equals(deContent))
				return true;
		}
		return false;
	}
	
	public static String contactCallBackString(Map<String, String> paras,
			String result) {
		try {
			StringBuffer rt = new StringBuffer();
			if (null == paras || paras.isEmpty()) {
				rt.append("(");
			} else {
				rt.append((paras.get(CALLBACK_KEY))).append("(");
			}
			rt.append(result).append(")");
			return rt.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AccessUtil.getAccessJson("", AccessConfig.RESULT_FAIL_CODE,
				AccessConfig.RESULT_FAIL_MSG);
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 * @param content
	 * @param ticket
	 * @param callback
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getPara(String content, String callback) {
		Map<String, String> paras = new HashMap<String, String>();
		try {
			if (!StringUtil.isEmpty(content)) {
				paras = StringUtil.strToMap(content, "=", ";");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paras;
	}
}
