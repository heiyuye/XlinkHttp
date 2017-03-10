package cn.xlink.common.http.restful.xlink.api;

import com.google.gson.GsonBuilder;

import cn.xlink.common.http.restful.AccessTokenIntercept;
import cn.xlink.common.http.restful.xlink.AuthProvider;
import cn.xlink.common.http.restful.xlink.mode.UserIdentity;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 用户身份接口中需要Access-Token部分
 *
 * @author sswukang on 2016/11/10 16:14
 * @version 1.0
 */
interface UserIdentityApi2 {

    class Builder {
        static Retrofit createRetrofit(String baseUrl, final AuthProvider authProvider, boolean logging) {

            Retrofit.Builder builder = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()));

            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            if (authProvider != null) {
                clientBuilder.addInterceptor(new AccessTokenIntercept(authProvider));
            }
            if (logging) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                clientBuilder.addInterceptor(loggingInterceptor);
            }

            OkHttpClient client = clientBuilder.build();
            builder.client(client);
            return builder.build();
        }
    }

    /**
     * 7.刷新凭证
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/token/refresh")
    Call<UserIdentity.UserTokenRefreshResponse> userTokenRefresh(@Body UserIdentity.UserTokenRefreshRequest request);

    /**
     * 7.刷新凭证
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/token/refresh")
    Observable<UserIdentity.UserTokenRefreshResponse> userTokenRefreshObservable(@Body UserIdentity.UserTokenRefreshRequest request);


    /**
     * 8.获取用户详细信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/user/{user_id}")
    Call<UserIdentity.GetUserResponse> getUser(@Path("user_id") int userId);

    /**
     * 8.获取用户详细信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/user/{user_id}")
    Observable<UserIdentity.GetUserResponse> getUserObservable(@Path("user_id") int userId);


    /**
     * 9.修改用户基本信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/user/{user_id}")
    Call<Void> putUser(@Path("user_id") int userId, @Body UserIdentity.PutUserRequest request);

    /**
     * 9.修改用户基本信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/user/{user_id}")
    Observable<Void> putUserObservable(@Path("user_id") int userId, @Body UserIdentity.PutUserRequest request);


    /**
     * 10.修改密码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/user/password/reset")
    Call<Void> userPasswordReset(@Body UserIdentity.UserPasswordResetRequest request);

    /**
     * 10.修改密码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/user/password/reset")
    Observable<Void> userPasswordResetObservable(@Body UserIdentity.UserPasswordResetRequest request);


    /**
     * 13.修改手机号
     * 1).请求发送验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/phone/sms_verifycode")
    Call<Void> userPhoneSmsVerifyCode(@Path("user_id") int userId, @Body UserIdentity.UserPhoneSmsVerifyCodeRequest request);

    /**
     * 13.修改手机号
     * 1).请求发送验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/phone/sms_verifycode")
    Observable<Void> userPhoneSmsVerifyCodeObservable(@Path("user_id") int userId, @Body UserIdentity.UserPhoneSmsVerifyCodeRequest request);


    /**
     * 13.修改手机号
     * 2).请求用于发送短信的图片验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/phone/sms_captcha")
    Call<UserIdentity.UserPhoneSmsCaptchaResponse> userPhoneSmsCaptcha(@Path("user_id") int userId, @Body UserIdentity.UserPhoneSmsCaptchaRequest request);

    /**
     * 13.修改手机号
     * 2).请求用于发送短信的图片验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/phone/sms_captcha")
    Observable<UserIdentity.UserPhoneSmsCaptchaResponse> userPhoneSmsCaptchaObservable(@Path("user_id") int userId, @Body UserIdentity.UserPhoneSmsCaptchaRequest request);


    /**
     * 13.修改手机号
     * 3).通过验证码完成修改
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/phone")
    Call<Void> putUserPhone(@Path("user_id") int userId, @Body UserIdentity.PutUserPhoneRequest request);

    /**
     * 13.修改手机号
     * 3).通过验证码完成修改
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/user/{user_id}/phone")
    Observable<Void> putUserPhoneObservable(@Path("user_id") int userId, @Body UserIdentity.PutUserPhoneRequest request);


    /**
     * 14.修改邮箱
     * 1).发送邮件验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/email_verifycode")
    Call<Void> userEmailVerifyCode(@Path("user_id") int userId, @Body UserIdentity.UserEmailVerifyCodeRequest request);

    /**
     * 14.修改邮箱
     * 1).发送邮件验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/email_verifycode")
    Observable<Void> userEmailVerifyCodeObservable(@Path("user_id") int userId, @Body UserIdentity.UserEmailVerifyCodeRequest request);


    /**
     * 14.修改邮箱
     * 2).请求用于发送邮件的图片验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/email_captcha")
    Call<UserIdentity.UserEmailCaptchaResponse> userEmailCaptcha(@Path("user_id") int userId, @Body UserIdentity.UserEmailCaptchaRequest request);

    /**
     * 14.修改邮箱
     * 2).请求用于发送邮件的图片验证码
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/email_captcha")
    Observable<UserIdentity.UserEmailCaptchaResponse> userEmailCaptchaObservable(@Path("user_id") int userId, @Body UserIdentity.UserEmailCaptchaRequest request);


    /**
     * 14.修改邮箱
     * 3).通过验证码完成修改
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/email")
    Call<Void> putUserEmail(@Path("user_id") int userId, @Body UserIdentity.PutUserEmailRequest request);

    /**
     * 14.修改邮箱
     * 3).通过验证码完成修改
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/user/{user_id}/email")
    Observable<Void> putUserEmailObservable(@Path("user_id") int userId, @Body UserIdentity.PutUserEmailRequest request);


    /**
     * 15.上传用户头像
     */
    @POST("/v2/user/avatar/upload")
    Call<UserIdentity.userAvatarUploadResponse> userAvatarUpload(@Query("avatarType") String avatarType, @Body RequestBody request);

    /**
     * 15.上传用户头像
     */
    @POST("/v2/user/avatar/upload")
    Observable<UserIdentity.userAvatarUploadResponse> userAvatarUploadObservable(@Query("avatarType") String avatarType, @Body RequestBody request);


    /**
     * 16.上传客户端信息
     */
    @POST("/v2/user/{user_id}/client_info")
    Call<Void> userClientInfo(@Path("user_id") int userId, @Body UserIdentity.UserClientInfoRequest request);

    /**
     * 16.上传客户端信息
     */
    @POST("/v2/user/{user_id}/client_info")
    Observable<Void> userClientInfoObservable(@Path("user_id") int userId, @Body UserIdentity.UserClientInfoRequest request);

}
