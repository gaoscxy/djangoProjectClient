package com.gaos.book.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.gaos.book.R;
import com.gaos.book.base.BaseMVPActivity;
import com.gaos.book.presenter.RecommendPresenter;
import com.gaos.book.presenter.contract.RecommendContract;
import com.gaos.book.view.widget.MyEditText;

import butterknife.BindView;

/**
* 推荐图书
* @author gaos
* created at 2021/3/11 17:52
*/
public class RecommendActivity extends BaseMVPActivity<RecommendContract.Presenter>
                        implements  RecommendContract.View{

    @BindView(R.id.content)
    MyEditText contentEt;
    @BindView(R.id.tel)
    MyEditText telEt;

    @Override
    protected RecommendContract.Presenter bindPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBarColor(R.color.black);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void commitData(View view){
        String content = contentEt.getText().toString();
        String tel = telEt.getText().toString();
        if(TextUtils.isEmpty(content) || TextUtils.isEmpty(tel)){
            showToast("图书名和联系方式不能为空！");
            return;
        }
        if(!isMobile(tel)){
            showToast("请输入正确的手机号码！");
            return;
        }
        mPresenter.postRecommend(content,tel);
    }
    @Override
    public void showResult(String msg) {
        showToast(msg);
        finish();
    }

    public boolean isMobile(String mobiles) {
        /**
         *  移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         *  联通：130、131、132、152、155、156、185、186
         *  电信：133、153、180、189、（1349卫通）
         *  总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "(?:(?:(?:13[0-9])|(?:14[57])|(?:15[0-35-9])|(?:17[36-8])|(?:18[0-9]))\\d{8})|(?:170|171[057-9]\\d{7})";//"[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

}