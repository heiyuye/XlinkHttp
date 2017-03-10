package cn.xlink.retrofithttp.entity.daoentity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

import cn.xlink.retrofithttp.utils.JacksonUtil;

/**
 * Created by liucr on 2017/3/1.
 */

@Entity
public class User {

    /**
     * id : 用户ID
     * corp_id : 企业ID
     * phone/email : 手机号/邮箱
     * nickname : 用户昵称
     * create_date : 创建时间
     * status : 用户状态
     * source : 用户来源
     * region_id : 所在区域ID
     * is_vaild : 用户账号是否已认证
     * avatar : 头像资源地址url
     * qq_open_id : 绑定的QQ帐号信息
     * wx_open_id : 绑定的微信帐号信息
     * wb_open_id : 绑定的微博帐号信息
     * other_open_id : 绑定的其他平台帐号信息
     * country : 用户所在国家
     * province : 用户所在省份
     * city : 用户所在城市
     * gender : 用户性别, 1为男, 2为女, -1为未知
     * passwd_inited : 是否初始化密码
     * age : 年龄
     * setting : {
     * "message": [
     * {
     * "type": "消息类型"
     * "receive":"是否接收"
     * },
     * {
     * "type": "消息类型"
     * "receive":"是否接收"
     * },
     * {
     * "type": "消息类型"
     * "receive":"是否接收"
     * }
     * ],
     * "message_inform":[
     * {
     * "type":"提示方式类型",
     * "enable":"是否启用"
     * }
     * ]
     * }
     */

    @Id
    private String id;
    private String corp_id;
    private String phone;
    private String email;
    private String nickname;
    private String create_date;
    private int status;
    private int source;
    private String region_id;
    private boolean is_vaild;
    private String avatar;
    private String qq_open_id;
    private String wx_open_id;
    private String wb_open_id;
    private String other_open_id;
    private String country;
    private String province;
    private String city;
    private String gender;
    private boolean passwd_inited;
    private int age;
    @Convert(converter = UserSettingConverter.class, columnType = String.class)
    private UserSetting setting;
    //是否主动退出，本地保存，服务器没有该字段
    private boolean isActiveLogout;
    private String password;
    

    public UserSetting getSetting() {
        return setting;
    }

    public void setSetting(UserSetting setting) {
        this.setting = setting;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    @Generated(hash = 1953677014)
    public User(String id, String corp_id, String phone, String email, String nickname,
            String create_date, int status, int source, String region_id, boolean is_vaild,
            String avatar, String qq_open_id, String wx_open_id, String wb_open_id,
            String other_open_id, String country, String province, String city, String gender,
            boolean passwd_inited, int age, UserSetting setting, boolean isActiveLogout,
            String password) {
        this.id = id;
        this.corp_id = corp_id;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
        this.create_date = create_date;
        this.status = status;
        this.source = source;
        this.region_id = region_id;
        this.is_vaild = is_vaild;
        this.avatar = avatar;
        this.qq_open_id = qq_open_id;
        this.wx_open_id = wx_open_id;
        this.wb_open_id = wb_open_id;
        this.other_open_id = other_open_id;
        this.country = country;
        this.province = province;
        this.city = city;
        this.gender = gender;
        this.passwd_inited = passwd_inited;
        this.age = age;
        this.setting = setting;
        this.isActiveLogout = isActiveLogout;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorp_id() {
        return corp_id;
    }

    public void setCorp_id(String corp_id) {
        this.corp_id = corp_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public boolean isIs_vaild() {
        return is_vaild;
    }

    public void setIs_vaild(boolean is_vaild) {
        this.is_vaild = is_vaild;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getQq_open_id() {
        return qq_open_id;
    }

    public void setQq_open_id(String qq_open_id) {
        this.qq_open_id = qq_open_id;
    }

    public String getWx_open_id() {
        return wx_open_id;
    }

    public void setWx_open_id(String wx_open_id) {
        this.wx_open_id = wx_open_id;
    }

    public String getWb_open_id() {
        return wb_open_id;
    }

    public void setWb_open_id(String wb_open_id) {
        this.wb_open_id = wb_open_id;
    }

    public String getOther_open_id() {
        return other_open_id;
    }

    public void setOther_open_id(String other_open_id) {
        this.other_open_id = other_open_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIs_vaild() {
        return this.is_vaild;
    }

    public boolean isActiveLogout() {
        return isActiveLogout;
    }

    public void setActiveLogout(boolean activeLogout) {
        isActiveLogout = activeLogout;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", corp_id='" + corp_id + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", create_date='" + create_date + '\'' +
                ", status=" + status +
                ", source=" + source +
                ", region_id='" + region_id + '\'' +
                ", is_vaild=" + is_vaild +
                ", avatar='" + avatar + '\'' +
                ", qq_open_id='" + qq_open_id + '\'' +
                ", wx_open_id='" + wx_open_id + '\'' +
                ", wb_open_id='" + wb_open_id + '\'' +
                ", other_open_id='" + other_open_id + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", passwd_inited='" + passwd_inited + '\'' +
                ", age=" + age +
                ", setting=" + setting +
                ", isActiveLogout=" + isActiveLogout +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean getIsActiveLogout() {
        return this.isActiveLogout;
    }

    public void setIsActiveLogout(boolean isActiveLogout) {
        this.isActiveLogout = isActiveLogout;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getPasswd_inited() {
        return this.passwd_inited;
    }

    public void setPasswd_inited(boolean passwd_inited) {
        this.passwd_inited = passwd_inited;
    }

    public static class UserSetting {

        private List<UserSettingMessage> message;
        private List<UserSettingMessageInform> message_inform;

        public List<UserSettingMessage> getMessage() {
            return message;
        }

        public void setMessage(List<UserSettingMessage> message) {
            this.message = message;
        }

        public List<UserSettingMessageInform> getMessage_inform() {
            return message_inform;
        }

        public void setMessage_inform(List<UserSettingMessageInform> message_inform) {
            this.message_inform = message_inform;
        }

        @Override
        public String toString() {
            return "UserSetting{" +
                    "message=" + message +
                    ", message_inform=" + message_inform +
                    '}';
        }
    }

    public static class UserSettingMessage {
        private String type;
        private String receive;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReceive() {
            return receive;
        }

        public void setReceive(String receive) {
            this.receive = receive;
        }

        @Override
        public String toString() {
            return "UserSettingMessage{" +
                    "type='" + type + '\'' +
                    ", receive='" + receive + '\'' +
                    '}';
        }
    }

    public static class UserSettingMessageInform {
        private String type;
        private String enable;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        @Override
        public String toString() {
            return "UserSettingMessageInform{" +
                    "type='" + type + '\'' +
                    ", enable='" + enable + '\'' +
                    '}';
        }
    }

    public static class UserSettingConverter implements PropertyConverter<UserSetting, String> {
        @Override
        public UserSetting convertToEntityProperty(String databaseValue) {

            return JacksonUtil.toEntity(databaseValue, UserSetting.class);
        }

        @Override
        public String convertToDatabaseValue(UserSetting entityProperty) {
            // 判断返回 null
            return JacksonUtil.toJson(entityProperty);
        }
    }

}
