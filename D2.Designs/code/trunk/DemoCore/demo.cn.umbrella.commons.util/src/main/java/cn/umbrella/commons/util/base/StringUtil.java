package cn.umbrella.commons.util.base;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.umbrella.commons.util.usual.BeanUtil;

/**
 * <p>Title: MES</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Umbrella</p>
 *
 * @author Shaw
 * @version 2.0
 */

/**
 * 共用字符串處理類，用於對字符串的轉換﹐替代﹐轉碼﹐截取﹐解析等常用操作
 */
public class StringUtil {
	private static final char[] LT_ENCODE = "&lt;".toCharArray();
	private static final char[] GT_ENCODE = "&gt;".toCharArray();
	private static final String EMPTY = "";

	/**
	 * 转换字符串的编码
	 */
	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
	public static final String US_ASCII = "US-ASCII";

	/** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/** 8 位 UCS 转换格式 */
	public static final String UTF_8 = "UTF-8";

	/** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
	public static final String UTF_16BE = "UTF-16BE";

	/** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
	public static final String UTF_16LE = "UTF-16LE";

	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
	public static final String UTF_16 = "UTF-16";

	/** 中文超大字符集 */
	public static final String GBK = "GBK";

	/**
	 * 構造函數
	 */
	public StringUtil() {

	}

