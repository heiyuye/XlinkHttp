package cn.xlink.retrofithttp.xlinkretrofithttp;

import java.util.HashMap;
import java.util.Map;

import cn.xlink.retrofithttp.entity.daoentity.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;
import cn.xlink.retrofithttp.entity.UserAuth;

/**
 * Created by liucr on 2017/2/5.
 */

public interface XlinkService {

    /**
     * 通过邮箱/手机号注册
     *
     * @param body
     * @return
     */
    @POST("v2/user_register")
    Observable<HashMap> register(@Body HashMap body);

    @POST("v2/user_register/verifycode")
    Observable<HashMap> getRegisterVerifycode(@Body HashMap body);

    @POST("v2/user_register/captcha")
    Observable<HashMap> getImageVerifycode(@Body HashMap body);

    @POST("v2/user_auth")
    Observable<UserAuth> login(@Body HashMap body);

    @POST("v2/user_auth")
    Observable<HashMap> refreshToken(@Header("Access-Token") String accessToken);

    @GET("v2/user/{user_id}?filter=setting")
    Observable<User> getUser(@Header("Access-Token") String accessToken, @Path("user_id") String user_id);

    @PUT("v2/user/{user_id}")
    Observable<Void> setUserBaseMsg(@Header("Access-Token") String accessToken, @Path("user_id") String user_id, @Body HashMap body);

    @PUT("v2/user/password/reset")
    Observable<Void> changePassword(@Header("Access-Token") String accessToken, @Body HashMap body);

    @POST("v2/user/password/forgot")
    Observable<Void> forgotPassword(@Body HashMap body);

    @POST("v2/user/password/foundback")
    Observable<Void> foundbackPassword(@Body HashMap body);

    @POST("v2/user/{user_id}/phone/sms_captcha")
    Observable<Void> getChangePhoneVerifycode(@Body HashMap body);

    @PUT("v2/user/{user_id}/phone")
    Observable<Void> changePhoneNumber(@Body HashMap body);

    @Multipart
    @POST("v2/user/avatar/upload?avatarType={xxxx}")
    Observable<HashMap> uploadAvatar(@Header("Access-Token") String accessToken, @Body byte[] body);
}
