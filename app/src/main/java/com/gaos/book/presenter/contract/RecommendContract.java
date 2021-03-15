package com.gaos.book.presenter.contract;


import com.gaos.book.base.BaseContract;
import com.gaos.book.model.BookInfo;
import com.gaos.book.model.VersionInfo;

import java.util.List;

/**
* 推荐
* @author gaos
* created at 2021/2/25 11:44
*/
public interface RecommendContract extends BaseContract {
    interface View extends BaseView {
        void showResult(String msg);
    }

    interface Presenter extends BasePresenter<RecommendContract.View>{
        void postRecommend(String name,String tel);
    }
}
