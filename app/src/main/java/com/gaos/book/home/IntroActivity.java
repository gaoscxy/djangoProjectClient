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

    public static final String RESULT_IS_COLLECTED = "result_is_collected";
    private static final int REQUEST_READ = 1;
    private boolean isCollected = false;
    private long book_id;

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
//    private CatalogInfo mCatalogInfo;
    private BookInfo bookinfo;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_intro;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bookinfo = BookRepository.getInstance().getCollBook(book_id);
        if(bookinfo != null){
            isCollected = true;
            readBtn.setText("继续阅读");
        }else{
            Intent intent = getIntent();
            bookinfo = (BookInfo)intent.getParcelableExtra("bookinfo");
        }
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

    }

    @Override
    protected void initClick() {
        super.initClick();
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadActivity.startActivity(IntroActivity.this,bookinfo, isCollected);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //如果进入阅读页面收藏了，页面结束的时候，就需要返回改变收藏按钮
        if (requestCode == REQUEST_READ) {
            if (data == null) {
                return;
            }

            isCollected = data.getBooleanExtra(RESULT_IS_COLLECTED, false);

            if (isCollected) {
//                mTvChase.setText(getResources().getString(R.string.nb_book_detail_give_up));
//                //修改背景
//                Drawable drawable = getResources().getDrawable(R.drawable.shape_common_gray_corner);
//                mTvChase.setBackground(drawable);
//                //设置图片
//                mTvChase.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.ic_book_list_delete), null,
//                        null, null);
                readBtn.setText("继续阅读");
            }
        }
    }
}