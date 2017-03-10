package cn.xlink.common.http.restful.xlink.api;


import java.util.List;

import cn.xlink.common.http.XLinkHttpManager;
import cn.xlink.common.http.restful.xlink.mode.DeviceFunction;
import cn.xlink.common.http.restful.xlink.mode.UserIdentity;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * xlink restful api
 *
 * @author sswukang on 2016/11/14 14:56
 * @version 1.0
 */
public class XLinkHttpApi {
    private UserIdentityApi userIdentityApi;
    private UserIdentityApi2 userIdentityApi2;
    private DeviceFunctionApi deviceFunctionApi;

    public void init(XLinkHttpManager.Config httpConfig) {
        userIdentityApi = UserIdentityApi.Builder.createRetrofit(httpConfig.getBaseUrl(),
                httpConfig.isLogging()).create(UserIdentityApi.class);
        userIdentityApi2 = UserIdentityApi2.Builder.createRetrofit(httpConfig.getBaseUrl(),
                httpConfig.getProvider(), httpConfig.isLogging()).create(UserIdentityApi2.class);
        deviceFunctionApi = DeviceFunctionApi.Builder.createRetrofit(httpConfig.getBaseUrl(),
                httpConfig.getProvider(), httpConfig.isLogging()).create(DeviceFunctionApi.class);
    }

    private static class LazyHolder {
        private static final XLinkHttpApi INSTANCE = new XLinkHttpApi();
    }

    private XLinkHttpApi() {
    }

    public static XLinkHttpApi getInstance() {
        return XLinkHttpApi.LazyHolder.INSTANCE;
    }


    /* -------------------------------- UserIdentityApi -------------------------------- */

    public Call<UserIdentity.UserEmailRegisterResponse> userEmailRegister(@Body UserIdentity.UserEmailRegisterRequest request) {
        return userIdentityApi.userEmailRegister(request);
    }

    public Observable<UserIdentity.UserEmailRegisterResponse> userEmailRegisterObservable(@Body UserIdentity.UserEmailRegisterRequest request) {
        return userIdentityApi.userEmailRegisterObservable(request);
    }

    public Call<Void> userEmailActivate(@Body UserIdentity.UserEmailActivateRequest request) {
        return userIdentityApi.userEmailActivate(request);
    }

    public Observable<Void> userEmailActivateObservable(@Body UserIdentity.UserEmailActivateRequest request) {
        return userIdentityApi.userEmailActivateObservable(request);
    }

    public Call<Void> userRegisterVerifyCode(@Body UserIdentity.UserRegisterVerifyCodeRequest request) {
        return userIdentityApi.userRegisterVerifyCode(request);
    }

    public Observable<Void> userRegisterVerifyCodeObservable(@Body UserIdentity.UserRegisterVerifyCodeRequest request) {
        return userIdentityApi.userRegisterVerifyCodeObservable(request);
    }

    public Call<UserIdentity.UserRegisterCaptchaResponse> userRegisterCaptcha(@Body UserIdentity.UserRegisterCaptchaCaptchaRequest request) {
        return userIdentityApi.userRegisterCaptcha(request);
    }

    public Observable<UserIdentity.UserRegisterCaptchaResponse> userRegisterCaptchaObservable(@Body UserIdentity.UserRegisterCaptchaCaptchaRequest request) {
        return userIdentityApi.userRegisterCaptchaObservable(request);
    }

    public Call<UserIdentity.UserPhoneRegisterResponse> userPhoneRegister(@Body UserIdentity.UserPhoneRegisterRequest request) {
        return userIdentityApi.userPhoneRegister(request);
    }

    public Observable<UserIdentity.UserPhoneRegisterResponse> userPhoneRegisterObservable(@Body UserIdentity.UserPhoneRegisterRequest request) {
        return userIdentityApi.userPhoneRegisterObservable(request);
    }

    public Call<UserIdentity.UserAuthResponse> userAuth(@Body UserIdentity.UserAuthRequest request) {
        return userIdentityApi.userAuth(request);
    }

    public Observable<UserIdentity.UserAuthResponse> userAuthObservable(@Body UserIdentity.UserAuthRequest request) {
        return userIdentityApi.userAuthObservable(request);
    }

    public Call<Void> userPasswordForgot(@Body UserIdentity.UserPasswordForgotRequest request) {
        return userIdentityApi.userPasswordForgot(request);
    }

