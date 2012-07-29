package hk.franks.newsletter.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共功能操作，如获取当前时间等.
 * 
 * @author joey
 * 
 */
public class CommonFunction {

	/**
	 * 获取当前时间 精确到秒 格式为"yyyy-MM-dd HH:mm"
	 * 
	 * @return
	 */
	public static String getcurrentTimetext() {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		java.util.Date date = new java.util.Date();
		return format.format(date);
	}

	/**
	 * 获取当前日期. 格式为 yyyy-MM-dd"
	 * 
	 * @return
	 */
	public static String getcurrentDatetext() {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		return format.format(date);
	}

	/**
	 * 获取日期. 格式为 yyyy-MM-dd
	 * 
	 * @param dayindex
	 *            -1就是今天的前一天；2就是今天的后两天
	 * @return yyyy-MM-dd
	 */
	public static String getDate(int dayindex) {
		StringBuffer resultStr = new StringBuffer();
		Calendar currentTime = Calendar.getInstance();
		currentTime.add(Calendar.DAY_OF_YEAR, dayindex);
		resultStr.append(currentTime.get(Calendar.YEAR));
		resultStr.append("-");
		resultStr.append(new Integer(currentTime.get(Calendar.MONTH))+1);
		resultStr.append("-");
		resultStr.append(currentTime.get(Calendar.DAY_OF_MONTH));
		return resultStr.toString();
	}

	/**
	 * 判断Email格式是否正确
	 * 
	 * @return
	 */

	public static boolean isEmail(String email) {
		Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 大写转小写
	 * 
	 * @param 要被装换的字符串参数
	 * @return 小写字符串
	 */
	public static String toLowerCase(String fireString) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < fireString.length(); i++) {
			char c = fireString.charAt(i);
			if (Character.isUpperCase(c)) {
				buffer.append(Character.toLowerCase(c));
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

	/**
	 * 当前时间是否在参数时间之前
	 * 
	 * @param time
	 *            格式21:10
	 * @return
	 */
	public static boolean isCurrentTimeBeforTime(String time) {
		Calendar currentTime = Calendar.getInstance();
		Calendar tmpTime = Calendar.getInstance();
		String tmpTimeArray[] = time.split(":");
		int hour = new Integer(tmpTimeArray[0]);
		int minute = new Integer(tmpTimeArray[1]);
		tmpTime.set(Calendar.HOUR_OF_DAY, hour);
		tmpTime.set(Calendar.MINUTE, minute);
		return tmpTime.after(currentTime);
	}
	
	/**
	 * get Current Timestamp
	 * @return
	 */
	public static Timestamp getCurrentTimestamp(){
		Date date = new Date();
		Timestamp tst = new Timestamp(date.getTime());
		return tst;
	}

}
