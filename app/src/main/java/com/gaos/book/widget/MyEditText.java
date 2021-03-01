package com.gaos.book.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.gaos.book.common.GlobalConstant;

import java.util.Objects;

public class MyEditText extends AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        Typeface tf = null;
        try {
            AssetManager mgr = Objects.requireNonNull(getContext()).getAssets();
            //根据路径得到Typeface
            tf = Typeface.createFromAsset(mgr, "fonts/" + GlobalConstant.FONT_STYLE);//幼圆
        } catch (Exception e) {
            tf = null;
        }
        setTypeface(tf);
    }
}
