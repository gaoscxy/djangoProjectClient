package com.gaos.book.common;


import com.gaos.book.utils.storage.StorageUtil;

import java.io.File;

/**
 * 全局常量
 * Created by gaos on 2017/8/2.
 */
public class GlobalConstant {

    //android手机
    public static int machineCode = 100001;
    public static int applyCode = 3;

    public static final int REQUEST_CODE_CHANGEINFO = 1;
    public static final int RESULT_CODE_CHANGEINFO = 2;
    public static final int SPEAK_STEP = 500;


    public static final String FONT_STYLE = "隶书.ttf";

    public static final int REQUEST_CODE_LOGIN = 100;
    public static final int RESULT_CODE_LOGIN_SUCCESS = 101;
    public static final int RESULT_CODE_LOGIN_FAILED = 102;

    public static int PAGESIZE = 15;//列表每次加载的步长
    public static final String DOCUMENT_SAVE_PATH = StorageUtil.getAppDirectory(MyApplication.getContext()) + File.separator + "document";
    public static final String IMAGE_SAVA_PATH = StorageUtil.getAppDirectory(MyApplication.getContext()) + File.separator + "images";

    public static final String AR_KEY = "7MMEgasCRiVFQVKcYzXuYMPXFct1KxVCR03naxxue81NzkEQ1j2FS1O2INEqamogZmhc0kf5EKsJdhQ5ohrdQ2U6lG5OcnFJVp9sniWcCNgwIM8T58drdnFadKSSVk5CFnHzyUsJlWweOxEZlS0H2NbbbJRuzbePDglyXsSX5QLalEsbshr2iJoUt4EqSMyHC9ZYkBPD";

    //bugly
    public static final String BUGLY_APPID = "301e03d8c3";
    public static final String BUGLY_APPKEY = "65ba7496-0a7c-49d1-a8dd-38a5863c2096";


    public interface URLContact {
        //开发环境
        String BASE_URL = "http://192.168.6.131:8000";
        String SAVEBOOK = BASE_URL + "/saveBook/";
        String GET_BOOK_LIST = BASE_URL + "/getBookList/";
        String GET_SEARCH_BOOK_LIST = BASE_URL + "/getSearchBookList/";
        String GET_CATALOG_LIST = BASE_URL + "/getCatalogList/";
        String GET_CHAPTER_INFO = BASE_URL + "/getBookDetails/";
        String GET_VERSION = BASE_URL + "/getVersion/";
        String SAVE_RECOMMEND_BOOK = BASE_URL + "/saveRecommendBook/";
    }

    //静态web页面url
    public static final String URL_ABOUT_US = URLContact.BASE_URL + "/fzps/base/common/about.html";               //当前版本
    public static final String URL_HOT_LINE = URLContact.BASE_URL + "/fzps/base/common/hotline.html";             //热线咨询
    public static final String URL_USER_PROTOCOL = URLContact.BASE_URL + "/fzps/user/t/user_1_t.html";            //用户服务协议
    public static final String URL_USER_PRIVACY = URLContact.BASE_URL + "/fzps/user/t/user_3_t.html";            //隐私政策
    public static final String URL_ARTIFICIAL = URLContact.BASE_URL + "/fzps/base/common/artificial.html";        //人工申诉
    public static final String URL_ABOUT_US_1 = URLContact.BASE_URL + "/fzps/base/common/about_1.html";           //关于我们
    public static final String URL_ADD_LAWYER = URLContact.BASE_URL + "/fzps/user/t/user_2_t.html";//律师认证协议
    public static final String URL_INVITE_FRIEND_SHARE = URLContact.BASE_URL + "/fzps/invitation/t/invitation_1_t.html?userAccount=";//邀请好友页面的分享地址

    //error code状态码
    public interface StatusCodeConstant {
        public static final int UNKNOWN_EXCEPTION = 0;
        public static final int SUCCESS = 200; // 成功
        public static final int NETWORK_NOT_ACCESS = 106; // Network can not
        public static final int SUCCESS_PARTIAL_CONTENT = 206; // 特殊请求成功
        public static final int BAD_REQUEST = 400; // 请求出现语法错误
        public static final int UNAUTHORIZED = 401; // 用户登录过期
        public static final int REQUEST_FORB = 403; // 请求被拒绝
        public static final int NOT_FOUND = 404; // 没有找到指定的文件或目录
        public static final int SERVER_ERROR = 500; // 服务器异常
        public static final int SQL_EXCEPTION = 600;// SQL语句异常
        public static final int DB_CONNECTION_EXCEPTION = 603;// 数据库连接异常
        public static final int NO_SDCARD = 667; // 没有SDCARD异常
        // access the result
        // failed to upload
        public static final int PARSE_ERROR = 903; // can't parse the response
        // result
        public static final int SOCKET_EXCEPTION = 904; // Network can not
        // access cause
        // SocketException
        public static final int SOCKET_TIMEOUT_EXCEPTION = 905; // Network can
        // not access
        // cause
        // SocketTimeoutException
        public static final int IO_EXCEPTION = 906; // Network can not access
        // cause IOException
        public static final int UNKNOWN_HOST = 907; // Unknown Host Exception
        public static final int ILLEGAL_STATE = 908; // IllegalStateException
        public static final int ILLEGAL_ARGUMENT = 909; // IllegalArgumentException
        public static final int OFFLINE = 1000;
        public static final int NO_ROLE = 408; // 当前用户没有权限访问系统
        public static final int UNKNOWUSER = 401; // 用户未登录
    }
}