    public Observable<Void> userPasswordForgotObservable(@Body UserIdentity.UserPasswordForgotRequest request) {
        return userIdentityApi.userPasswordForgotObservable(request);
    }

    public Call<UserIdentity.UserPasswordCaptchaResponse> userPasswordCaptcha(@Body UserIdentity.UserPasswordCaptchaRequest request) {
        return userIdentityApi.userPasswordCaptcha(request);
    }

    public Observable<UserIdentity.UserPasswordCaptchaResponse> userPasswordCaptchaObservable(@Body UserIdentity.UserPasswordCaptchaRequest request) {
        return userIdentityApi.userPasswordCaptchaObservable(request);
    }

    public Call<Void> userPasswordFoundBack(@Body UserIdentity.UserPasswordFoundBackRequest request) {
        return userIdentityApi.userPasswordFoundBack(request);
    }

    public Observable<Void> userPasswordFoundBackObservable(@Body UserIdentity.UserPasswordFoundBackRequest request) {
        return userIdentityApi.userPasswordFoundBackObservable(request);
    }

    public Call<UserIdentity.UserVerifyCodeVerifyResponse> userVerifyCodeVerify(@Body UserIdentity.UserVerifyCodeVerifyRequest request) {
        return userIdentityApi.userVerifyCodeVerify(request);
    }

    public Observable<UserIdentity.UserVerifyCodeVerifyResponse> userVerifyCodeVerifyObservable(@Body UserIdentity.UserVerifyCodeVerifyRequest request) {
        return userIdentityApi.userVerifyCodeVerifyObservable(request);
    }


    /* -------------------------------- UserIdentityApi2 -------------------------------- */

    public Call<UserIdentity.UserTokenRefreshResponse> userTokenRefresh(@Body UserIdentity.UserTokenRefreshRequest request) {
        return userIdentityApi2.userTokenRefresh(request);
    }

    public Observable<UserIdentity.UserTokenRefreshResponse> userTokenRefreshObservable(@Body UserIdentity.UserTokenRefreshRequest request) {
        return userIdentityApi2.userTokenRefreshObservable(request);
    }

    public Call<UserIdentity.GetUserResponse> getUser(@Path("user_id") int userId) {
        return userIdentityApi2.getUser(userId);
    }

    public Observable<UserIdentity.GetUserResponse> getUserObservable(@Path("user_id") int userId) {
        return userIdentityApi2.getUserObservable(userId);
    }

    public Call<Void> putUser(@Path("user_id") int userId, @Body UserIdentity.PutUserRequest request) {
        return userIdentityApi2.putUser(userId, request);
    }

    public Observable<Void> putUserObservable(@Path("user_id") int userId, @Body UserIdentity.PutUserRequest request) {
        return userIdentityApi2.putUserObservable(userId, request);
    }

    public Call<Void> userPasswordReset(@Body UserIdentity.UserPasswordResetRequest request) {
        return userIdentityApi2.userPasswordReset(request);
    }

    public Observable<Void> userPasswordResetObservable(@Body UserIdentity.UserPasswordResetRequest request) {
        return userIdentityApi2.userPasswordResetObservable(request);
    }

    public Call<Void> userPhoneSmsVerifyCode(@Path("user_id") int userId, @Body UserIdentity.UserPhoneSmsVerifyCodeRequest request) {
        return userIdentityApi2.userPhoneSmsVerifyCode(userId, request);
    }

    public Observable<Void> userPhoneSmsVerifyCodeObservable(@Path("user_id") int userId, @Body UserIdentity.UserPhoneSmsVerifyCodeRequest request) {
        return userIdentityApi2.userPhoneSmsVerifyCodeObservable(userId, request);
    }

    public Call<UserIdentity.UserPhoneSmsCaptchaResponse> userPhoneSmsCaptcha(@Path("user_id") int userId, @Body UserIdentity.UserPhoneSmsCaptchaRequest request) {
        return userIdentityApi2.userPhoneSmsCaptcha(userId, request);
    }

    public Observable<UserIdentity.UserPhoneSmsCaptchaResponse> userPhoneSmsCaptchaObservable(@Path("user_id") int userId, @Body UserIdentity.UserPhoneSmsCaptchaRequest request) {
        return userIdentityApi2.userPhoneSmsCaptchaObservable(userId, request);
    }

