package com.gaos.book.catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gaos.book.R;
import com.gaos.book.api.ApiFactory;
import com.gaos.book.api.ProgressObserver;
import com.gaos.book.base.BaseActivity;
import com.gaos.book.model.BookInfo;

import java.util.List;

/**
* 目录
* @author gaos
* created at 2021/2/24 16:58
*/
public class CatalogActivity extends BaseActivity {

    private int book_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        BookInfo bookinfo = (BookInfo)intent.getSerializableExtra("bookinfo");
        String name = bookinfo.getBook_name();
        String author = bookinfo.getBook_author();
        String introduce = bookinfo.getBook_introduce();
        book_id = bookinfo.getBook_id();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

//        ApiFactory.getCatalogList(book_id)
//                .subscribe(new ProgressObserver<List<BookInfo>>() {
//                    @Override
//                    public void onSuccess(List<BookInfo> result) {
//                        mAdapter.addRes(result);
//                    }
//
//                    @Override
//                    public void onFailure(Throwable e, int errcode, String errormsg) {
//                        showToast(errormsg);
//                    }
//                });
    }
}