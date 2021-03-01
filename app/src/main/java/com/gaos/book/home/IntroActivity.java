package com.gaos.book.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gaos.book.R;
import com.gaos.book.base.BaseActivity;
import com.gaos.book.model.BookInfo;
import com.gaos.book.model.CatalogInfo;
import com.gaos.book.model.local.BookRepository;
import com.gaos.book.read.ReadActivity;
import com.gaos.book.widget.MyButton;
import com.gaos.book.widget.MyTextView;

import butterknife.BindView;


/**
 * 简介
 * @author gaos
 * created at 2021/2/24 16:58
 */
public class IntroActivity extends BaseActivity {

    private int book_id;

    @BindView(R.id.name)
    MyTextView nameTv;
    @BindView(R.id.readbtn)
    MyButton readBtn;
    @BindView(R.id.author)
    MyTextView authorTv;
    @BindView(R.id.time)
    MyTextView timeTv;
    @BindView(R.id.intro)
    MyTextView introTv;
    private CatalogInfo mCatalogInfo;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_intro;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        BookInfo bookinfo = (BookInfo)intent.getSerializableExtra("bookinfo");
        String name = bookinfo.getBook_name();
        String author = bookinfo.getBook_author();
        String introduce = bookinfo.getBook_introduce();
        String time = bookinfo.getBook_update_time();
        book_id = bookinfo.getBook_id();
        nameTv.setText(name);
        authorTv.setText(author);
        timeTv.setText("更新时间：" + time);
        introTv.setText(introduce);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mCatalogInfo = BookRepository.getInstance().getCollBook(book_id);

    }

    @Override
    protected void initClick() {
        super.initClick();
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadActivity.startActivity(IntroActivity.this,
                        mCollBookAdapter.getItem(pos), true);
            }
        });
    }
}