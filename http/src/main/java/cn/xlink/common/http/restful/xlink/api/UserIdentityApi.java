package cn.xlink.common.http.restful.xlink.api;


import cn.xlink.common.http.restful.xlink.mode.UserIdentity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 用户身份接口
 *
 * @author sswukang on 2016/11/10 10:13
 * @version 1.0
 */
interface UserIdentityApi {

    class Builder {
        static Retrofit createRetrofit(String baseUrl, boolean logging) {

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            if (logging) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                builder.client(client);
            }

            return builder.build();
        }
    }

    /**
     * 1.使用邮箱注册新账号
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_register")
    Call<UserIdentity.UserEmailRegisterResponse> userEmailRegister(@Body UserIdentity.UserEmailRegisterRequest request);

    /**
     * 1.使用邮箱注册新账号
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_register")
    Observable<UserIdentity.UserEmailRegisterResponse> userEmailRegisterObservable(@Body UserIdentity.UserEmailRegisterRequest request);


    /**
     * 2.激活邮箱账号
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_email_activate")
    Call<Void> userEmailActivate(@Body UserIdentity.UserEmailActivateRequest request);

    /**
     * 2.激活邮箱账号
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_email_activate")
    Observable<Void> userEmailActivateObservable(@Body UserIdentity.UserEmailActivateRequest request);


    /**
     * 3.发送注册手机验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_register/verifycode")
    Call<Void> userRegisterVerifyCode(@Body UserIdentity.UserRegisterVerifyCodeRequest request);

    /**
     * 3.发送注册手机验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_register/verifycode")
    Observable<Void> userRegisterVerifyCodeObservable(@Body UserIdentity.UserRegisterVerifyCodeRequest request);


    /**
     * 4. 请求或刷新发送注册短信的图片验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_register/captcha")
    Call<UserIdentity.UserRegisterCaptchaResponse> userRegisterCaptcha(@Body UserIdentity.UserRegisterCaptchaCaptchaRequest request);

    /**
     * 4. 请求或刷新发送注册短信的图片验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_register/captcha")
    Observable<UserIdentity.UserRegisterCaptchaResponse> userRegisterCaptchaObservable(@Body UserIdentity.UserRegisterCaptchaCaptchaRequest request);


    /**
     * 5.通过手机验证码注册新账号
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_register")
    Call<UserIdentity.UserPhoneRegisterResponse> userPhoneRegister(@Body UserIdentity.UserPhoneRegisterRequest request);

    /**
     * 5.通过手机验证码注册新账号
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_register")
    Observable<UserIdentity.UserPhoneRegisterResponse> userPhoneRegisterObservable(@Body UserIdentity.UserPhoneRegisterRequest request);


    /**
     * 6.登录与认证
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_auth")
    Call<UserIdentity.UserAuthResponse> userAuth(@Body UserIdentity.UserAuthRequest request);

    /**
     * 6.登录与认证
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user_auth")
    Observable<UserIdentity.UserAuthResponse> userAuthObservable(@Body UserIdentity.UserAuthRequest request);


    /**
     * 11.找回密码
     * A.请求密码找回
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/password/forgot")
    Call<Void> userPasswordForgot(@Body UserIdentity.UserPasswordForgotRequest request);

    /**
     * 11.找回密码
     * A.请求密码找回
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/password/forgot")
    Observable<Void> userPasswordForgotObservable(@Body UserIdentity.UserPasswordForgotRequest request);


    /**
     * 11.找回密码
     * B.请求或刷新请求密码找回所需的图片验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/password/captcha")
    Call<UserIdentity.UserPasswordCaptchaResponse> userPasswordCaptcha(@Body UserIdentity.UserPasswordCaptchaRequest request);

    /**
     * 11.找回密码
     * B.请求或刷新请求密码找回所需的图片验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/password/captcha")
    Observable<UserIdentity.UserPasswordCaptchaResponse> userPasswordCaptchaObservable(@Body UserIdentity.UserPasswordCaptchaRequest request);


    /**
     * 11.找回密码
     * C.通过验证码重置密码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/password/foundback")
    Call<Void> userPasswordFoundBack(@Body UserIdentity.UserPasswordFoundBackRequest request);

    /**
     * 11.找回密码
     * C.通过验证码重置密码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/password/foundback")
    Observable<Void> userPasswordFoundBackObservable(@Body UserIdentity.UserPasswordFoundBackRequest request);


    /**
     * 12.校验手机验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/verifycode/verify")
    Call<UserIdentity.UserVerifyCodeVerifyResponse> userVerifyCodeVerify(@Body UserIdentity.UserVerifyCodeVerifyRequest request);

    /**
     * 12.校验手机验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/verifycode/verify")
    Observable<UserIdentity.UserVerifyCodeVerifyResponse> userVerifyCodeVerifyObservable(@Body UserIdentity.UserVerifyCodeVerifyRequest request);

}
