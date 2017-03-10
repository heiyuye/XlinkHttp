package cn.xlink.common.http.restful.xlink.mode;

import java.util.Date;
import java.util.List;

/**
 * 用户身份接口 mode
 *
 * @author sswukang on 2016/11/14 12:12
 * @version 1.0
 */
public class UserIdentity {

    /**
     * 使用邮箱注册新账号 请求
     */
    public static class UserEmailRegisterRequest {
        /**
         * 邮箱地址
         */
        public String email; // 是
        /**
         * 用户昵称，长度2-32个字符
         */
        public String nickname; // 否
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 认证密码，长度6-16个字符
         */
        public String password; // 是
        /**
         * 用户来源，见{@link XLinkEnum.UserSource}
         */
        public int source; // 是
        /**
         * 本地语言代码，默认：zh-cn。见{@link XLinkEnum.LocalLang}
         */
        public String local_lang; // 否
        /**
         * 用户所属的应用插件ID
         */
        public String plugin_id; // 否
    }

    /**
     * 使用邮箱注册新账号 响应
     */
    public static class UserEmailRegisterResponse {
        /**
         * 邮箱地址
         */
        public String email; // 是
    }


    /**
     * 激活邮箱账号 请求
     */
    public static class UserEmailActivateRequest {
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 验证码
         */
        public String verifycode; // 是
        /**
         * 邮箱地址
         */
        public String email; // 是
    }


    /**
     * 发送注册手机验证码 请求
     */
    public static class UserRegisterVerifyCodeRequest {
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机号
         */
        public String phone; // 是
        /**
         * 手机区号，不填则默认中国:+86
         */
        public String phone_zone; // 否
        /**
         * 图片验证码，当调用本接口到了一定次数以后需要该字段。
         */
        public String captcha; // 否
    }


    /**
     * 请求或刷新发送注册短信的图片验证码 请求
     */
    public static class UserRegisterCaptchaCaptchaRequest {
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机号
         */
        public String phone; // 是
        /**
         * 手机区号，不填则默认中国:+86
         */
        public String phone_zone; // 否
    }

    /**
     * 请求或刷新发送注册短信的图片验证码 响应
     */
    public static class UserRegisterCaptchaResponse {
        /**
         * 图片验证码url
         */
        public String url; // 是
    }


    /**
     * 通过手机验证码注册新账号 请求
     */
    public static class UserPhoneRegisterRequest {
        /**
         * 手机号
         */
        public String phone; // 是
        /**
         * 手机区号，不填则默认中国:+86
         */
        public String phone_zone; // 否
        /**
         * 用户昵称，长度2-32个字符
         */
        public String nickname; // 是
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机短信验证码
         */
        public String verifycode; // 是
        /**
         * 认证密码，长度6-16个字符
         */
        public String password; // 是
        /**
         * 用户来源，见{@link XLinkEnum.UserSource}
         */
        public int source; // 是
        /**
         * 本地语言代码，默认：zh-cn。见{@link XLinkEnum.LocalLang}
         */
        public String local_lang; // 否
        /**
         * 用户所属的应用插件ID
         */
        public String plugin_id; // 否
    }

    /**
     * 通过手机验证码注册新账号 响应
     */
    public static class UserPhoneRegisterResponse {
        /**
         * 手机号
         */
        public String phone; // 是
    }


    /**
     * 登录与认证 请求
     */
    public static class UserAuthRequest {
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机号
         */
        public String phone; // 与邮箱2选1
        /**
         * 邮箱
         */
        public String email;// 与手机号2选1
        /**
         * 手机区号，不填则默认中国:+86
         */
        public String phone_zone; // 否
        /**
         * 密码
         */
        public String password;// 是
        /**
         * 登录源，用户可以在登录时指定登录源，不同登录源可同时登录，长度在0~16个字符之间。
         */
        public String resource; // 否
    }

    /**
     * 登录与认证 响应
     */
    public static class UserAuthResponse {
        /**
         * 用户ID
         */
        public int user_id; // 是
        /**
         * 调用凭证
         */
        public String access_token; // 是
        /**
         * 刷新凭证
         */
        public String refresh_token; // 是
        /**
         * 有效期（秒）
         */
        public int expire_in; // 是
        /**
         * APP登录云端认证码
         */
        public String authorize; // 是
    }


