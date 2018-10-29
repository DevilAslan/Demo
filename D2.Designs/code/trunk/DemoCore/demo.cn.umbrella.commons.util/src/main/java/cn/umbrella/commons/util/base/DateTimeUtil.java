package cn.umbrella.commons.util.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {

	public final static String DATE_FORMAT_USUAL = "yyyy-MM-dd";

	public final static String DATE_FORMAT_CN = "yyyy年MM月dd日";

	public final static String TIME_FORMAT_USUAL = "yyyy-MM-dd HH:mm:ss";

	public final static String TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";

	public final static String MONTH_FORMAT_USUAL = "yyyy-MM";

	public final static String DAY_FORMAT_SIMPLE = "yyyyMMdd";

	// Begin public

	/**
	 * switch
	 * 
	 * @param calendar
	 * @param pattern
	 * @return pattern : ?
	 */
	public static String formatCalendar(Calendar calendar, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.format(calendar.getTime());
	}

	/**
	 * switch
	 * 
	 * @param calendar
	 * @return DEFAULTFORMAT:2015-03-15 13:35:42
	 */
	public static String formatCalendar(Calendar calendar) {
		return formatCalendar(calendar, TIME_FORMAT_USUAL);
	}

	/**
	 * get current by pattern
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurDateTime(String pattern) {
		return formatCalendar(Calendar.getInstance(), pattern);
	}

	/**
	 * get current DateTime
	 * 
	 * @return
	 */
	public static String getCurDateTime() {
		return getCurDateTime(TIME_FORMAT_USUAL);
	}

	/**
	 * get current Date
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return getCurDateTime(DATE_FORMAT_USUAL);
	}

	/**
	 * 判断时间格式 格式必须为“YYYY-MM-dd” 2004-2-30 是无效的 2003-2-29 是无效的
	 * 
	 * @param sDate
	 * @return
	 */
	public boolean isValidDate(String date_str, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		try {
			Date date = (Date) formatter.parse(date_str);
			return date_str.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * 
	 * @return
	 */
	public static String timeStamp() {
		long time = System.currentTimeMillis();
		String t = String.valueOf(time / 1000);
		return t;
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * 
	 * @return
	 */
	public static int getNowSecond() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds
	 *            精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty())
			format = TIME_FORMAT_USUAL;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 日期格式字符串转换成时间戳
	 * 
	 * @param date
	 *            字符串日期
	 * @param format
	 *            如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String date2TimeStamp(String date_str, String format) {
		if (date_str == null || date_str.isEmpty() || date_str.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty())
			format = TIME_FORMAT_USUAL;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(date_str).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取系统时间戳
	 * 
	 * @return
	 */
	public static long getSysTimeStamp() {
		long res;
		res = System.currentTimeMillis();
		return res;
	}

	/**
	 * 时间戳转换成时间
	 * 
	 * @param timeStamp
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String convertToTime(String timeStamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_USUAL);
		String date = sdf.format(new Date(timeStamp));
		return date;
	}

	/**
	 * 字符转换成日期
	 * 
	 * @param timeStamp
	 * @return
	 * @throws ParseException
	 */
	public static Date convertToDay(String day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT_SIMPLE);
		java.util.Date date = sdf.parse(day);
		return date;
	}

	public static String getSpecifiedDayBefore(String specifiedDay,
			int beforeDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_USUAL).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - beforeDay);

		String dayBefore = new SimpleDateFormat(DATE_FORMAT_USUAL).format(c
				.getTime());
		return dayBefore;
	}

	public static String getNextDay(Date date, int afterDay) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, afterDay);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_USUAL);
		String dateString = formatter.format(date);
		return dateString;
	}

	public static String millisToDate(String millis, String format) {
		if (format == null) {
			format = TIME_FORMAT_USUAL;
		}

		if ("".equals(millis)) {
			return "";
		} else {
			DateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(Long.parseLong(millis));
			return df.format(calendar.getTime());
		}
	}

	public static String secondToString(int second, String format) {
		return millisToDate(second * 1000l + "", format);
	}

	// End public

	// Begin private
	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	private static long toDays(long date) {
		return toHours(date) / 24L;
	}

	private static long toMonths(long date) {
		return toDays(date) / 30L;
	}

	@SuppressWarnings("unused")
	private static long toYears(long date) {
		return toMonths(date) / 365L;
	}
	// End private

	// Begin Test

	// End Test
}
