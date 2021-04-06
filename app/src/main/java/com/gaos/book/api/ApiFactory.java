package com.gaos.book.api;

import com.gaos.book.base.BaseBean;
import com.gaos.book.model.BookInfo;
import com.gaos.book.model.CatalogInfo;
import com.gaos.book.model.VersionInfo;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by gaos on 2017/8/8.
 */

public class ApiFactory {

//    //登录
//    public static Flowable<BaseBean<User>> userLogin(String username, String password) {
//        return Api.getInstance().service.save(title, name,publisher_id).compose(RxSchedulers.io_main());
//    }
//

    /**
    * 保存图书
    * @author gaos
    */
    public static Observable<BaseBean<String>> save(String title, String name, int publisher_id) {
        return Api.getInstance().service.save(title, name,publisher_id).compose(RxSchedulers.observableIO2Main());

    }
    public static Observable<BaseBean<List<BookInfo>>> getBookList(int page) {
        return Api.getInstance().service.getBookList(page).compose(RxSchedulers.observableIO2Main());

    }
    public static Observable<BaseBean<List<BookInfo>>> getSearchBookList(String keyword) {
        return Api.getInstance().service.getSearchBookList(keyword).compose(RxSchedulers.observableIO2Main());

    }
    public static Observable<BaseBean<List<CatalogInfo>>> getCatalogList(long book_id) {
        return Api.getInstance().service.getCatalogList(book_id).compose(RxSchedulers.observableIO2Main());

    }
    public static Observable<BaseBean<String>> getChapterInfo(String path) {
        return Api.getInstance().service.getChapterInfo(path).compose(RxSchedulers.observableIO2Main());
    }
    public static Observable<VersionInfo> getVersion() {
        return Api.getInstance().service.getVersion().compose(RxSchedulers.observableIO2Main());
    }
    public static Observable<BaseBean<String>> postRecommend(String name,String tel) {
        return Api.getInstance().service.postRecommend(name,tel).compose(RxSchedulers.observableIO2Main());
    }

    public static Observable<BaseBean<String>> isMarketPass() {
        return Api.getInstance().service.isMarketPass().compose(RxSchedulers.observableIO2Main());
    }


}