    /**
     * 请求密码找回 请求
     */
    public static class UserPasswordForgotRequest {
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机号码
         */
        public String phone; // 与邮箱地址2选1
        /**
         * 邮箱地址
         */
        public String email; // 与手机号码2选1
        /**
         * 手机区号，默认为中国:+86
         */
        public String phone_zone; // 否
        /**
         * 图片验证码，当调用次数达到一定数量后需要此字段。
         */
        public String captcha; // 否
    }


    /**
     * 请求或刷新请求密码找回所需的图片验证码 请求
     */
    public static class UserPasswordCaptchaRequest {
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机号码
         */
        public String phone; // 与邮箱地址2选1
        /**
         * 手机区号，默认为中国:+86
         */
        public String phone_zone; // 否
        /**
         * 邮箱地址
         */
        public String email; // 与手机号码2选1
    }

    /**
     * 请求或刷新请求密码找回所需的图片验证码 响应
     */
    public static class UserPasswordCaptchaResponse {
        /**
         * 验证码图片URL
         */
        public String url; // 是
    }


    /**
     * 通过验证码重置密码 请求
     */
    public static class UserPasswordFoundBackRequest {
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机号码
         */
        public String phone; // 是
        /**
         * 手机区号，默认为中国:+86
         */
        public String phone_zone; // 否
        /**
         * 验证码
         */
        public String verifycode; // 是
        /**
         * 新的密码
         */
        public String new_password; // 是
    }


    /**
     * 校验手机验证码 请求
     */
    public static class UserVerifyCodeVerifyRequest {
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机号码
         */
        public String phone; // 是
        /**
         * 手机区号，默认为中国:+86
         */
        public String phone_zone; // 否
        /**
         * 验证码
         */
        public String verifycode; // 是
    }

    /**
     * 校验手机验证码 响应
     */
    public static class UserVerifyCodeVerifyResponse {
        /**
         * 验证码
         */
        public String verifycode; // 是
    }


    /**
     * 刷新凭证 请求
     */
    public static class UserTokenRefreshRequest {
        /**
         * 刷新凭证
         */
        public String refresh_token; // 是
    }

    /**
     * 刷新凭证 响应
     */
    public static class UserTokenRefreshResponse {
        /**
         * 新的调用凭证
         */
        public String access_token; // 是
        /**
         * 新的刷新凭证
         */
        public String refresh_token; // 是
        /**
         * 有效期
         */
        public String expire_in; // 是
    }


    /**
     * 获取用户详细信息 响应
     */
    public static class GetUserResponse {
        /**
         * 用户ID
         */
        public int id; // 是
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 手机号
         */
        public String phone; // 与邮箱2选1
        /**
         * 邮箱
         */
        public String email; // 与手机号2选1
        /**
         * 用户昵称
         */
        public String nickname; // 是
        /**
         * 用户注册时间，如:2015-10-09T08:15:40.843Z
         */
        public Date create_date; // 是
        /**
         * 用户状态，见{@link XLinkEnum.UserStatus}
         */
        public String status; // 是
        /**
         * 用户来源，见{@link XLinkEnum.UserSource}
         */
        public int source; // 是
        /**
         * 所在区域ID
         */
        public int region_id; // 是
        /**
         * 账号是否已激活
         */
        public boolean is_vaild; // 是
        /**
         * 用户标签,如果用户没有标签则不返回,有则返回数组
         */
        public List<String> tags; // 否
        /**
         * 头像资源地址url
         */
        public String avatar; // 否
        /**
         * 绑定的QQ帐号信息，为空则无绑定
         */
        public String qq_open_id; // 否
        /**
         * 绑定的微信帐号信息，为空则无绑定
         */
        public String wx_open_id; // 否
        /**
         * 绑定的微博帐号信息，为空则无绑定
         */
        public String wb_open_id; // 否
        /**
         * 绑定的其他平台帐号信息，为空则无绑定
         */
        public String other_open_id; // 否
        /**
         * 用户所在国家
         */
        public String country; // 否
        /**
         * 用户所在省份
         */
        public String province; // 否
        /**
         * 用户所在城市
         */
        public String city; // 否
        /**
         * 用户性别, 1为男, 2为女, -1为未知
         */
        public int gender; // 否
        /**
         * 密码是否初始化
         */
        public boolean passwd_inited; // 是
        /**
         * 年龄
         */
        public int age; // 是
        /**
         * 设置
         */
        public Setting setting;  // 否
        /**
         * 授权码
         */
        public String authorize_code; // 是
        /**
         * 账号
         */
        public String account; // 是

