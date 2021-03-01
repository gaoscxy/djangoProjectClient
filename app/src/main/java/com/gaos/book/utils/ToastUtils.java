package com.gaos.book.utils;

import android.widget.Toast;

import com.gaos.book.common.MyApplication;
/**
*
* @author gaos
* created at 2021/2/25 14:58
*/
public class ToastUtils {

    public static void show(String msg){
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
