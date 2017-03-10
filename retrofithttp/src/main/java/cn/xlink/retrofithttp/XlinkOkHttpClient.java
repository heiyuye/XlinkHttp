package cn.xlink.retrofithttp;

import java.util.concurrent.TimeUnit;

import cn.xlink.retrofithttp.log.LogUtil;
import cn.xlink.retrofithttp.log.LoggerInterceptor;
import okhttp3.OkHttpClient;

/**
 * Created by liucr on 2017/2/16.
 */

public class XlinkOkHttpClient {

    private static XlinkOkHttpClient mInstance;

    private OkHttpClient okHttpClient;

    public XlinkOkHttpClient() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor(LogUtil.TAG, true))
                .build();
    }

    public static XlinkOkHttpClient getInstance() {
        if (mInstance == null) {
            synchronized (XlinkOkHttpClient.class) {
                if (mInstance == null) {
                    mInstance = new XlinkOkHttpClient();
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
