package com.gaos.book.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gaos.book.R;
import com.gaos.book.base.BaseMVPActivity;
import com.gaos.book.model.VersionInfo;
import com.gaos.book.presenter.MainPresenter;
import com.gaos.book.presenter.contract.MainContract;
import com.gaos.book.utils.DialogUtils;
import com.gaos.book.utils.SpUtil;
import com.gaos.book.view.adapter.BookListAdapter;
import com.gaos.book.model.BookInfo;
import com.gaos.book.view.widget.ClearEditText;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

import butterknife.BindView;

import static android.view.inputmethod.EditorInfo.*;

public class MainActivity extends BaseMVPActivity<MainContract.Presenter>
        implements MainContract.View {
    @BindView(R.id.list)
    RecyclerView mRecycler;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
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
            intent.putExtra("bookinfo", (BookInfo) data);
            startActivity(intent);
        });
    }

    @Override
    protected void initClick() {
        super.initClick();
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == IME_ACTION_SEARCH) {
                String search = etSearch.getText().toString().trim();
                if (search.length() != 0) {
                    mPresenter.getSearchBookList(etSearch.getText().toString());
                } else {
                    //当搜索框为空的时候，清除搜索条件重新搜索
                    mPresenter.getBookList();
                }
                return true;
            }
            return false;
        });
        RxTextView.afterTextChangeEvents(etSearch)
                .subscribe(textViewAfterTextChangeEvent -> {

//                    String etSearchText = etSearch.getText().toString();
//                    if(etSearchText.length() != 0){
                        if (textViewAfterTextChangeEvent.editable().length() == 0) {
                            //当搜索框为空的时候，清除搜索条件重新搜索
                            mPresenter.getBookList();
                        }
//                    }
                });
    }

    public void clickAbout(View view){
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.processLogic();
        mPresenter.getBookList();
        mPresenter.getVersion();
    }

    @Override
    protected MainContract.Presenter bindPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showBookList(List<BookInfo> bookList) {
        mAdapter.updateRes(bookList);
    }

    @Override
    public void showSearchBookResult(List<BookInfo> bookList) {

        if (bookList.size() == 0) {
            //清空搜索条件并跳转到推荐图书页面
            Intent intent = new Intent(this, RecommendActivity.class);
            startActivity(intent);
            etSearch.setText("");
        } else {
            mAdapter.updateRes(bookList);
        }
    }

    @Override
    public void showVersionMsg(VersionInfo versionInfo) {
        String msg = versionInfo.getUpdatemsg();
        String version_code = versionInfo.getVersion_code();
        if (SpUtil.getData("version_code").equals(version_code)) {
            return;
        }
        SpUtil.setData("version_code", version_code);
        DialogUtils.singleBtnDialogForMsg(MainActivity.this, msg, () -> {

        }, () -> {

        });
    }
}