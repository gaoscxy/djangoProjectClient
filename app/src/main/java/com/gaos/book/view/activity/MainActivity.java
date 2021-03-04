package com.gaos.book.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gaos.book.R;
import com.gaos.book.api.ApiFactory;
import com.gaos.book.api.ProgressObserver;
import com.gaos.book.base.BaseActivity;
import com.gaos.book.view.adapter.BookListAdapter;
import com.gaos.book.model.BookInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.list)
    RecyclerView mRecycler;
    BookListAdapter mAdapter;
    View header;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mAdapter = new BookListAdapter(this, new ArrayList<>());
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);
        header = LayoutInflater.from(this).inflate(R.layout.home_header, mRecycler, false);
        mAdapter.setHeaderView(header);
        mAdapter.setOnItemClickListener((position, data) -> {
            Intent intent = new Intent(this, IntroActivity.class);
            intent.putExtra("bookinfo",(BookInfo)data);
            startActivity(intent);
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        getBookList();
    }

    public void getBookList() {
        ApiFactory.getBookList()
                .subscribe(new ProgressObserver<List<BookInfo>>() {
                    @Override
                    public void onSuccess(List<BookInfo> result) {
                        mAdapter.addRes(result);
                    }

                    @Override
                    public void onFailure(Throwable e, int errcode, String errormsg) {
                        showToast(errormsg);
                    }
                });
    }

//    @OnClick(R.id.button)
//    public void putData() {
//        String title = textView.getText().toString();
//        String name = textView2.getText().toString();
//        int publisher_id = 1;
//        // 请求参数
//        ApiFactory.save(title, name, publisher_id)
//                .subscribe(new ProgressObserver<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        if (result.equals("done")) {
//                            showToast("insert success!");
////                            User user = SpUtil.getUser();
////                            user.getUserprofilesummary().setGender(Gender + "");
////                            SpUtil.setUser(user);
////                            setResult(RESULT_OK);
////                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Throwable e, int errcode, String errormsg) {
//                        showToast(errormsg);
//                    }
//                });
//
//    }
}