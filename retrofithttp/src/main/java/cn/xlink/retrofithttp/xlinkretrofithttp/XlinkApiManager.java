package cn.xlink.retrofithttp.xlinkretrofithttp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

import cn.xlink.retrofithttp.XlinkOkHttpClient;
import cn.xlink.retrofithttp.constant.HttpServerConfig;
import cn.xlink.retrofithttp.entity.daoentity.User;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import cn.xlink.retrofithttp.entity.UserAuth;

/**
 * Created by liucr on 2017/2/16.
 */

public class XlinkApiManager {

    private static XlinkApiManager mInstance;

    private XlinkService xlinkService;

    private String accessToken = "";

    public XlinkApiManager() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        xlinkService = new Retrofit.Builder()
                .baseUrl(HttpServerConfig.xlinkServer)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//用于返回Rxjava调用,非必须
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(XlinkOkHttpClient.getInstance().getOkHttpClient())
                .build()
                .create(XlinkService.class);
    }

    public static XlinkApiManager getInstance() {
        if (mInstance == null) {
            synchronized (XlinkApiManager.class) {
                if (mInstance == null) {
                    mInstance = new XlinkApiManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 使用邮箱注册新账号
     *
     * @param email
     * @param nickname （非必须）
     * @param password
     * @param source
     * @return
     */
    public Observable<HashMap> registerByEmail(String email, String nickname, String password, int source) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("nickname", nickname);
        params.put("corp_id", HttpServerConfig.companyId);
        params.put("password", password);
        params.put("source", source);
        return xlinkService.register(params);
    }

    /**
     * 发送注册手机验证码
     *
     * @param corp_id
     * @param phone
     * @param phone_zone 手机区号，不填则默认中国:+86（非必要）
     * @param captcha    图片验证码 （图片验证码，当调用本接口到了一定次数以后需要该字段。）
     * @return
     */
    public Observable<HashMap> getRegisterVerifycode(String phone, String phone_zone, String captcha) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("corp_id", HttpServerConfig.companyId);
        params.put("phone", phone);
        params.put("phone_zone", phone_zone);
        params.put("captcha", captcha);
        return xlinkService.getRegisterVerifycode(params);
    }

    /**
     * 请求或刷新发送注册短信的图片验证码（当请求发送注册手机验证码达到一定的次数后，需要进行图片验证码进行验证，本接口用于获取图片验证码。）
     *
     * @param corp_id
     * @param phone
     * @param phone_zone （非必须）
     * @return "url":"图片验证码url"
     */
    public Observable<HashMap> getImageVerifycode(String phone, String phone_zone) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("corp_id", HttpServerConfig.companyId);
        params.put("phone", phone);
        params.put("phone_zone", phone_zone);
        return xlinkService.getImageVerifycode(params);
    }

    /**
     * 使用手机号注册新账号
     *
     * @param phone
     * @param nickname （非必须）
     * @param password
     * @param source
     * @return
     */
    public Observable<HashMap> registerByPhone(String phone, String nickname, String verifycode, String password, int source) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", phone);
        params.put("nickname", nickname);
        params.put("corp_id", HttpServerConfig.companyId);
        params.put("verifycode", verifycode);
        params.put("password", password);
        params.put("source", source);
        return xlinkService.register(params);
    }


    /**
     * 登录获取token及Uid
     *
     * @param phone
     * @param password
     * @return
     */
    public Observable<UserAuth> login(String phone, String password) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        params.put("corp_id", HttpServerConfig.companyId);
        params.put("password", password);
        return xlinkService.login(params);
    }

    /**
     * 刷新凭证
     *
     * @return "refresh_token":"刷新凭证"
     */
    public Observable<HashMap> refreshToken() {
        return xlinkService.refreshToken(accessToken);
    }

    /**
     * 获取用户信息
     *
     * @param uid
     * @return
     */
    public Observable<User> getUserMsg(String uid) {
        return xlinkService.getUser(accessToken, uid);
    }

    /**
     * 修改用户基本信息
     *
     * @param uid
     * @param nickname 昵称
     * @param gender   性别 1为男, 2为女, -1为未知
     * @param age      年龄
     * @param avatar   头像
     * @return
     */
    public Observable<Void> setUserBaseMsg(String uid, String nickname, Integer gender, Integer age, String avatar) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("nickname", nickname);
        parms.put("gender", gender);
        parms.put("age", age);
        parms.put("avatar", avatar);
        parms.values().remove(null);
        return xlinkService.setUserBaseMsg(accessToken, uid, parms);
    }

    /**
     * 修改用户的密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public Observable<Void> changePassword(String oldPassword, String newPassword) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("old_password", oldPassword);
        parms.put("new_password", newPassword);
        return xlinkService.changePassword(accessToken, parms);
    }

    /**
     * 找回密码
     *
     * @param phone
     * @param captcha
     * @return
     */
    public Observable<Void> forgotPasswordByPhone(String phone, String captcha) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("corp_id", HttpServerConfig.companyId);
        parms.put("phone", phone);
        parms.put("captcha", captcha);
        parms.values().remove(null);
        return xlinkService.forgotPassword(parms);
    }

    /**
     * 找回密码
     *
     * @param email
     * @param captcha
     * @return
     */
    public Observable<Void> forgotPasswordByEmail(String email, String captcha) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("corp_id", HttpServerConfig.companyId);
        parms.put("email", email);
        parms.put("captcha", captcha);
        parms.values().remove(null);
        return xlinkService.forgotPassword(parms);
    }

    /**
     * 通过验证码重置密码
     *
     * @param phone
     * @param verifycode
     * @param new_password
     * @return
     */
    public Observable<Void> foundbackPasswordByPhone(String phone, String verifycode, String new_password) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("corp_id", HttpServerConfig.companyId);
        parms.put("phone", phone);
        parms.put("verifycode", verifycode);
        parms.put("new_password", new_password);
        return xlinkService.forgotPassword(parms);
    }

    /**
     * 通过验证码重置密码
     *
     * @param email
     * @param verifycode
     * @param new_password
     * @return
     */
    public Observable<Void> foundbackPasswordByEmail(String email, String verifycode, String new_password) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("corp_id", HttpServerConfig.companyId);
        parms.put("email", email);
        parms.put("verifycode", verifycode);
        parms.put("new_password", new_password);
        return xlinkService.foundbackPassword(parms);
    }

    /**
     * 修改手机号
     * 请求发送验证码
     *
     * @param oldphone
     * @return
     */
    public Observable<Void> getChangePhoneVerifycode(String oldphone) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("phone", oldphone);
        return xlinkService.getChangePhoneVerifycode(parms);
    }

    /**
     * 修改手机号
     * 通过验证码完成修改
     *
     * @param newPhone
     * @param verifycode
     * @param password
     * @return
     */
    public Observable<Void> changePhoneNumber(String newPhone, String verifycode, String password) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("phone", newPhone);
        parms.put("verifycode", verifycode);
        parms.put("password", password);
        return xlinkService.changePhoneNumber(parms);
    }

    /**
     * 上传用户头像
     *
     * @param img
     * @return "url":"头像资源地址"
     */
    public Observable<HashMap> uploadAvatar(byte[] img) {
        return xlinkService.uploadAvatar(accessToken, img);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