    public Call<Void> putUserPhone(@Path("user_id") int userId, @Body UserIdentity.PutUserPhoneRequest request) {
        return userIdentityApi2.putUserPhone(userId, request);
    }

    public Observable<Void> putUserPhoneObservable(@Path("user_id") int userId, @Body UserIdentity.PutUserPhoneRequest request) {
        return userIdentityApi2.putUserPhoneObservable(userId, request);
    }

    public Call<Void> userEmailVerifyCode(@Path("user_id") int userId, @Body UserIdentity.UserEmailVerifyCodeRequest request) {
        return userIdentityApi2.userEmailVerifyCode(userId, request);
    }

    public Observable<Void> userEmailVerifyCodeObservable(@Path("user_id") int userId, @Body UserIdentity.UserEmailVerifyCodeRequest request) {
        return userIdentityApi2.userEmailVerifyCodeObservable(userId, request);
    }

    public Call<UserIdentity.UserEmailCaptchaResponse> userEmailCaptcha(@Path("user_id") int userId, @Body UserIdentity.UserEmailCaptchaRequest request) {
        return userIdentityApi2.userEmailCaptcha(userId, request);
    }

    public Observable<UserIdentity.UserEmailCaptchaResponse> userEmailCaptchaObservable(@Path("user_id") int userId, @Body UserIdentity.UserEmailCaptchaRequest request) {
        return userIdentityApi2.userEmailCaptchaObservable(userId, request);
    }

    public Call<Void> putUserEmail(@Path("user_id") int userId, @Body UserIdentity.PutUserEmailRequest request) {
        return userIdentityApi2.putUserEmail(userId, request);
    }

    public Observable<Void> putUserEmailObservable(@Path("user_id") int userId, @Body UserIdentity.PutUserEmailRequest request) {
        return userIdentityApi2.putUserEmailObservable(userId, request);
    }

    public Call<UserIdentity.userAvatarUploadResponse> userAvatarUpload(@Query("avatarType") String avatarType, @Body RequestBody request) {
        return userIdentityApi2.userAvatarUpload(avatarType, request);
    }

    public Observable<UserIdentity.userAvatarUploadResponse> userAvatarUploadObservable(@Query("avatarType") String avatarType, @Body RequestBody request) {
        return userIdentityApi2.userAvatarUploadObservable(avatarType, request);
    }

    public Call<Void> userClientInfo(@Path("user_id") int userId, @Body UserIdentity.UserClientInfoRequest request) {
        return userIdentityApi2.userClientInfo(userId, request);
    }

    public Observable<Void> userClientInfoObservable(@Path("user_id") int userId, @Body UserIdentity.UserClientInfoRequest request) {
        return userIdentityApi2.userClientInfoObservable(userId, request);
    }


    /* -------------------------------- DeviceFunctionApi -------------------------------- */

    public Call<DeviceFunction.UserRegisterDeviceResponse> userRegisterDevice(@Path("user_id") int userId, @Body DeviceFunction.UserRegisterDeviceRequest request) {
        return deviceFunctionApi.userRegisterDevice(userId, request);
    }

    public Observable<DeviceFunction.UserRegisterDeviceResponse> userRegisterDeviceObservable(@Path("user_id") int userId, @Body DeviceFunction.UserRegisterDeviceRequest request) {
        return deviceFunctionApi.userRegisterDeviceObservable(userId, request);
    }

    public Call<List<DeviceFunction.UserSubscribeDevicesResponse>> userSubscribeDevices(@Path("user_id") int userId) {
        return deviceFunctionApi.userSubscribeDevices(userId);
    }

    public Observable<List<DeviceFunction.UserSubscribeDevicesResponse>> userSubscribeDevicesObservable(@Path("user_id") int userId) {
        return deviceFunctionApi.userSubscribeDevicesObservable(userId);
    }

    public Call<DeviceFunction.DeviceProperty> postProductDeviceProperty(@Path("product_id") String productId, @Path("device_id") int deviceId, @Body DeviceFunction.DeviceProperty request) {
        return deviceFunctionApi.postProductDeviceProperty(productId, deviceId, request);
    }

    public Observable<DeviceFunction.DeviceProperty> postProductDevicePropertyObservable(@Path("product_id") String productId, @Path("device_id") int deviceId, @Body DeviceFunction.DeviceProperty request) {
        return deviceFunctionApi.postProductDevicePropertyObservable(productId, deviceId, request);
    }

