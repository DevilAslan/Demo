package cn.umbrella.conmmons.util.base;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 时间格式化及计算
 */
public class DateStringUtility {

	public static final String YYYY_MM_DD_HH_MM_SS_MS = "yyyy-MM-dd HH:mm:ss:ms";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYYMM = "yyyy.MM";
	public static final String YYYYMM2 = "yyyyMM";
	public static final String YYYYMMDDHHMMSSMS = "yyyyMMddhhmmssms";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddhhmmss";
	public static final String YYYYMMDDHHMMSS2 = "yyyyMMddHHmmss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYY = "yyyy";
	public static final String HH_MM_SS = "hh:mm:ss";
    public static final String YYYYMMDDHHMMSSSSS="yyyyMMddHHmmssSSS";
    
    /**
     * 
     * @Title: getYear 
     * @Description: 获取年份 
     * @param date
     * @return String
     * @throws
     */
    public static String getYear(Date date) {
    	String dateString = "";
		if(date != null) {
    		SimpleDateFormat formatter = new SimpleDateFormat(YYYY);
    		dateString = formatter.format(date);
		}
		return dateString;
    }
    
	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToString(Date dateDate, String formatString) {
		String dateString = "";
		if(dateDate != null && formatString != "" && formatString != null) {
    		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
    		dateString = formatter.format(dateDate);
		}
		return dateString;
	}