	/**
	 * 將一個null的字符串轉換為空串，
	 * 
	 * @param str
	 *            要轉化字符串，可以為null,非null
	 * @return
	 */
	public static String getEmptyStringIfNull(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 
	 * 判別String是否為數字?
	 * 
	 * @param str
	 *            String
	 * 
	 * @return boolean
	 */

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("-?[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isEmpty(String str) {
		if (str != null && !EMPTY.equals(str.trim())
				&& !str.equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws LogicException
	 */
	public static boolean isNull(String[] strs) {
		boolean flag = true;
		for (int i = 0; i < strs.length; i++) {
			if ("".equals(strs[i]) || strs[i] == null
					|| strs[i].equalsIgnoreCase("null")) {
				flag = false;
			}
		}
		return flag;
	}

	public static String substring(String s, int length) {
		String ret = "";
		if (s != null && s.length() > 0) {
			if (s.length() > length) {
				ret = s.substring(0, length);
			} else {
				ret = s;
			}
		} else {
			ret = "";
		}
		return ret;
	}

	/**
	 * 如果輸入的字符串中包含有空格﹐將空格過濾掉﹐如果輸入null的字符串轉換為空串 使用方法：<br>
	 * String strValue=StringUtil.trimString(request.getParameter("pro_id"));
	 * 
	 * @param str
	 *            要轉化的字符串，可以為null,非null
	 * @return 非null的字符串返回去掉空格后的s﹐null的字符串返回空串
	 */
	public static String trimString(String s) {
		if ((s == null) || (s.equalsIgnoreCase("null")))
			return "";
		else
			return s.trim();
	}

	public static String trimString(Object obj) {
		if (obj == null || obj.toString().equalsIgnoreCase("null"))
			return "";
		else
			return obj.toString();
	}

	public static String trimString(Long s) {
		if (s == null || s.toString().equalsIgnoreCase("null")
				|| s.toString().equalsIgnoreCase("0"))
			return "";
		else
			return s.toString();
	}

	public static String stringToNumber(String s) {
		if (s == null || s.toString().equalsIgnoreCase("null")
				|| s.toString().equalsIgnoreCase(""))
			return "0";
		else
			return s.toString();
	}

	public static String trimString(Object object, Object defaultObject) {
		if (object == null || object.toString().equalsIgnoreCase("null")) {
			if (defaultObject == null
					|| defaultObject.toString().equalsIgnoreCase("null")) {
				return "";
			} else {
				return defaultObject.toString();
			}
		} else {
			return object.toString();
		}
	}

	/**
	 * 將字符串轉換成utf-8格式的字符串
	 * 
	 * @param s
	 *            任意字符串
	 * @return 返回utf-8的字符
	 */
	public static String coventString(String s) {
		String s1 = "";
		try {
			if (s != null) {
				s1 = new String(s.trim().getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (Exception exception) {
			s1 = "";
		}
		return s1;
	}

	/**
	 * 將數組轉為UTF-8碼
	 */
	public String[] arraysStringUTF(String as[]) {
		String as1[] = as;
		if (as == null) {
			return null;
		}
		for (int i = 0; i < as1.length; i++) {
			try {
				as1[i] = new String(as1[i].trim().getBytes("ISO-8859-1"),
						"UTF-8");
			} catch (Exception exception) {
				as1[i] = "";
			}
		}

		return as1;
	}

	/**
	 * 將字符串轉換成utf-8的編碼，轉換後的字符串類似：\u8907\u88fd\u6a21
	 * 
	 * @param s
	 *            任意字符
	 * @return 返回以utf-8編碼的字符串,類似：\u8907\u88fd\u6a21
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					// System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 字符串的解析﹐將給定的字符串按照指定的分隔符解析 使用方法：<br>
	 * String strValue=StringUtil.split("my test,split/ string"," ,/");
	 * strValue={"my","test","split","string"};
	 * 
	 * @param s
	 *            任意要解析的字符串﹐上例中的 "my test,split/ string"
	 * @param delim
	 *            字符串﹐即給定的分隔符﹐可以有多個不同的分隔符﹐上例中" ,/"﹐表示三個分隔符﹕空格﹐逗號和斜線
	 * @return 返回按照給定分隔符解析后的子串形成的數組
	 */
	public static String[] split(String s, String delim) {
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer token = new StringTokenizer(s, delim);
		while (token.hasMoreTokens()) {
			String a = token.nextToken();
			list.add(a);
		}
		String[] stringArray = new String[list.size()];
		list.toArray(stringArray);
		return stringArray;
	}

	/**
	 * 將字符串的某一部分用另外一個字符串替代,和JAVA中的replaceAll類似﹐但是replaceAll對大小寫敏感 例如字符串 cadCAm
	 * ,要將ca用test代替，替換後結果為 testdtestm 使用方法： String
	 * strValue=StringUtil.replaceIgnoreCase("cadCAm","ca","test")
	 * strValue="testdtestm" <br>
	 * <b>注意：此方法對字符的大小寫不敏感，</b><br>
	 * 
	 * @param line
	 *            字符串，如：cadCAm
	 * @param oldString
	 *            要被替換的字符，如：ca
	 * @param newString
	 *            要替換的新字符,如：test
	 * @return 返回被替換後的字符
	 */

	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 主要功能是替換HTML標簽符號，將"<"替換為"&lt;" , 將">"替換為"&gt;" 使用方法： String
	 * strValue=StringUtil.escapeHTMLTags("<br>
	 * <table>
	 * <td></td>
	 * <table>
	 * ") strValue="&lt;br&gt;&lt;table&gt;&lt;td&gt;&lt;/td&gt;&lt;table&gt;"
	 * 
	 * @param in
	 *            帶HTML標簽的字符串
	 * @return 返回被替換後的字符
	 */
	public static final String escapeHTMLTags(String in) {
		if (in == null) {
			return null;
		}
		char ch;
		int i = 0;
		int last = 0;
		char[] input = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (; i < len; i++) {
			ch = input[i];
			if (ch > '>') {
				continue;
			} else if (ch == '<') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(LT_ENCODE);
			} else if (ch == '>') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(GT_ENCODE);
			}
		}

		if (last == 0) {
			return in;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	/**
	 * 把Array 轉成String,如: '1','2','3'
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrayToSqlString(String[] arr) {
		String sqlStr = "";
		if (arr == null || arr.length == 0) {
			return "''";
		} else {
			for (int i = 0; i < arr.length; i++) {
				if (i == 0) {
					sqlStr += "'" + arr[i] + "'";
				} else {
					sqlStr += ",'" + arr[i] + "'";
				}
			}
		}
		return sqlStr;
	}

	public static String numberFomatString(int number, int decimal) {
		String str = "";
		if (String.valueOf(number).length() < decimal) {
			for (int i = 0; i < decimal - String.valueOf(number).length(); i++) {
				str = str + "0";
			}
		}
		return str + String.valueOf(number);
	}

	/**
	 * 傳入小時分鐘得到日期
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Date getDateByHoursAddMinutes(String s) throws Exception {

		SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm");
		ParsePosition parseposition = new ParsePosition(0);
		Date date = simpledateformat.parse(s, parseposition);
		return date;
	}

	/**
	 * 定義輸出整數的格式﹐將輸入整數按照指定的格式輸出﹐如果輸入整數原來的位數大于或等于要格式化的位數﹐
	 * 則返回原來輸入整數的字符串形式﹐如果不足位﹐則在前面補0 使用方法：<br>
	 * String strValue=StringUtil.numberFomat(7,4); strValue="0007";
	 * 
	 * @param int number 任意要格式化的整數
	 * @param int decimal 格式化后的整數位數
	 * @return 返回按照指定格式輸出的字符串
	 */
	public static String numberFomat(int number, int decimal) {
		String str = "";
		DecimalFormat df = new DecimalFormat();
		df.setMinimumIntegerDigits(decimal);
		str = df.format(number);
		return str;
	}

	/**
	 * 功能﹕就是傳入一個字符串﹐然后把其轉化為時間類型
	 * 
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		ParsePosition parseposition = new ParsePosition(0);
		Date date = simpledateformat.parse(str, parseposition);
		return date;
	}

	/**
	 * string 转 int[]
	 * 
	 * @param strs
	 * @return
	 */
	public static int[] strToIntArray(String strs) {
		String str[] = strs.split("\\,");
		int array[] = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			array[i] = Integer.parseInt(str[i]);
		}
		return array;
	}

	/**
	 * string 转 List
	 * 
	 * @param strs
	 * @return
	 */
	public static String[] strToStrArray(String strs) {
		String str[] = strs.split("\\,");
		String array[] = new String[str.length];
		for (int i = 0; i < str.length; i++) {
			array[i] = String.valueOf(str[i]);
		}
		return array;
	}

	/**
	 * 方法名称:transStringToMap 传入参数:mapString 形如 username'chenziwen^password'1234
	 * 返回值:Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map strToMap(String str, String equalSign,
			String splitSign) {
		Map map = new HashMap();
		java.util.StringTokenizer items;
		for (StringTokenizer entrys = new StringTokenizer(str, splitSign); entrys
				.hasMoreTokens(); map.put(items.nextToken(),
				items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))
			items = new StringTokenizer(entrys.nextToken(), equalSign);
		return map;
	}

	/**
	 * 取得系統當前日期，日期格式為:yyyy-MM-dd
	 * 
	 * @return yyyy-MM-dd樣式結果，例如：2006-09-11 使用方法：<br>
	 *         String nowDate=DateUtil.getNowDate(); nowDate="2006-09-11" ;
	 */
	public static String getNowDate() {
		java.util.Date date = new java.util.Date();

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String s = simpledateformat.format(date);
		return s;
	}

	public static String getNowYear() {
		java.util.Date date = new java.util.Date();

		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy");
		String s = simpledateformat.format(date);
		return s;
	}

	public static int strCompar(String s) {
		Date current_time = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm:ss");
		String current_time_format = simpledateformat.format(current_time);
		String current_time_hours = current_time_format.substring(0, 2);
		String current_time_min = current_time_format.substring(3, 5);
		int current_time_hours_int = Integer.parseInt(current_time_hours);
		int current_time_min_int = Integer.parseInt(current_time_min);
		String s_begin_hours = s.substring(0, 2);
		String s_end_hours = s.substring(6, 8);
		String s_begin_min = s.substring(3, 5);
		String s_end_min = s.substring(9, 11);
		int s_begin_hours_int = Integer.parseInt(s_begin_hours);
		int s_end_hours_int = Integer.parseInt(s_end_hours);
		int s_begin_min_int = Integer.parseInt(s_begin_min);
		int s_end_min_int = Integer.parseInt(s_end_min);
		if ((s_begin_hours_int == s_end_hours_int)
				&& (s_end_hours_int == current_time_hours_int)
				&& (s_end_hours_int == current_time_hours_int)) {
			if (s_begin_min_int > s_end_min_int) {
				if (current_time_min_int >= s_begin_min_int
						&& current_time_min_int <= s_end_min_int) {
					return 1;
				} else {
					return 0;
				}
			} else {
				if (current_time_min_int <= s_begin_min_int
						&& current_time_min_int >= s_end_min_int) {
					return 1;
				} else {
					return 0;
				}
			}
		} else if (s_begin_hours_int < s_end_hours_int) {

			if (current_time_hours_int > s_begin_hours_int
					&& current_time_hours_int < s_end_hours_int) {
				return 1;
			} else if (current_time_hours_int == s_begin_hours_int
					&& current_time_hours_int != s_end_hours_int) {

				if (s_begin_min_int > current_time_min_int) {
					return 0;
				} else {
					if (s_end_min_int >= current_time_min_int) {
						return 1;
					} else {
						return 0;
					}
				}
			} else if (current_time_hours_int == s_end_hours_int
					&& current_time_hours_int != s_begin_hours_int) {

				if (s_end_min_int < current_time_min_int) {
					return 0;
				} else {
					if (s_begin_min_int <= current_time_min_int) {
						return 1;
					} else {
						return 0;
					}
				}
			} else {
				return 0;
			}
		} else if (s_begin_hours_int > s_end_hours_int) {

			if (s_begin_hours_int > current_time_hours_int
					&& current_time_hours_int < s_end_hours_int) {

				return 1;
			} else if (current_time_hours_int != s_begin_hours_int
					&& current_time_hours_int == s_end_hours_int) {

				if (current_time_min_int <= s_begin_min_int) {
					return 1;
				} else {
					return 1;
				}
			} else if (current_time_hours_int == s_begin_hours_int
					&& current_time_hours_int != s_end_hours_int) {

				if (current_time_min_int <= s_end_min_int) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 0;
			}
		} else
			return 0;
	}

	/**
	 * 功能:去格式有空格的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String StringFormatTrim(String str) {
		return str.replace(" ", "");
	}

	public static String getEmptyStringIfNullToMidLine(Object str) {
		String returstr = "";
		if (str == null) {
			returstr = "";
		} else {
			returstr = str.toString();
		}
		return returstr;
	}

	/**
	 * 获取某一字符串下包含子串后第一次出现某一子串的位置以及再两者之间子串
	 * <p>
	 * 
	 * @author F1632506
	 * @date Apr 2, 2010 11:38:32 AM
	 * @param allString
	 *            type String 不能够为空，总字串
	 * @param containString
	 *            type String 不能够为空，子串
	 * @param nextString
	 *            type String 不能够为空，子串 </ul>
	 * @see package.className[#method([param])]
	 * @throws exceptionType
	 *             如果...將拋出exceptionType異常.
	 * @return returnType 返回某一字符串下包含子串后第一次出现某一子串的位置以及再两者之间子串
	 */
	public static String[] getNextPositionAtString(String allString,
			String containString, String nextString) throws Exception {
		String targetStringArray[] = new String[2];
		int position = 0;
		if (allString.indexOf(containString) < 0) {
			throw new Exception(allString + "不包含" + containString);
		} else if (allString.indexOf(nextString) < 0) {
			throw new Exception(allString + "不包含" + nextString);
		} else {
			// 去查詢总字符串中包含字符传中所在的位置
			int containStringPosition = allString.indexOf(containString);
			// 截取总字符串从这位置开始的字符串
			String cutAfterAllString = allString
					.substring(containStringPosition + containString.length());// containString.length()为了防止containString包含nextString
			if (cutAfterAllString.indexOf(nextString) < 0) {
				throw new Exception(allString + "中不存在" + containString + "后的"
						+ nextString);
			} else {
				position = cutAfterAllString.indexOf(nextString);
				String targetString = cutAfterAllString.substring(0, position);

				if (position < 0) {
					throw new Exception(allString + "包含" + containString
							+ "后不存在包含" + nextString);
				}
				position = containStringPosition + containString.length()
						+ position;
				targetStringArray[0] = String.valueOf(position);
				targetStringArray[1] = targetString;
			}

		}
		return targetStringArray;
	}

	/**
	 * 去字串中尋找含某個字符的個數
	 * <p>
	 * 
	 * @author F1632506
	 * @date Nov 25, 2009 1:51:47 PM
	 * @param param1
	 *            type 描述
	 * @param param2
	 *            type 描述
	 *            <ul>
	 *            <li>value1描述 <li>value2描述
	 *            </ul>
	 * @see package.className[#method([param])]
	 * @throws exceptionType
	 *             如果...將拋出exceptionType異常.
	 * @return returnType 返回類型描述
	 */
	public static int strContainNumber(String str, char subStr) {
		char[] charStr = str.toCharArray();
		int a = 0;
		for (int i = 0; i < charStr.length; i++) {
			if (charStr[i] == subStr) {
				a++;
			}
		}
		return a;
	}

	/**
	 * 如果輸入null的字符串轉換為空串,字符串中間包含有空格將保留﹐ 使用方法：<br>
	 * String strValue=StringUtil.trimString(request.getParameter("info"));
	 * 
	 * @param str
	 *            要轉化的字符串，可以為null,非null
	 * @return null的字符串返回空串
	 */

	public static String trimStringStr(String str) {
		if ((str == null) || (str.equalsIgnoreCase("null")))
			return "";
		else
			return str.trim();
	}

	public static String trimStringStr(Object obj) {
		if (obj == null || obj.toString().equalsIgnoreCase("null"))
			return "";
		else
			return obj.toString().trim();
	}

	/**
	 * 將字符串轉換為long,如果為"",轉換為0
	 * <p>
	 * 
	 * @author F1041724
	 * @date Oct 9, 2010 11:56:44 AM
	 */
	public static long stringToLong(String str) {
		long l = 0;
		if (!"NULL".equalsIgnoreCase(str) && str != null && !"".equals(str)) {
			l = Long.parseLong(str);
		} else {
			l = 0;
		}
		return l;
	}

	/**
	 * 將字符串轉換為int,如果為"",轉換為0
	 * <p>
	 * 
	 * @author F1041724
	 * @date Oct 9, 2010 11:56:44 AM
	 */
	public static int stringToInt(String str) {
		int i = 0;
		if (!"NULL".equalsIgnoreCase(str) && str != null && !"".equals(str)) {
			i = Integer.parseInt(str);
		} else {
			i = 0;
		}
		return i;
	}

	/**
	 * 方法描述
	 * <p>
	 * 將字符串轉換為double,如果為"",轉換為0
	 * 
	 * @author F1041724
	 * @date Oct 25, 2010 9:38:48 AM
	 */
	public static double stringToDouble(String str) {
		double d = 0;
		if (!"NULL".equalsIgnoreCase(str) && str != null && !"".equals(str)) {
			d = Double.parseDouble(str);
		} else {
			d = 0;
		}
		return d;
	}

	public static Object trimObject(Object obj) {
		if ((obj == null) || (obj.toString().equalsIgnoreCase("null")))
			return "";
		else
			return obj;
	}

	/**
	 * 将字符编码转换成US-ASCII码
	 */
	@SuppressWarnings("static-access")
	public String toASCII(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, US_ASCII);
	}

	/**
	 * 将字符编码转换成ISO-8859-1码
	 */
	@SuppressWarnings("static-access")
	public String toISO_8859_1(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, ISO_8859_1);
	}

	/**
	 * 将字符编码转换成UTF-8码
	 */
	@SuppressWarnings("static-access")
	public String toUTF_8(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, UTF_8);
	}

	/**
	 * 将字符编码转换成UTF-16BE码
	 */
	@SuppressWarnings("static-access")
	public String toUTF_16BE(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, UTF_16BE);
	}

	/**
	 * 将字符编码转换成UTF-16LE码
	 */
	@SuppressWarnings("static-access")
	public String toUTF_16LE(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, UTF_16LE);
	}

	/**
	 * 将字符编码转换成UTF-16码
	 */
	@SuppressWarnings("static-access")
	public String toUTF_16(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, UTF_16);
	}

