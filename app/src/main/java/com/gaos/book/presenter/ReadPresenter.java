package com.gaos.book.presenter;



import com.gaos.book.api.ApiFactory;
import com.gaos.book.api.ProgressObserver;
import com.gaos.book.base.BaseBean;
import com.gaos.book.base.RxPresenter;
import com.gaos.book.model.BookChapterBean;
import com.gaos.book.model.BookInfo;
import com.gaos.book.model.CatalogInfo;
import com.gaos.book.model.local.BookRepository;
import com.gaos.book.presenter.contract.ReadContract;
import com.gaos.book.utils.LogUtils;
import com.gaos.book.utils.MD5Utils;
import com.gaos.book.utils.RxUtils;
import com.gaos.book.widget.page.TxtChapter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
*
* @author gaos
* created at 2021/2/25 16:02
*/
public class ReadPresenter extends RxPresenter<ReadContract.View>
        implements ReadContract.Presenter {
    private static final String TAG = "ReadPresenter";

    private Disposable mChapterSub;

    @Override
    public void loadCategory(int bookId) {

        ApiFactory.getCatalogList(bookId)
                .subscribe(new ProgressObserver<List<CatalogInfo>>() {
                    @Override
                    public void onSuccess(List<CatalogInfo> result) {
                        mView.showCategory(result);
                    }

                    @Override
                    public void onFailure(Throwable e, int errcode, String errormsg) {
//                        showToast(errormsg);
                        mView.errorChapter();
                    }
                });

    }

//    @Override
//    public void loadChapter(int bookId, List<TxtChapter> bookChapterList) {
//
//    }

    @Override
    public void loadChapter(int bookId, List<TxtChapter> bookChapters) {
        int size = bookChapters.size();

        //取消上次的任务，防止多次加载
        if (mChapterSub != null) {
            mChapterSub.dispose();
        }

        List<Observable<BaseBean<String>>> chapterInfos = new ArrayList<>(bookChapters.size());
        ArrayDeque<String> titles = new ArrayDeque<>(bookChapters.size());

        // 将要下载章节，转换成网络请求。
        for (int i = 0; i < size; ++i) {
            TxtChapter bookChapter = bookChapters.get(i);
            // 网络中获取数据
            Observable<BaseBean<String>> chapterInfo_observable = ApiFactory.getChapterInfo(bookChapter.getLink());
            chapterInfos.add(chapterInfo_observable);
            titles.add(bookChapter.getTitle());
        }

        Observable.concat(chapterInfos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                                String title = titles.poll();
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {
//                                   d.request(Integer.MAX_VALUE);
                                   mChapterSub = d;
                               }

                               @Override
                               public void onNext(@NonNull BaseBean<String> BaseBean) {

                                   //存储数据
                                   BookRepository.getInstance().saveChapterInfo(
                                           bookId , title, BaseBean.getData()
                                   );
                                   mView.finishChapter();
                                   //将获取到的数据进行存储
                                   title = titles.poll();
                               }

                               @Override
                               public void onError(@NonNull Throwable t) {

                                   //只有第一个加载失败才会调用errorChapter
                                   if (bookChapters.get(0).getTitle().equals(title)) {
                                       mView.errorChapter();
                                   }
                                   LogUtils.e(t);
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );

    }

    @Override
    public void detachView() {
        super.detachView();
        if (mChapterSub != null) {
            mChapterSub.dispose();
        }
    }

}
