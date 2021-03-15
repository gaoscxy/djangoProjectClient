package com.gaos.book.presenter.contract;


import com.gaos.book.model.VersionInfo;

import com.gaos.book.base.BaseContract;
import com.gaos.book.model.BookInfo;
import com.gaos.book.model.CatalogInfo;
import com.gaos.book.view.widget.page.TxtChapter;

import java.util.List;

/**
*
* @author gaos
* created at 2021/2/25 11:44
*/
public interface MainContract extends BaseContract {
    interface View extends BaseView {
        void showBookList(List<BookInfo> bookList);
        void showSearchBookResult(List<BookInfo> bookList);
        void showVersionMsg(VersionInfo versionInfo);
    }

    interface Presenter extends BaseContract.BasePresenter<MainContract.View>{
        void getBookList();
        void getSearchBookList(String keyword);
        void getVersion();
    }
}