	/**
	 * 获取当前时间
	 * 
	 * @param formatString yyyyMMddHHmmss, yyyy-MM-dd HH:mm:ss(标准格式), yyyyMMddHHmmssSSS
	 * @return Date
	 */
	public static String getCurrDateStr(String formatString) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		String dateString = formatter.format(date);
		return dateString;
	}
	
	/**
	 * 获取当前时间字符串
	 * @param formatString 格式化
	 * @return Date
	 */
	public static Date getCurrDateTime(String formatString) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		String dateString = formatter.format(date);
		Date currentDateTime = null;
		try {
			currentDateTime = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentDateTime;
	}

	/**
	 * 格式时间,假如date为空则为当前格式时间
	 * 
	 * @param dateStr 时间字符串
	 * @param formatString (必须与所传时间字符串格式相同)例yyyyMMddHHmmss
	 * @param simpleDateFormatStri需要返回的格试 例yyyyMMddHHmmss
	 * @return String
	 */
	public static String getDate(String dateStr, String formatString, String simpleDateFormatString) {

		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(simpleDateFormatString);
		ParsePosition pos = new ParsePosition(0);
		Date nowDate = formatter.parse(dateStr, pos);
		nowDate = new Date(nowDate.getTime());
		String strDate = simpleDateFormat.format(nowDate);
		return strDate;
	}

	/**
	 * 当前的下年,月,日afterOrAgo或上年,月,日beforeOrAfter的时间
	 * 
	 * @param type 1,年 2,月 3,日
	 * @param beforeOrAfter 下beforeOrAfter年,月,日份数
	 * @param formatString 格式化参数 yyyyMMddHHmmss
	 * @return String
	 */
	public static String getDateBeforeOrAfter(int type, int beforeOrAfter, String formatString) {
		GregorianCalendar now = new GregorianCalendar();
		SimpleDateFormat outFormat = new SimpleDateFormat(formatString);
		switch (type) {
		case 1:
			now.add(GregorianCalendar.YEAR, beforeOrAfter);
			break;
		case 2:
			now.add(GregorianCalendar.MONTH, beforeOrAfter);
			break;
		case 3:
			now.add(GregorianCalendar.DATE, beforeOrAfter);
			break;
		case 4:
			now.add(GregorianCalendar.HOUR, beforeOrAfter);
			break;
		case 5:
			now.add(GregorianCalendar.SECOND, beforeOrAfter);
			break;
		case 6:
			now.add(GregorianCalendar.MINUTE, beforeOrAfter);
			break;
		}
		return outFormat.format(now.getTime());
	}

	/**
	 * 所需时间的下年,月,日afterOrAgo或上年,月,日beforeOrAfter的时间
	 * 
	 * @param dateStr 时间字符串
	 * @param type 1,年 2,月 3,日
	 * @param beforeOrAfter 下beforeOrAfter年,月,日份数
	 * @param formatString 格式化参数 yyyyMMddHHmmss
	 * @return String
	 * @throws Exception
	 */
	public static String getDateBeforeOrAfter(String dateStr, int type, int beforeOrAfter, String formatString) {

		SimpleDateFormat outFormat = new SimpleDateFormat(formatString);
		GregorianCalendar calendar = new GregorianCalendar();
		try {
			Date date = outFormat.parse(dateStr);
			calendar.setTime(date);
			switch (type) {
			case 1:
				calendar.add(GregorianCalendar.YEAR, beforeOrAfter);
				break;
			case 2:
				calendar.add(GregorianCalendar.MONTH, beforeOrAfter);
				break;
			case 3:
				calendar.add(GregorianCalendar.DATE, beforeOrAfter);
				break;
			}
		} catch (ParseException ex) {
			try {
				throw new Exception("日期格式化异常！", ex);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return outFormat.format(calendar.getTime());
	}

	/**
	 * 获取当前时间前后beforeOrAfterDay天时间
	 * 
	 * @param beforeOrAfterDay
	 * @return Calendar
	 */
	public static Calendar getCalendar(int beforeOrAfterDay) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
		return calendar;
	}

	/**
	 * 获取Calendar前后beforeOrAfterDay天时间
	 * 
	 * @param calendar
	 * @param beforeOrAfterDay 前后天数用正负表示
	 * @return Calendar
	 */
	public static Calendar getCalendar(Calendar calendar, int beforeOrAfterDay) {
		calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
		return calendar;
	}

	/**
	 * 获取Calendar前后beforeOrAfterDay天时间
	 * 
	 * @param date
	 * @param beforeOrAfterDay 前后天数用正负表示
	 * @return Calendar
	 */
	public static Calendar getCalendar(Date date, int beforeOrAfterDay) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
		return calendar;
	}

	/**
	 * 获取当前时间前后beforeOrAfterDay天时间
	 * 
	 * @param beforeOrAfterDay
	 * @return Date
	 */
	public static Date getDate(int beforeOrAfterDay) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
		return calendar.getTime();
	}

	/**
	 * 获取Date前后beforeOrAfterDay天时间
	 * 
	 * @param calendar
	 * @param beforeOrAfterDay
	 * @return Date
	 */
	public static Date getDate(Calendar calendar, int beforeOrAfterDay) {
		calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
		return calendar.getTime();
	}

	/**
	 * 获取Date前后afterOrAgo天时间
	 * 
	 * @param date
	 * @param beforeOrAfterDay
	 * @return Date
	 */
	public static Date getDate(Date date, int beforeOrAfterDay) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, beforeOrAfterDay);
		return calendar.getTime();
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 返回double值 保留两位小数
	 * */
	public static Double getDouble2(Double d) {
		return new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {
		// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * 
	 * @param nowdate
	 * @param delay
	 * @return
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = stringToDate(nowdate, "yyyy-MM-dd");
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String datestr) {
		// 取该时间所在月的一号
		datestr = datestr.substring(0, 8) + "01";
		// 得到这个月的1号是星期几
		Date date = stringToDate(datestr, "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = getNextDay(datestr, (1 - u) + "");
		return newday;
	}

	/**
	 * 根据传入时间，返回格式，返回上个月时间  
	 *
	 * @Title: getPreMonth 
	 * @param date
	 * @param formatString
	 * @return String
	 */
	public static String getPreMonth(Date date, String formatString) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		Date pre = c.getTime();
		return DateStringUtility.dateToString(pre, formatString);
	}

	/**
	 * 根据传入时间，返回格式，返回下个月时间  
	 *
	 * @Title: getPreMonth 
	 * @param date
	 * @param formatString
	 * @return String
	 */
	public static String getNextMonth(Date date, String formatString) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		Date pre = c.getTime();
		return DateStringUtility.dateToString(pre, formatString);
	}
	
	/**
	 * 时间前推或后推分钟,其中mm表示分钟.
	 */
	public static String getPreTime(String sj1, int mm) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datestr = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + mm * 60;
			date1.setTime(Time * 1000);
			datestr = format.format(date1);
		} catch (Exception e) {
		}
		return datestr;
	}


	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获取当前时间
	 * 
	 * @param formatString yyyyMMddHHmmss, yyyy-MM-dd HH:mm:ss(标准格式), yyyyMMddHHmmssSSS
	 * @return String
	 */
	public static String getStringDate(String formatString) {
		// GregorianCalendar now = new GregorianCalendar();
		// SimpleDateFormat outFormat = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String dateStr = outFormat.format(now.getTime());
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat(formatString);
		String str = outFormat.format(now);
		return str;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}
	
	/**
	 * 获得二个时间之差-以毫秒为单位
	 * @param curDate 
	 * @param targetDate
	 * @return long 计算后的时间毫秒数
	 * **/
	public static long getTimes(Date curDate,Date targetDate){
		if(null==curDate||null==targetDate){
			return 0;
		}
		long times=curDate.getTime()-targetDate.getTime();
		return times;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort(String formatString) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到二个日期间的间隔天数
	 * 
	 * @param sj1
	 * @param sj2
	 * @return
	 */
	public static String getTwoDay(String str1, String str2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(str1);
			java.util.Date mydate = myFormatter.parse(str2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 * 
	 * @param st1
	 * @param st2
	 * @return
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String datestr) {
		// 再转换为时间
		Date date = stringToDate(datestr, "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String date, String num) {
		// 再转换为时间
		Date dd = stringToDate(date, "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	public static String getWeekStr(String date) {
		String str = "";
		str = getWeek(date);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 判断是否润年
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isLeapYear(String date) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = stringToDate(date, "yyyy-MM-dd");
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 毫秒转时间
	 * 
	 * @param millisecond
	 * @return
	 */
	public static String MillisecondToSimpleDate(int millisecond) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(millisecond);
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date stringToDate(String strDate, String formatString) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	public static void main(String[] args) {
		String a = "201610";
		Date d = stringToDate(a, YYYY_MM);
		System.out.println(d);
		
		System.out.println(getDayOfMonth("2017-05"));

//		System.out.println(getNextMonth(stringToDate("2016-12", YYYY_MM), YYYY_MM));
//		Map<String, String> map = getFirstday_Lastday_Month(new Date());
//		for (Map.Entry<String, String> entry  : map.entrySet()) {
//			System.out.println(entry.getKey() + "：" + entry.getValue());
//		}
		System.out.println(getFirstDay(new Date()));

		Map<String, String> map = getCurrentFirstday_Lastday_Month(new Date());
		for (Map.Entry<String, String> entry  : map.entrySet()) {
			System.out.println(entry.getKey() + "：" + entry.getValue());
		}
		
	}
	
	/**
	 * 将date 转换成 Calendar格式
	 * ***/
	public static Calendar dateToCalendar(Date date,String formatString){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException 
	 */
	public static Calendar stringToCalendar(String strDate, String formatString) throws ParseException {
		if(strDate==null){
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		formatter.parse(strDate);
		return formatter.getCalendar();
	}

	public static Calendar parseTimestamp(String timestamp, String formatString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString, Locale.CHINA);
		Date date = sdf.parse(timestamp);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Calendar parseDatetime(String strDate) throws ParseException {
		if (strDate == null || "".equals(strDate.trim())) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_SSS.substring(0, strDate.length()));
		sdf.parse(strDate);
		return sdf.getCalendar();
	}

	public static String getFourSeq(String seq) {
		switch (seq.length()) {
		case 1:
			seq = "000" + seq;
			break;
		case 2:
			seq = "00" + seq;
			break;
		case 3:
			seq = "0" + seq;
			break;

		default:
			seq = seq.substring(seq.length() - 4);
			break;
		}

		return seq;
	}
	
	/**
	 * 按格式化串比较两个日期,要求格式化串必须按年月日时分秒顺序
	 * 格式化后,日期相同返还0,第一个日期小于第二个日期返回-1,第一个日期大于第二个日期返回1
	 * @param cal1
	 * @param cal2
	 * @param formatStr
	 * @return
	 */
	public static int compare(Calendar cal1, Calendar cal2, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String date1 = sdf.format(cal1.getTime());
		String date2 = sdf.format(cal2.getTime());
		return date1.compareTo(date2);
	}
	
	 /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
     
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
         
        return currYearLast;
    }
    
    /**
     * 第一天和最后一天
     * @param date
     * @return
     * 2015-06-01 00:00:00
	   2015-06-30 23:59:59
     */
    public static Map<String, String> getFirstday_Lastday_Month(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        
        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        day_first = str.toString();

        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
        day_last = endStr.toString();

        Map<String, String> map = new HashMap<String, String>();
        map.put("first", day_first);
        map.put("last", day_last);
        return map;
    }

    /**
     * 
    * @Title: getCurrentFirstday_Lastday_Month
    * @Description: 获取当月第一天和最后一天
    * @param date
    * @return Map<String,String>
     */
    public static Map<String, String> getCurrentFirstday_Lastday_Month(Date date) {
    	String firstday, lastday;  
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获取前月的第一天  
        calendar.add(Calendar.MONTH, 0);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = df.format(calendar.getTime());  
        StringBuffer str = new StringBuffer().append(firstday).append(" 00:00:00");
        firstday = str.toString();
        
        // 获取前月的最后一天  
        calendar = Calendar.getInstance();  
        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 0);  
        lastday = df.format(calendar.getTime());  
        StringBuffer endStr = new StringBuffer().append(lastday).append(" 23:59:59");
        lastday = endStr.toString();
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("first", firstday);
    	map.put("last", lastday);
    	return map;
    }
    
    
    
    /**
     * 当月第一天 2015-07-01 00:00:00
     * @return
     */
    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString();

    }
    
    /**
     * 
    * @Title: getFirstDay
    * @Description: 返回传入时间所在月份第一天
    * @param date
    * @return String
     */
    public static String getFirstDay(Date date) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString();
    }
    
    /**
     *    上周的星期一
     *    last Monday Mon Jun 22 16:38:14 CST 2015
     * @return
     */
    public static Date getPrevWeekMonday() {
    	Calendar calendar1 = Calendar.getInstance();
		int dayOfWeek=calendar1.get(Calendar.DAY_OF_WEEK)-1;
		int offset1=1-dayOfWeek;
		calendar1.add(Calendar.DATE, offset1-7);
        return calendar1.getTime();
    }
    /**
     *   上周的星期日
     *    Sun Jun 28 16:38:14 CST 2015
     * @return
     */
    public static Date getPrevWeekSunday() {
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek=calendar2.get(Calendar.DAY_OF_WEEK)-1;
		int offset2=7-dayOfWeek;
		calendar2.add(Calendar.DATE, offset2-7);
        return calendar2.getTime();
    }
    
    /**
     * 
    * @Title: getDayOfMonth
    * @Description: 根據月份返回天數
    * @param yearMonth
    * @return Integer
     */
    public static Integer getDayOfMonth(String yearMonth) {
    	if (StringUtils.isBlank(yearMonth)) {
    		return 0;
    	}
    	Date date = stringToDate(yearMonth, YYYY_MM);
		// 获得Calendar实例
		Calendar calendar = Calendar.getInstance();
		// 根据date赋值
		calendar.setTime(date);
		// 计算是当月的第几天
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		// 计算当月的第一天
		calendar.add(Calendar.DATE, 1 - dayOfMonth);
		// 计算下月的第一天
		calendar.add(Calendar.MONTH, 1);
		// 计算当月的最后一天
		calendar.add(Calendar.DATE, -1);
		// 计算当月一共几天
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    	return dayOfMonth;
    }
}
