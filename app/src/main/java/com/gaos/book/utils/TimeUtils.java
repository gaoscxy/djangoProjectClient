package com.gaos.book.utils;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {

    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String MM_DD_HH_MM = "MM-dd HH:mm";
    private static final String HH_MM_SS = "HH:mm:ss";
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final String ONE_SECOND_AGO = "刚刚";
//    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    public static long serverTime = getNowTime();

    /**
     * 时间转换
     *
     * @param position 当前播放的事件 默认毫秒
     * @return
     */
    public static String parseTime(int position) {
        return (String) DateFormat.format("mm:ss", position);
    }


    public static long TimezhuanLong(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        try {
            d = sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d.getTime();
    }

    public static long getNowTime() {
        Date dt = new Date();
        return dt.getTime();
    }

    public static String getStrNowTime() {
        Date dt = new Date();
        return dt.toString();
    }

    /**
     * 转换时间日期或者时间
     *
     * @param value 2013-11-11 18:35:35
     * @return
     * @throws ParseException
     */
    public static String format(String value,String system_time) {
        SimpleDateFormat format1 = new SimpleDateFormat(YYYY_MM_DD);
        SimpleDateFormat format2 = new SimpleDateFormat(HH_MM_SS);
        String date = format1.format(new Date(TimezhuanLong(value)));
        system_time = format1.format(new Date(TimezhuanLong(system_time)));
        try {
            if(system_time.equals(date)){
                //今天，返回时分秒
                date = format2.format(new Date(TimezhuanLong(value)));//" 18:35:35"
            }
        } catch (Exception e) {
            Log.e("TimeUtils.class", e.getMessage());
        }
        return date;

    }
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

    public static String format(long systemtime,long value) {
//        long delta = new Date().getTime() - value;
        long delta = serverTime - value;
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
//            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
            return ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
//        if (delta < 48L * ONE_HOUR) {
//            return "1天前";
//        }
//        if (delta < 8L * ONE_DAY) {
//            long days = toDays(delta);
//            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
//        }
//        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
//        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
//        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM);
        SimpleDateFormat format = new SimpleDateFormat(MM_DD_HH_MM);
        return format.format(new Date(value));
    }

    /**
     *  时间转换为YYYY-MM-DD
     */
   public static String formartToYMD(long time){
       SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
       Date date = new Date(time);
       return format.format(date);
   }
   public static String formartToYMDHHMM(long time){
       SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM);
       Date date = new Date(time);
       return format.format(date);
   }
   public static String formartToYMDHHMMSS(long time){
       SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
       Date date = new Date(time);
       return format.format(date);
   }
   /**
   * 返回2018-04-10 11:34格式
   * @author gaos
   */
   public static String formartYMDHHMMssToYMDHHMM(String time){
       SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM);
       Date date = new Date(TimezhuanLong(time));
       return format.format(date);
   }

    /**
     * 返回2018-04-10格式
     * @author gaos
     */
   public static String formartYMDHHMMssToYMD(String time){
       SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
       Date date = new Date(TimezhuanLong(time));
       return format.format(date);
   }

    public static long getLongSystemTime(){
        long time=System.currentTimeMillis();
        return time;
    }
    /**
     * 返回2018-04-10格式的后台系统时间
     * @author gaos
     */
    public static String getSystemTime(){
        String time = formartToYMD(serverTime);
        return time;
    }
    /**
     * 获取两个日期之间的间隔天数为1天
     * @return
     */
    public static boolean getGapCount(long startDate) {
        long endDate = getLongSystemTime();
        long result = endDate - startDate;
        int d = (int) (Math.abs(result) / (1000 * 60 * 60 * 24));
        if(d >= 1){
            return true;
        }else{
            return false;
        }
    }
    /**
    * 根据调用接口获取服务器系统时间
    * @author gaos
    */
    public static void setServerTime(String strServerTime){

        if(!TextUtils.isEmpty(strServerTime)){
            try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            Date date = dateFormat.parse(strServerTime);
            //加8个时区
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(date);
             calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
             date = calendar.getTime();
             serverTime = date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 对比两个时间大小
     * @author gaos
     * @param time1 09:00
     * @param time2 11:00
     * @return false time1>time2
     */
    public static boolean compareTime(String time1,String time2){
        if(TextUtils.isEmpty(time1) || TextUtils.isEmpty(time2)){
            return true;
        }
        String[] time1s = time1.split(":");
        String[] time2s =  time2.split(":");
        if(Integer.parseInt(time1s[0]) > Integer.parseInt(time2s[0])){
            return false;
        }
        if(Integer.parseInt(time1s[0]) == Integer.parseInt(time2s[0]) && Integer.parseInt(time1s[1]) >= Integer.parseInt(time2s[1])){
            return false;
        }
        return true;
    }

    /**
     * 秒转成 时分秒格式
     * @param second
     * @return
     */
    public static String getTime(int second) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(second);
        return hms;
    }

    public static String parseSecTime(int size) {
//        int size = duration/1000;
        String  time;
//        if(size<60){
//            time=String.format("00:%02d",size%60);
//        }else if(size<3600){
//            time=String.format("%02d:%02d",size/60,size%60);
//        }else{
            time=String.format("%02d:%02d:%02d",size/3600,size%3600/60,size%60);
//        }
        return time;
    }

}