    public Call<DeviceFunction.DeviceProperty> getProductDeviceProperty(@Path("product_id") String productId, @Path("device_id") int deviceId) {
        return deviceFunctionApi.getProductDeviceProperty(productId, deviceId);
    }

    public Observable<DeviceFunction.DeviceProperty> getProductDevicePropertyObservable(@Path("product_id") String productId, @Path("device_id") int deviceId) {
        return deviceFunctionApi.getProductDevicePropertyObservable(productId, deviceId);
    }

    public Call<Void> userUnSubscribe(@Path("user_id") int userId, @Body DeviceFunction.UserUnSubscribeRequest request) {
        return deviceFunctionApi.userUnSubscribe(userId, request);
    }

    public Observable<Void> userUnSubscribeObservable(@Path("user_id") int userId, @Body DeviceFunction.UserUnSubscribeRequest request) {
        return deviceFunctionApi.userUnSubscribeObservable(userId, request);
    }

    public Call<DeviceFunction.DeviceSubscribeUsersResponse> deviceSubscribeUsers(@Path("user_id") int userId, @Query("device_id") String deviceId) {
        return deviceFunctionApi.deviceSubscribeUsers(userId, deviceId);
    }

    public Observable<DeviceFunction.DeviceSubscribeUsersResponse> deviceSubscribeUsersObservable(@Path("user_id") int userId, @Query("device_id") String deviceId) {
        return deviceFunctionApi.deviceSubscribeUsersObservable(userId, deviceId);
    }

    public Call<DeviceFunction.ShareDeviceResponse> shareDevice(@Body DeviceFunction.ShareDeviceRequest request) {
        return deviceFunctionApi.shareDevice(request);
    }

    public Observable<DeviceFunction.ShareDeviceResponse> shareDeviceObservable(@Body DeviceFunction.ShareDeviceRequest request) {
        return deviceFunctionApi.shareDeviceObservable(request);
    }

    public Call<Void> shareDeviceCancel(@Body DeviceFunction.ShareDeviceCancelRequest request) {
        return deviceFunctionApi.shareDeviceCancel(request);
    }

    public Observable<Void> shareDeviceCancelObservable(@Body DeviceFunction.ShareDeviceCancelRequest request) {
        return deviceFunctionApi.shareDeviceCancelObservable(request);
    }

    public Call<Void> shareDeviceAccept(@Body DeviceFunction.ShareDeviceAcceptRequest request) {
        return deviceFunctionApi.shareDeviceAccept(request);
    }

    public Observable<Void> shareDeviceAcceptObservable(@Body DeviceFunction.ShareDeviceAcceptRequest request) {
        return deviceFunctionApi.shareDeviceAcceptObservable(request);
    }

    public Call<Void> shareDeviceDeny(@Body DeviceFunction.ShareDeviceDenyRequest request) {
        return deviceFunctionApi.shareDeviceDeny(request);
    }

    public Observable<Void> shareDeviceDenyObservable(@Body DeviceFunction.ShareDeviceDenyRequest request) {
        return deviceFunctionApi.shareDeviceDenyObservable(request);
    }

    public Call<List<DeviceFunction.ShareDeviceListResponse>> shareDeviceList() {
        return deviceFunctionApi.shareDeviceList();
    }

    public Observable<List<DeviceFunction.ShareDeviceListResponse>> shareDeviceListObservable() {
        return deviceFunctionApi.shareDeviceListObservable();
    }

    public Call<Void> shareDeviceDelete(@Path("invite_code") String inviteCode) {
        return deviceFunctionApi.shareDeviceDelete(inviteCode);
    }

    public Observable<Void> shareDeviceDeleteObservable(@Path("invite_code") String inviteCode) {
        return deviceFunctionApi.shareDeviceDeleteObservable(inviteCode);
    }

    public Call<DeviceFunction.UpgradeDeviceNewestVersionResponse> upgradeDeviceNewestVersion(@Body DeviceFunction.UpgradeDeviceNewestVersionRequest request) {
        return deviceFunctionApi.upgradeDeviceNewestVersion(request);
    }

    public Observable<DeviceFunction.UpgradeDeviceNewestVersionResponse> upgradeDeviceNewestVersionObservable(@Body DeviceFunction.UpgradeDeviceNewestVersionRequest request) {
        return deviceFunctionApi.upgradeDeviceNewestVersionObservable(request);
    }