	/**
	 * 将字符编码转换成GBK码
	 */
	@SuppressWarnings("static-access")
	public String toGBK(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, GBK);
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 解析 Unicode
	 * 
	 * @param ori
	 * @return
	 */
	public static String convertUnicode(String ori) {
		char aChar;
		int len = ori.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = ori.charAt(x++);
			if (aChar == '\\') {
				aChar = ori.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = ori.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);

		}
		return outBuffer.toString();
	}
	
	 /** 
     * 得到本月第一天的日期 
     * @Methods Name getFirstDayOfMonth 
     * @return Date 
     */  
    public Date getFirstDayOfMonth(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        cDay.set(Calendar.DAY_OF_MONTH, 1);  
        System.out.println(cDay.getTime());  
        return cDay.getTime();     
    }     
    /** 
     * 得到本月最后一天的日期 
     * @Methods Name getLastDayOfMonth 
     * @return Date 
     */  
    public Date getLastDayOfMonth(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
        System.out.println(cDay.getTime());  
        return cDay.getTime();     
    }  

	public static void main(String[] args) {
		/*
		 * String allString =
		 * "EXTREF5=$EXTREF1=$Db_Synchronized_Parts_List=$PartOfRelativeMold=$Article_Name=$RELPART3=$PartType=$Supplier=$LAYOUTS=$INSERT_TYPE=$Std_Part_Id=$RELPART2=$ORD_NUMBER=199.353x138.816x138.816$CONFIGURATION=$Material=$Section_Component=$MoldNo=$GATE_TYPE=$ProjectName=$EXTREF3=$ASMTYPENAME=$Mw_Side=$Db_Part_Desc=$Nrt_Db_Part_Rev=$Db_Units=$Catalog=$Part_Type=$EXTREF6=$Db_Part_No=$PDM_SAVE_ID=$EXTREF2=$Supplier_No=$RELPART1=$Db_Part_Name=$Description=$QUICK_MOLDBASE_NO=$INHERITABLE=$Db_Part_Rev=$Mw_Component_Name=$NOZZLE_SIZE=$SHIELDING_SCREW=$EXTREF4=$PartNo=$Db_Part_Type=$Mw_Manuf_Pos=$GATE_DIA=0.0$WIDTH=0.0$PITCH_Y=0.0$L=0.0$DIAMETER=0.0$LENGTH=0.0$PITCH_X=0.0$HEIGHT=0.0$_CCCVERSION=A.1$name=TEST0325_M003.PRT$number=TEST0325_M003.PRT$versionInfo.identifier.versionId=A$iterationInfo.identifier.iterationId=1$"
		 * ; String containString = "INSERT_TYPE="; String nextString = "$"; try
		 * { Object a[] =
		 * getNextPositionAtString(allString,containString,nextString);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		Object[] obj = new Object[] { null, new Date() };
		for (Object object : obj) {
			String typeName = BeanUtil.getClassName(trimObject(object)
					.getClass());
			System.out.println(typeName);
		}
	}
}
