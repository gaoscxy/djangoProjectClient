package com.gaos.book.presenter;


import com.gaos.book.api.ApiFactory;
import com.gaos.book.api.ProgressObserver;
import com.gaos.book.base.RxPresenter;
import com.gaos.book.model.BookInfo;
import com.gaos.book.model.VersionInfo;
import com.gaos.book.presenter.contract.MainContract;
import com.gaos.book.presenter.contract.RecommendContract;
import com.gaos.book.utils.ToastUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author gaos
 * created at 2021/2/25 16:02
 */
public class RecommendPresenter extends RxPresenter<RecommendContract.View>
        implements RecommendContract.Presenter {
    private static final String TAG = "RecommendPresenter";

    @Override
    public void postRecommend(String name, String tel) {
        ApiFactory.postRecommend(name,tel)
                .subscribe(new ProgressObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                        mView.showResult(result);
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
