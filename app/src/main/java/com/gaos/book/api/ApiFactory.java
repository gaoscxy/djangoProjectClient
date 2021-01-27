package com.gaos.book.api;

import com.gaos.book.BookInfo;
import com.gaos.book.base.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


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
    public static Observable<BaseBean<List<BookInfo>>> getBookList() {
        return Api.getInstance().service.getBookList().compose(RxSchedulers.observableIO2Main());

    }


}
