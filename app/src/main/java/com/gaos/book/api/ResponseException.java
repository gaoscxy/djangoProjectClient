package com.gaos.book.api;

import android.content.Context;
import android.text.TextUtils;

import com.gaos.book.R;
import com.gaos.book.common.GlobalConstant;
import com.gaos.book.common.MyApplication;
import com.google.gson.JsonSyntaxException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.HttpException;


/**
 * 异常信息类
 * @author gaos
 *
 */
public class ResponseException extends Exception {
	private String message;//异常信息
    private int statusCode;//异常代码
    public static final Map<String, String> mHttpStatusCodeMap = new HashMap<String, String>();

	private static final long serialVersionUID = 1L;

	public ResponseException(){}
	
	public ResponseException(String message) {
		this.message = message;
	}

    public ResponseException(int statusCode) {
        this(statusCode, getMessage(statusCode));
    }

    public static String getErrMsg(int statusCode){
	    if(mHttpStatusCodeMap.size() == 0){
            loadMessageData();
        }
	    return getMessage(statusCode);
    }
    public ResponseException(Exception e) {
        super(e);
        loadMessageData();
        statusCode = exceptionToStatusCode(e);
        this.message = getMessage(statusCode);
    }
    public ResponseException(Throwable e) {
        super(e);
        loadMessageData();
        statusCode = exceptionToStatusCode(e);
        this.message = getMessage(statusCode);
    }
    
    public ResponseException(int statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }
    
    public int getStatusCode() {
		return statusCode;
	}
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
    	return this.message;
    }

    protected static void loadData(Context context, int resId, Map<String, String> map) {
        String[] lines = context.getResources().getStringArray(resId);
        for (String line : lines) {
            String[] fields = TextUtils.split(line, "=");
            if (fields == null || fields.length != 2) {
                continue;
            }

            map.put(fields[0], fields[1]);
        }
    }
    
    /**
     * 
     * <pre>
     * 加载异常信息
     * </pre>
     *
     */
    public static void loadMessageData() {
        loadData(MyApplication.getContext(), R.array.status_codes, mHttpStatusCodeMap);
    }
    
    public synchronized static String getMessage(int statusCode) {
        String value = null;
        
        String key = String.valueOf(statusCode);
        if (mHttpStatusCodeMap.containsKey(key)) {
            value = mHttpStatusCodeMap.get(key);
        } else {
            value = mHttpStatusCodeMap.get(String.valueOf(GlobalConstant.StatusCodeConstant.UNKNOWN_EXCEPTION));
        }

        return value;
    }
    
    protected static int exceptionToStatusCode(Throwable e) {
        int statusCode = GlobalConstant.StatusCodeConstant.UNKNOWN_EXCEPTION;
        if (e instanceof UnknownHostException) {
            statusCode = GlobalConstant.StatusCodeConstant.UNKNOWN_HOST;
        } else if (e instanceof ConnectException) {
            statusCode = GlobalConstant.StatusCodeConstant.NETWORK_NOT_ACCESS;
        } else if (e instanceof HttpException) {
            statusCode = GlobalConstant.StatusCodeConstant.SERVER_ERROR;
        } else if (e instanceof IllegalArgumentException) {
            statusCode = GlobalConstant.StatusCodeConstant.ILLEGAL_ARGUMENT;
        } else if (e instanceof IllegalStateException) {
            statusCode = GlobalConstant.StatusCodeConstant.ILLEGAL_STATE;
        } else if (e instanceof SocketTimeoutException) {
            statusCode = GlobalConstant.StatusCodeConstant.SOCKET_TIMEOUT_EXCEPTION;
        } else if (e instanceof ConnectTimeoutException) {
            statusCode = GlobalConstant.StatusCodeConstant.SOCKET_TIMEOUT_EXCEPTION;
        } else if (e instanceof SocketException) {
            statusCode = GlobalConstant.StatusCodeConstant.SOCKET_EXCEPTION;
        } else if (e instanceof JSONException || e instanceof JsonSyntaxException) {
            statusCode = GlobalConstant.StatusCodeConstant.PARSE_ERROR;
        } else if (e instanceof XmlPullParserException) {
            statusCode = GlobalConstant.StatusCodeConstant.PARSE_ERROR;
        } else if (e instanceof UnsupportedEncodingException) {
            statusCode = GlobalConstant.StatusCodeConstant.PARSE_ERROR;
        } else if (e instanceof IOException) {
            statusCode = GlobalConstant.StatusCodeConstant.IO_EXCEPTION;
        } else if (e instanceof ResponseException) {
        	statusCode  = ((ResponseException) e).getStatusCode();
        }

        return statusCode;
    }
}
