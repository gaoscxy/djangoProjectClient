package com.gaos.book.base;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    protected abstract int getLayoutResId();

    /**
     * 界面初始化前的准备
     */
    protected void doBeforeSetContentView() {
    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在设置内容View之前调用
        doBeforeSetContentView();
        // 设置布局
        if (getLayoutResId() != 0) {
            setContentView(getLayoutResId());
//            initPresenter();
            initView(savedInstanceState);
        }
    }
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}