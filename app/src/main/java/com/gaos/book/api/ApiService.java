package com.gaos.book.api;


import com.gaos.book.base.BaseBean;
import com.gaos.book.common.GlobalConstant;
import com.gaos.book.model.BookChapterBean;
import com.gaos.book.model.BookInfo;
import com.gaos.book.model.CatalogInfo;
import com.gaos.book.model.ChapterInfoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by gaos on 2017/8/8.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST(GlobalConstant.URLContact.SAVEBOOK)
    Observable<BaseBean<String>> save(@Field("title") String title,@Field("name") String name,@Field("publisher_id") int publisher_id);

    @GET(GlobalConstant.URLContact.GET_BOOK_LIST)
    Observable<BaseBean<List<BookInfo>>> getBookList();

    @GET(GlobalConstant.URLContact.GET_CATALOG_LIST)
    Observable<BaseBean<List<BookChapterBean>>> getCatalogList(@Field("book_id") int book_id);

    @GET(GlobalConstant.URLContact.GET_CHAPTER_INFO)
    Observable<BaseBean<ChapterInfoBean>> getChapterInfo(@Field("path") String path);
    //登录
//    @FormUrlEncoded
//    @POST(GlobalConstant.URLContact.USER_LOGIN_URL)
//    Flowable<BaseBean<User>> userLogin(@Field("userAccount") String userAccount,
//                                       @Field("userPassword") String userPassword);

//    @POST(GlobalConstant.URLContact.GET_EXAM_PAPER_INFO)
//    Flowable<BaseBean<String>> getExamPaperInfo(@Query("examId") long page, @Query("rd") int rd,
//                                                @Query("userAccount") String userAccount,
//                                                @Query("sid") String sid);

}
