package com.gaos.book.base;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.gaos.book.R;
import com.gaos.book.common.MyApplication;
import com.gaos.book.utils.StatusBarCompat;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity {

    protected CompositeDisposable mDisposable;
    /************************init area************************************/
    protected void addDisposable(Disposable d){
        if (mDisposable == null){
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(d);
    }
    private Toolbar mToolbar;
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
    protected abstract void initData(Bundle savedInstanceState);
    /**
     * 初始化点击事件
     */
    protected void initClick(){
    }

    /**
     * 逻辑使用区
     */
    protected void processLogic(){
    }

    /**
     * 配置Toolbar
     * @param toolbar
     */
    protected void setUpToolbar(Toolbar toolbar){
    }
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(R.color.theme_statusbar);
        // 在设置内容View之前调用
        doBeforeSetContentView();
        // 设置布局
        if (getLayoutResId() != 0) {
            setContentView(getLayoutResId());
//            initPresenter();
            initToolbar();
            initView(savedInstanceState);
            initData(savedInstanceState);
            initClick();
            processLogic();
        }
    }
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void initToolbar(){
        //更严谨是通过反射判断是否存在Toolbar
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if (mToolbar != null){
            supportActionBar(mToolbar);
            setUpToolbar(mToolbar);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null){
            mDisposable.dispose();
        }
    }
    protected ActionBar supportActionBar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(
                (v) -> finish()
        );
        return actionBar;
    }

    protected void setStatusBarColor(int statusColor){
        StatusBarCompat.compat(this, ContextCompat.getColor(this, statusColor));
    }
}