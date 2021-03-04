package com.gaos.book.presenter.contract;

import com.gaos.book.base.BaseContract;
import com.gaos.book.model.BookChapterBean;
import com.gaos.book.model.CatalogInfo;
import com.gaos.book.widget.page.TxtChapter;

import java.util.List;

/**
*
* @author gaos
* created at 2021/2/25 11:44
*/
public interface ReadContract extends BaseContract {
    interface View extends BaseContract.BaseView {
        void showCategory(List<CatalogInfo> bookChapterList);
        void finishChapter();
        void errorChapter();
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void loadCategory(long bookId);
        void loadChapter(long bookId,List<TxtChapter> bookChapterList);
    }
}
