package cn.yoho.library.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @description 日期工具类
 * @version 1.0
 */
public class DateUtil {
	public static final long m_second = 1000;
	public static final long m_minute = m_second * 60;
	public static final long m_hour = m_minute * 60;
	public static final long m_day = m_hour * 24;
	private static String[] cnweek = { "", "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

	/**
	 * 得到当前的时间，精确到毫秒,共14位 返回格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		long time = System.currentTimeMillis();
		Date NowDate = new Date(time);
		return formatter.format(NowDate);
	}

	/**
	 * 得到当前的时间，精确到毫秒,共14位 返回格式:yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		long time = System.currentTimeMillis();
		Date NowDate = new Date(time);
		return formatter.format(NowDate);
	}

	/**
	 * 
	 * @description 获取当前月份的当前天数
	 * @createDate 2014-7-16
	 * @return
	 */
	public static String getCurrentDay() {
		java.util.Date NowDate = new java.util.Date(System.currentTimeMillis());

		SimpleDateFormat formatter = new SimpleDateFormat("dd", Locale.getDefault());
		return formatter.format(NowDate);
	}

	/**
	 * 得到当前的年份 返回格式:yyyy
	 * 
	 * @return String
	 */
	public static String getCurrentYear() {
		java.util.Date NowDate = new java.util.Date(System.currentTimeMillis());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.getDefault());
		return formatter.format(NowDate);
	}

	/**
	 * 得到当前的月份 返回格式:MM
	 * 
	 * @return String
	 */
	public static String getCurrentMonth() {
		java.util.Date NowDate = new java.util.Date(System.currentTimeMillis());

		SimpleDateFormat formatter = new SimpleDateFormat("MM", Locale.getDefault());
		return formatter.format(NowDate);
	}

	/**
	 * 根据字串获取日期
	 * 
	 * @description
	 * @param str
	 * @return
	 */
	public static Date getDateFormat(String str) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			date = formatter.parse(str);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * return yyyy-MM-dd
	 * 
	 * @param time
	 * @return
	 */
	public static String getFormatDate(Date date) {
		SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		try {
			return dateSDF.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * return yyyy-MM-dd HH:mm
	 */
	public static String formatDateTime(long time) {
        if (0 == time) {
            return "";
        }
        SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        try {
			return dateSDF.format(new Date(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "";
    }

	/**
	 * return yyyy-MM-dd
	 * 
	 * @param time
	 * @return
	 */
	public static String getFormatMonth(Date date) {
		SimpleDateFormat dateSDF = new SimpleDateFormat("MM月dd日", Locale.getDefault());
		try {
			return dateSDF.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * return yyyy-MM-dd
	 * 
	 * @param time
	 * @return
	 */
	public static String getFormatYear(Date date) {
		SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy年", Locale.getDefault());
		try {
			return dateSDF.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getCnWeek(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return cnweek[c.get(Calendar.DAY_OF_WEEK)];
	}

	/**
	 * return 周x hh:mm
	 */
	public static String getFormatTime(Date date) {
		SimpleDateFormat dateSDF = new SimpleDateFormat("E HH:mm", Locale.getDefault());
		try {
			return dateSDF.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * return 周x
	 */
	public static String getWeekTime(Date date) {
		SimpleDateFormat dateSDF = new SimpleDateFormat("E", Locale.getDefault());
		try {
			return dateSDF.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * return HH:mm
	 */
	public static String getHourTime(Date date) {
		SimpleDateFormat dateSDF = new SimpleDateFormat("HH:mm", Locale.getDefault());
		try {
			return dateSDF.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