        public static class Setting {
            /**
             * 消息接收设置
             */
            public List<Message> message;
            /**
             * 消息提示方式设置
             */
            public List<MessageInform> message_inform;

            public static class Message {
                /**
                 * 消息类型的接收设置，见{@link XLinkEnum.MessageType}
                 */
                public int type;
                /**
                 * 表示是否接收该类型的消息
                 */
                public boolean receive;
            }

            public static class MessageInform {
                /**
                 * 用户消息的提示方式，见{@link XLinkEnum.MessageInformType}
                 */
                public int type;
                /**
                 * 表示是否启用该提示方式
                 */
                public boolean enable;
            }
        }
    }


    /**
     * 修改用户基本信息 请求
     */
    public static class PutUserRequest {
        /**
         * 用户昵称
         */
        public String nickname; // 否
        /**
         * 备注
         */
        public String remark; // 否
        /**
         * 用户标签,如果用户没有标签则不返回,有则返回数组
         */
        public List<String> tags; // 否
        /**
         * 头像资源地址url
         */
        public String avatar; // 否
        /**
         * 用户所在国家
         */
        public String country; // 否
        /**
         * 用户所在省份
         */
        public String province; // 否
        /**
         * 用户所在城市
         */
        public String city; // 否
        /**
         * 用户性别, 1为男, 2为女, -1为未知
         */
        public int gender; // 否
        /**
         * 年龄
         */
        public int age; // 否
    }


    /**
     * 修改密码 请求
     */
    public static class UserPasswordResetRequest {
        /**
         * 旧密码
         */
        public String old_password; // 是
        /**
         * 新密码
         */
        public String new_password; // 是
    }


    /**
     * 请求发送验证码 请求
     */
    public static class UserPhoneSmsVerifyCodeRequest {
        /**
         * 手机号码
         */
        public String phone; // 是
        /**
         * 手机区号，默认为中国:+86
         */
        public String phone_zone; // 否
        /**
         * 图片验证码，当调用次数达到一定数量后需要此字段。
         */
        public String captcha; // 否
    }


    /**
     * 请求用于发送短信的图片验证码 请求
     */
    public static class UserPhoneSmsCaptchaRequest {
        /**
         * 手机号码
         */
        public String phone; // 是
        /**
         * 手机区号，默认为中国:+86
         */
        public String phone_zone; // 否
    }

    /**
     * 请求用于发送短信的图片验证码 响应
     */
    public static class UserPhoneSmsCaptchaResponse {
        /**
         * 图片验证码URL
         */
        public String url; // 是
    }


    /**
     * 通过验证码完成修改 请求
     */
    public static class PutUserPhoneRequest {
        /**
         * 手机号码
         */
        public String phone; // 是
        /**
         * 手机区号，默认为中国:+86
         */
        public String phone_zone; // 否
        /**
         * 验证码
         */
        public String verifycode; // 是
        /**
         * 用户登录密码
         */
        public String password; // 是
    }


    /**
     * 发送邮件验证码 请求
     */
    public static class UserEmailVerifyCodeRequest {
        /**
         * 邮箱地址
         */
        public String email; // 是
        /**
         * 图片验证码，当调用次数达到一定数量后需要此字段。
         */
        public String captcha; // 否
    }


    /**
     * 请求用于发送邮件的图片验证码 请求
     */
    public static class UserEmailCaptchaRequest {
        /**
         * 邮箱地址
         */
        public String email; // 是
    }

    /**
     * 请求用于发送邮件的图片验证码 响应
     */
    public static class UserEmailCaptchaResponse {
        /**
         * 图片验证码URL
         */
        public String url; // 是
    }


    /**
     * 通过验证码完成修改 请求
     */
    public static class PutUserEmailRequest {
        /**
         * 邮箱地址
         */
        public String email; // 是
        /**
         * 验证码
         */
        public String verifycode; // 是
        /**
         * 用户登录密码
         */
        public String password; // 是
    }


    /**
     * 上传用户头像 响应
     */
    public static class userAvatarUploadResponse {
        /**
         * 头像资源地址
         */
        public String url; // 是
    }


    /**
     * 上传客户端信息 请求
     */
    public static class UserClientInfoRequest {
        /**
         * 用户使用语言的名称
         */
        public String language; // 是
        /**
         * 终端的操作系统类型,见{@link XLinkEnum.OperateSystem}
         */
        public String operate_system; // 是
        /**
         * 终端的机器类型名称
         */
        public String machine_type; // 是
    }
}
