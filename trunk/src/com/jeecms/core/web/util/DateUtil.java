package com.jeecms.core.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述--> 时间帮助类 </span><br />
 * <br />
 * <span> Copyright LENOVO </span><br />
 * <span> Project Name AppMusicServer </span><br />
 * <span> Author ZengZS </span><br />
 * <span> Create Time 2012-3-20 下午04:46:11 </span><br />
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br />
 * <span> Modification history none </span><br />
 * <span> Modified by none </span><br />
 * 
 */
public class DateUtil {

    /**
     * 
     * <span><b>功能</b></span><br />
     * <!--在这下面一行写功能描述--> 取当前时间至第二天凌晨的时间差，秒数 <br />
     * <br />
     * <span><b>参数</b></span><br />
     * <!--在这下面一行写参数描述，如果参数是多个，请用HTML标签br换行-->
     * 
     * <br />
     * <br />
     * <span><b>返回值 </b> </span><br>
     * <span><!--在这下面一行写返回类型，如果是接口，则写上返回值的类型，可以举例说明-->
     * 
     * </span> <br />
     * <br />
     * <span> Author ZengZS </span><br />
     * <span> Create Time 2012-3-20 下午05:01:39 </span><br />
     * 
     */
    public static int nowToNextDaySeconds() {
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        long sec = c.getTimeInMillis();
        return (int) (sec - now) / 1000;
    }

    /**
     * 计算当前时间与某一时间相隔多少天、或者小时、或者分钟、或者秒
     * 
     * @param now
     * @param before
     * @return
     */

//    public static String getDisplayDate(Date now, Date before) {
//        long diff = now.getTime() - before.getTime();
//        if (diff >= DateFormat.MILLSECOND_OF_WEEK) return DateFormat.MM_dd_HH_mm_ss.format(before);
//        if (diff >= DateFormat.MILLSECOND_OF_DAY) return (diff / DateFormat.MILLSECOND_OF_DAY) + "天前";
//        if (diff >= DateFormat.MILLSECOND_OF_HOUR) return (diff / DateFormat.MILLSECOND_OF_HOUR) + "小时前";
//        if (diff >= DateFormat.MILLSECOND_OF_MINUTE) return (diff / DateFormat.MILLSECOND_OF_MINUTE) + "分钟前";
//        return (diff / 1000) + "秒前";
//    }

    /**
     * 获取一周中的第一天
     */
    public static String getFirstDateOfWeek(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
        if (dd == 1) {
            dd = 7;
        } else if (dd > 1) {
            dd = dd - 1;
        }
        c.add(Calendar.DATE, 1 - dd);
        String date = c.get(Calendar.YEAR) + "-" + towStr(1 + c.get(Calendar.MONTH)) + "-" + towStr(c.get(Calendar.DATE));
        return date;
    }

    /**
     * 获取一周中的最后一天
     */
    public static String getLastDateOfWeek() {
        Calendar c = Calendar.getInstance();
        int dd = c.get(GregorianCalendar.DAY_OF_WEEK);
        if (dd == 1) {
            dd = 7;
        } else if (dd > 1) {
            dd = dd - 1;
        }
        c.add(Calendar.DATE, 7 - dd);
        String date = c.get(Calendar.YEAR) + "-" + towStr(1 + c.get(Calendar.MONTH)) + "-" + towStr(c.get(Calendar.DATE));
        return date;
    }

    public static String towStr(int n) {
        if (n < 10) {
            return "0" + n;
        } else {
            return "" + n;
        }
    }

    /**
     * 获取相差月份
     * 
     * @param month
     * @return
     */
    public static String getMonth(int month) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -month + 1);

        return c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-1";
    }

    /**
     * 根据日期获取毫秒
     * 
     * @param dateStr
     * @return
     */
    public static long getTimeMillis(String dateStr) {
        long time = 0;
//        try {
////            time = DateFormat.yyyy_MM_dd_HH_mm_ss.parse(dateStr).getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return time;
    }

    /**
     * 获取日期,格式为yyyy-MM-dd
     * 
     * @param day
     * @return
     */
    public static String getYesterdayDate(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前日期，格式为formatStr
     * 
     * @return
     */
    public static String getDateStr(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }

    public static void main(String[] args) {
        String date = DateUtil.getYesterdayDate(0);
        long beginTime = DateUtil.getTimeMillis(date + " 14:30:00");
        long endTime = DateUtil.getTimeMillis(date + " 15:30:00");

        long currentTime = System.currentTimeMillis();
        if (currentTime >= beginTime && currentTime <= endTime) {
            System.out.println((endTime - currentTime) / 1000);
        }
    }

}
