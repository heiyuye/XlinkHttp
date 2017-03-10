package cn.xlink.common.http.restful;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;

import cn.xlink.common.http.XLinkErrorCode;
import cn.xlink.common.http.restful.xlink.AuthProvider;
import cn.xlink.common.http.restful.xlink.api.XLinkHttpApi;
import cn.xlink.common.http.restful.xlink.mode.UserIdentity;
import okhttp3.Interceptor;
import okhttp3.Request;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Response;

/**
 * access token 拦截器
 *
 * @author sswukang on 2016/11/14 11:22
 * @version 1.0
 */
public class AccessTokenIntercept implements Interceptor {
    private AuthProvider authProvider;

    public AccessTokenIntercept(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        // ok http请求
        Request okHttpRequest = chain.request();

        // 通过接口得到AccessToken，并为每个请求添加请求头
        Request tryRequest = okHttpRequest.newBuilder()
                .header("Access-Token", authProvider.getAccessToken()) //
                .build();
        // 发出ok http请求
        okhttp3.Response response = chain.proceed(tryRequest);

        // 请求错误403，尝试拦截
        if (response.code() == 403) {
            Log.d("intercept", "access token 403. response.code():" + response.code());
            // 得到error body
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            String json = source.buffer().clone().readString(Charset.forName("UTF-8"));
            XLinkErrorCode.ErrorWrapper errorWrapper = new Gson()
                    .fromJson(json, XLinkErrorCode.ErrorWrapper.class);

            // 当error code代表无效的Access-Token时，尝试重新认证
            if (errorWrapper.error.code == XLinkErrorCode.ACCESS_TOKEN_INVALID) {
                Log.d("intercept", "access token invaild. now checking user info");
                // 得到认证接口equest
                UserIdentity.UserAuthRequest authRequest = authProvider.authRequest();

                if (authRequest != null) {
                    Log.d("intercept", "access token expired. now refreshing token");
                    // 开始认证http请求
                    Call<UserIdentity.UserAuthResponse> responseCall = XLinkHttpApi
                            .getInstance().userAuth(authRequest);
                    // 通过接口得到请求结果
                    Response<UserIdentity.UserAuthResponse> authResponse = responseCall.execute();

                    // 如果认证通过
                    if (authResponse.isSuccessful()) {
                        Log.d("intercept", "auth success. token:" + authResponse.body().access_token);
                        // 通过接口保存认证结果
                        authProvider.authResponse(authResponse.body());

                        // 用新的Access-Token开始原ok http请求
                        return chain.proceed(okHttpRequest.newBuilder()
                                .header("Access-Token", authResponse.body().access_token)
                                .build());
                    } else {
                        // 通过接口反馈认证失败
                        authProvider.onAuthFailure();
                    }
                }
            }
        }

        // 其他情况直接返回请求
        return response;
    }
}
