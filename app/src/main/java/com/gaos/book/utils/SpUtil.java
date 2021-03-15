package com.gaos.book.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by gaos on 2017/8/4.
 */
public class SpUtil {

    static SharedPreferences prefs;

    private static final String KEY_BRGIHTNESS = "KEY_BRGIHTNESS";
    private static final String KEY_TEXT_SIZE = "KEY_TEXT_SIZE";
    private static final String KEY_OPERATION_TIPS = "KEY_OPERATION_TIPS";
    private static final String KEY_CALL_SESSION = "KEY_CALL_SESSION";    //音视频通话

    public static String getDataByKey(String key) {
        return prefs.getString(key, "");
    }

    public static int getIntByKey(String key) {
        return prefs.getInt(key, -1);
    }

    public static long getLongByKey(String key) {
        return prefs.getLong(key, -1);
    }

    public static boolean getBoolean(String key) {
        return prefs.getBoolean(key, false);
    }

    public static int getTextSizeByKey(String key) {
        return prefs.getInt(key, 0);
    }

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 判断是否登录
     *
     * @return
     */
//    public static Boolean isLogin() {
//        return getUser() != null;
//    }
//
//    public static User getUser() {
//        return new Gson().fromJson(prefs.getString("user", ""), User.class);
//    }
//
//    public static void setUser(User user) {
//        prefs.edit().putString("user", new Gson().toJson(user)).apply();
//    }
//
//    public static AreaInfo getArea() {
//        return new Gson().fromJson(prefs.getString("area", ""), AreaInfo.class);
//    }
//
//    public static void setArea(AreaInfo area) {
//        prefs.edit().putString("area", new Gson().toJson(area)).apply();
//    }
//
//    public static void setUpdateInfo(UpdateInfo.DataBean data) {
//        prefs.edit().putString("updateInfo", new Gson().toJson(data)).apply();
//    }
//
//    public static UpdateInfo.DataBean getUpdateInfo() {
//        return new Gson().fromJson(prefs.getString("updateInfo", ""), UpdateInfo.DataBean.class);
//    }

    public static String getData(String key) {
        return prefs.getString(key, "");
    }

    public static String getImageVersion(String key) {
        return prefs.getString(key, "1.0.0");
    }

    public static void setData(String key, String data) {
        prefs.edit().putString(key, data).apply();
    }

    public static void setData(String key, int data) {
        prefs.edit().putInt(key, data).apply();
    }

    public static void setData(String key, boolean aBoolean) {
        prefs.edit().putBoolean(key, aBoolean).apply();
    }

    public static void setData(String key, long data) {
        prefs.edit().putLong(key, data).apply();
    }

}
