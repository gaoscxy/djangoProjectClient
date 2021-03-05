package com.gaos.book.presenter;


import com.gaos.book.api.ApiFactory;
import com.gaos.book.api.ProgressObserver;
import com.gaos.book.base.BaseBean;
import com.gaos.book.base.RxPresenter;
import com.gaos.book.model.BookInfo;
import com.gaos.book.model.CatalogInfo;
import com.gaos.book.model.local.BookRepository;
import com.gaos.book.presenter.contract.MainContract;
import com.gaos.book.utils.LogUtils;
import com.gaos.book.utils.ToastUtils;
import com.gaos.book.view.widget.page.TxtChapter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author gaos
 * created at 2021/2/25 16:02
 */
public class MainPresenter extends RxPresenter<MainContract.View>
        implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";

    @Override
    public void getBookList() {
        ApiFactory.getBookList()
                .subscribe(new ProgressObserver<List<BookInfo>>() {
                    @Override
                    public void onSuccess(List<BookInfo> result) {
                        mView.showBookList(result);
                    }

                    @Override
                    public void onFailure(Throwable e, int errcode, String errormsg) {
                        ToastUtils.show(errormsg);
                    }
                });
    }

    @Override
    public void getSearchBookList(String keyword) {

        ApiFactory.getSearchBookList(keyword)
                .subscribe(new ProgressObserver<List<BookInfo>>() {
                    @Override
                    public void onSuccess(List<BookInfo> result) {
                        mView.showSearchBookResult(result);
                    }

                    @Override
                    public void onFailure(Throwable e, int errcode, String errormsg) {
                        ToastUtils.show(errormsg);
                    }
                });
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
