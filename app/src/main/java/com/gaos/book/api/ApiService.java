package com.gaos.book.api;


import com.gaos.book.BookInfo;
import com.gaos.book.base.BaseBean;
import com.gaos.book.common.GlobalConstant;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by gaos on 2017/8/8.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST(GlobalConstant.URLContact.SAVEBOOK)
    Observable<BaseBean<String>> save(@Field("title") String title,@Field("name") String name,@Field("publisher_id") int publisher_id);

    @GET(GlobalConstant.URLContact.GET_BOOK_LIST)
    Observable<BaseBean<List<BookInfo>>> getBookList();
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
