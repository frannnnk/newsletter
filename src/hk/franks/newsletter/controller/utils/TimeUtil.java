package hk.franks.newsletter.controller.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class TimeUtil {

    
    public static String getCommonFormatStr() {
        return "yyyy-MM-dd";
    }
    
    public static boolean compareDatesNotIncludingTime(Date date1, Date date2){
    	if (CommonUtil.isExNull(date1) || CommonUtil.isExNull(date2)) {
    		return false;
    	}
    	
    	String d1 = TimeUtil.getDay(date1, "yyyy-MM-dd");
    	String d2 = TimeUtil.getDay(date2, "yyyy-MM-dd");
    	
    	if (d1.equals(d2)) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    public static List getEbbinghausDates(Date startDate) {
    	//20分钟后再重复一遍，1小时后，12小时后，|| 1天后，2天后，5天后，8天后，14天，26天，14天，7天，3天后就会记得很牢
    	List list = new ArrayList();
    	list.add(TimeUtil.getDay(startDate, 1));
    	list.add(TimeUtil.getDay(startDate, 3));
    	list.add(TimeUtil.getDay(startDate, 8));
    	list.add(TimeUtil.getDay(startDate, 16));
    	list.add(TimeUtil.getDay(startDate, 30));
    	list.add(TimeUtil.getDay(startDate, 56));
    	list.add(TimeUtil.getDay(startDate, 70));
    	list.add(TimeUtil.getDay(startDate, 77));
    	list.add(TimeUtil.getDay(startDate, 80));
	    	
        return list;
    }
    
    public static int getDateDif(Date fromDate, Date toDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fromDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(toDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

     }
    
    public static DateFormat getCommonFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static Date getCurrentDate() {
        Calendar calendar = new GregorianCalendar();
        return calendar.getTime();
    }
    
    /**
     * 
     * 得到给定日期的前几月或者后几月
     * date: 给定的日期 
     * inc: 根据给定的日期向后（positive） 或者向前（negative）的月数。
     * @return java.util.Date
     */
	public static Date getDateByIncMonth(Date date, int inc) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, inc);
		return calendar.getTime();
	}

    public static String getCurrentDate(String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Date currentDate = getCurrentDate();

        return sdf.format(currentDate);
    }

    /**
     * 得到输入日期的前几天或后几天
     * 
     * @param day
     *            (yyyy-MM-dd)
     * @param inc
     *            (日期增减量)
     * @param formatStyle
     *            (格式化日期的格式:yyyy-MM-dd)
     * @return String (yyyy-MM-dd)
     */
    public static String getDay(String day, int inc, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Calendar calendar = new GregorianCalendar();
        // Date now = new Date();
        if (day == null || "".equals(day)) {
            return sdf.format(calendar.getTime());
        }
        Date date = null;
        try {
            date = sdf.parse(day);
            calendar.setTime(date);
            calendar.add(GregorianCalendar.DATE, inc);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            //log.error(e.toString());
            return null;
        }
    }

    /**
     * 得到当前日期前几天或后几天
     * 
     * @param inc
     *            (日期增减量)
     * @param formatStyle
     *            (格式化日期的格式:yyyy-MM-dd)
     * @return String (yyyy-MM-dd)
     */
    public static String getDay(int inc, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Calendar calendar = new GregorianCalendar();
        // Date now = new Date();
        // calendar.setTime(now);
        calendar.add(GregorianCalendar.DATE, inc);
        return sdf.format(calendar.getTime());
    }
    
    public static Date getDay(int inc) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(GregorianCalendar.DATE, inc);
        return calendar.getTime();
    }
    
    public static Date getDay(Date date, int inc) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE, inc);
        return calendar.getTime();
    }
    
    public static Date getDay(int inc, int field) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(field, inc);
        return calendar.getTime();
    }

    public static Date getDay(String day, String formatStyle) {
    	if (CommonUtil.isExNull(day)) {
    		return null;
    	}
    	
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        try {
            return sdf.parse(day);
        } catch (ParseException e) {
            //log.error(e.toString());
            return null;
        }
    }

    public static String getDay(Date day, String formatStyle) {
        if (day == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
            return sdf.format(day);
        }
    }

    public static boolean isAfterNow(String day, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Calendar now = Calendar.getInstance();
        try {
            if (sdf.parse(sdf.format(now.getTime())).before(sdf.parse(day))) {
                return true;
            }
        } catch (ParseException e) {
            //log.error(e.toString());
        }
        return false;
    }
    
    public static boolean isAfterNow(Date day, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Calendar now = Calendar.getInstance();
        try {
            if (sdf.parse(sdf.format(now.getTime())).before(day)) {
                return true;
            }
        } catch (ParseException e) {
            //log.error(e.toString());
        }
        return false;
    }
    
    public static boolean isBeforeNow(String day, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Calendar now = Calendar.getInstance();
        try {
            if (sdf.parse(sdf.format(now.getTime())).after(sdf.parse(day))) {
                return true;
            }
        } catch (ParseException e) {
            //log.error(e.toString());
        }
        return false;
    }
    
    public static boolean isBeforeNow(Date day, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Calendar now = Calendar.getInstance();
        try {
            if (sdf.parse(sdf.format(now.getTime())).after(day)) {
                return true;
            }
        } catch (ParseException e) {
            //log.error(e.toString());
        }
        return false;
    }
    
    public static boolean isBetween(Date date, Date startDate, Date endDate) {
        if ((date.after(startDate) || date.equals(startDate)) 
                && (date.before(endDate) || date.equals(endDate))) {
            return true;
        }
        
        return false;
    }
    
    public static boolean isBetween(Date date, Date startDate, Date endDate, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Date compareDate;
        Date compareStartDate;
        Date compareEndDate;
        
        try {
            compareDate = sdf.parse(sdf.format(date));
            compareStartDate = sdf.parse(sdf.format(startDate));
            compareEndDate = sdf.parse(sdf.format(endDate));
        } catch (ParseException e) {
            //log.error(e.getMessage());
            
            return false;
        }
        
        if ((compareDate.after(compareStartDate) || compareDate.equals(compareStartDate)) 
                && (compareDate.before(compareEndDate) || compareDate.equals(compareEndDate))) {
            return true;
        }
        
        return false;
    }
    
    public static boolean isBetween(String dateStr, String startDateStr, String endDateStr, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        Date date;
        Date startDate;
        Date endDate;
        try {
            date = sdf.parse(dateStr);
            startDate = sdf.parse(startDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            //log.error(e.getMessage());
            
            return false;
        }
        
        if ((date.after(startDate) || date.equals(startDate)) 
                && (date.before(endDate) || date.equals(endDate))) {
            return true;
        }
        
        return false;
    }
    
    public static boolean isEquals(String oneStr, String otherStr) {
    	if (CommonUtil.isExNull(oneStr) && CommonUtil.isExNull(otherStr)) {
    		return true;
    	}
    	if (!CommonUtil.isExNull(oneStr) && CommonUtil.isExNull(otherStr)) {
    		return false;
    	}
    	if (CommonUtil.isExNull(oneStr) && !CommonUtil.isExNull(otherStr)) {
    		return false;
    	}
    	
    	String formatStyle = "yyyyMMdd";
    	Date oneDate = getDay(oneStr, formatStyle);
    	Date otherDate = getDay(otherStr, formatStyle);
    	
    	if (!CommonUtil.isExNull(oneDate)) {
    		if (oneDate.equals(otherDate)) {
    			return true;
    		}
    	}
    	if (!CommonUtil.isExNull(otherDate)) {
    		if (otherDate.equals(oneDate)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static boolean isEquals(Date one, String other) {
    	return isEquals(getDay(one, "yyyyMMdd"), other);
    }

    /**
     * 得到输入日期的前一天
     * 
     * @param day
     *            (yyyy-MM-dd)
     * @return String (yyyy-MM-dd)
     */
    public static String getPreviousDay(String day) {
        return getDay(day, -1, "yyyy-MM-dd");
    }

    /**
     * 取得指定月份的第一天
     * 
     * @param strDate
     *            (yyyyMM)
     * @return String (yyyyMMddhhmmss)
     */
    public static String getMonthBegin(String strDate) {
        return strDate + "01000000";
    }

    /**
     * 取得指定月份的最后一天
     * 
     * @param strdate
     *            (yyyyMM)
     * @return String (yyyyMMddhhmmss)
     */
    public static String getMonthEnd(String strDate) {
        SimpleDateFormat formatYYYYMM = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat formatYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = formatYYYYMM.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatYYYYMMDD.format(calendar.getTime()) + "235959";
    }

    public static Date getDayBegin(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }

    public static Date getDayEnd(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        
        return calendar.getTime();
    }
}
