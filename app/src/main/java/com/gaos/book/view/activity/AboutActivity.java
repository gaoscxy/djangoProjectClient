package com.gaos.book.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gaos.book.base.BaseActivity;
import com.gaos.book.R;

public class AboutActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBarColor(R.color.black);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}