package com.gaos.book.view.activity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import com.gaos.book.R;
import com.gaos.book.base.BaseActivity;
import com.gaos.book.utils.MPermissionUtils;
import com.gaos.book.view.widget.MyButton;
import com.gaos.book.view.widget.MyTextView;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements MPermissionUtils.OnPermissionListener{
    private TelephonyManager telephonyManager;
    @BindView(R.id.phone)
    MyTextView phone;
    @BindView(R.id.provider)
    MyTextView provider;
    @BindView(R.id.login)
    MyButton login;
    @BindView(R.id.pass)
    MyTextView pass;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String[] permissions = {Manifest.permission.READ_PHONE_STATE};
        MPermissionUtils.requestPermissionsResult(this, 0, permissions, this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }
    public void onLogin(View view){

    }
    public void onPass(View view){

    }
    private String getPhoneNumber() {
        return telephonyManager.getLine1Number();
    }
    public String getProvidersName() {
        String ProvidersName = null;
        String IMSI;// 国际移动用户识别码
        IMSI = telephonyManager.getSubscriberId();
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信
        try {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                ProvidersName = "中国移动";
            } else if (IMSI.startsWith("46001")) {
                ProvidersName = "中国联通";
            } else if (IMSI.startsWith("46003")) {
                ProvidersName = "中国电信";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ProvidersName;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted() {
        String phoneTxt = getPhoneNumber();
        if(!TextUtils.isEmpty(phoneTxt) && phoneTxt.length() > 6 ) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phoneTxt.length(); i++) {
                char c = phoneTxt.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            phone.setText(sb);
        }
        provider.setText("认证服务由" + getProvidersName() + "提供");
    }

    @Override
    public void onPermissionDenied() {
        showToast("当前应用缺少必要权限，可能会导致某些功能无法使用");

    }

}