    public Call<Void> upgradeDevice(@Body DeviceFunction.UpgradeDeviceRequest request) {
        return deviceFunctionApi.upgradeDevice(request);
    }

    public Observable<Void> upgradeDeviceObservable(@Body DeviceFunction.UpgradeDeviceRequest request) {
        return deviceFunctionApi.upgradeDeviceObservable(request);
    }

    public Call<DeviceFunction.ProductVDeviceResponse> productVDevice(@Path("product_id") String product_id, @Path("device_id") int device_id) {
        return deviceFunctionApi.productVDevice(product_id, device_id);
    }

    public Observable<DeviceFunction.ProductVDeviceResponse> productVDeviceObservable(@Path("product_id") String product_id, @Path("device_id") int device_id) {
        return deviceFunctionApi.productVDeviceObservable(product_id, device_id);
    }

    public Call<DeviceFunction.ProductDeviceSnapshotResponse> productDeviceSnapshot(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.ProductDeviceSnapshotRequest request) {
        return deviceFunctionApi.productDeviceSnapshot(product_id, device_id, request);
    }

    public Observable<DeviceFunction.ProductDeviceSnapshotResponse> productDeviceSnapshotObservable(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.ProductDeviceSnapshotRequest request) {
        return deviceFunctionApi.productDeviceSnapshotObservable(product_id, device_id, request);
    }

    public Call<List<DeviceFunction.ProductDataPointsResponse>> productDataPoints(@Path("product_id") String productId) {
        return deviceFunctionApi.productDataPoints(productId);
    }

    public Observable<List<DeviceFunction.ProductDataPointsResponse>> productDataPointsObservable(@Path("product_id") String productId) {
        return deviceFunctionApi.productDataPointsObservable(productId);
    }

    public Call<DeviceFunction.ProductDeviceAlertLogsResponse> productDeviceAlertLogs(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.ProductDeviceAlertLogsRequest request) {
        return deviceFunctionApi.productDeviceAlertLogs(product_id, device_id, request);
    }

    public Observable<DeviceFunction.ProductDeviceAlertLogsResponse> productDeviceAlertLogsObservable(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.ProductDeviceAlertLogsRequest request) {
        return deviceFunctionApi.productDeviceAlertLogsObservable(product_id, device_id, request);
    }

    public Call<DeviceFunction.UserQRCodeSubscribeResponse> userQRCodeSubscribe(@Path("user_id") int user_id, @Body DeviceFunction.UserQRCodeSubscribeRequest dataRequest) {
        return deviceFunctionApi.userQRCodeSubscribe(user_id, dataRequest);
    }

    public Observable<DeviceFunction.UserQRCodeSubscribeResponse> userQRCodeSubscribeObservable(@Path("user_id") int user_id, @Body DeviceFunction.UserQRCodeSubscribeRequest dataRequest) {
        return deviceFunctionApi.userQRCodeSubscribeObservable(user_id, dataRequest);
    }

    public Call<DeviceFunction.GetProductDeviceGeographyResponse> getProductDeviceGeography(@Path("product_id") String product_id, @Path("device_id") int device_id) {
        return deviceFunctionApi.getProductDeviceGeography(product_id, device_id);
    }

    public Observable<DeviceFunction.GetProductDeviceGeographyResponse> getProductDeviceGeographyObservable(@Path("product_id") String product_id, @Path("device_id") int device_id) {
        return deviceFunctionApi.getProductDeviceGeographyObservable(product_id, device_id);
    }

    public Call<DeviceFunction.PutProductDeviceResponse> putProductDevice(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.PutProductDeviceRequest request) {
        return deviceFunctionApi.putProductDevice(product_id, device_id, request);
    }

    public Observable<DeviceFunction.PutProductDeviceResponse> putProductDeviceObservable(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.PutProductDeviceRequest request) {
        return deviceFunctionApi.putProductDeviceObservable(product_id, device_id, request);
    }

    public Call<Void> putProductDeviceGeography(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.PutProductDeviceGeographyRequest request) {
        return deviceFunctionApi.putProductDeviceGeography(product_id, device_id, request);
    }

    public Observable<Void> putProductDeviceGeographyObservable(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.PutProductDeviceGeographyRequest request) {
        return deviceFunctionApi.putProductDeviceGeographyObservable(product_id, device_id, request);
    }

}
