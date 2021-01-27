package com.gaos.book;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gaos.book.api.ApiFactory;
import com.gaos.book.api.ProgressObserver;
import com.gaos.book.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView)
    EditText textView;
    @BindView(R.id.textView2)
    EditText textView2;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.content)
    TextView content;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick(R.id.button2)
    public void getBookList() {
        ApiFactory.getBookList()
                .subscribe(new ProgressObserver<List<BookInfo>>() {
                    @Override
                    public void onSuccess(List<BookInfo> result) {
                        StringBuffer strResult = new StringBuffer();
                        for (BookInfo bookInfo : result) {
                            strResult.append(bookInfo.getTitle() + "\n");
                        }
                        content.setText(strResult);
                    }

                    @Override
                    public void onFailure(Throwable e, int errcode, String errormsg) {
                        showToast(errormsg);
                    }
                });
    }

    @OnClick(R.id.button)
    public void putData() {
        String title = textView.getText().toString();
        String name = textView2.getText().toString();
        int publisher_id = 1;
        // 请求参数
        ApiFactory.save(title, name, publisher_id)
                .subscribe(new ProgressObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("done")) {
                            showToast("insert success!");
//                            User user = SpUtil.getUser();
//                            user.getUserprofilesummary().setGender(Gender + "");
//                            SpUtil.setUser(user);
//                            setResult(RESULT_OK);
//                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, int errcode, String errormsg) {
                        showToast(errormsg);
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}