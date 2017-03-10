package cn.xlink.common.http.restful.xlink.mode;

/**
 * xlink枚举附录
 *
 * @author sswukang on 2016/11/10 16:16
 * @version 1.0
 */
public class XLinkEnum {

    /**
     * 设备控制权限
     */
    public enum DeviceAuthority {
        /**
         * 可读
         */
        R("R"),
        /**
         * 可写
         */
        W("W"),
        /**
         * 可读可写
         */
        RW("RW");

        private String value;

        DeviceAuthority(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 快照排序
     */
    public enum SnapshotSort {
        /**
         * 降序
         */
        DESC("desc"),
        /**
         * 升序
         */
        ASC("asc");

        private String value;

        SnapshotSort(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /* ---------------- 用户身份相关 ---------------- */

    /**
     * 用户来源
     */
    public enum UserSource {
        /**
         * web
         */
        WEB(1),
        /**
         * Android客户端
         */
        ANDROID(2),
        /**
         * IOS客户端
         */
        IOS(3),
        /**
         * 微信用户
         */
        WEIXIN(4),
        /**
         * QQ用户
         */
        QQ(5),
        /**
         * 微博用户
         */
        WEIBO(6),
        /**
         * 其它遵循xlink统一身份认证规范的用户来源
         */
        OTHER(10);

        private int value;

        UserSource(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 用户状态
     */
    public enum UserStatus {
        /**
         * 正常可用状态
         */
        ENABLE(1),
        /**
         * 停用状态
         */
        DISABLE(2);
        private int value;

        UserStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 本地语言代码
     */
    public enum LocalLang {
        /**
         * 中文（简体）
         */
        ZH_CN("zh-cn"),
        /**
         * 英语（美国）
         */
        EN_US("en-us");

        private String value;

        LocalLang(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 客户端操作系统类型
     */
    public enum OperateSystem {
        /**
         * IOS系统
         */
        IOS("ios"),
        /**
         * 安卓系统
         */
        ANDROID("android"),
        /**
         * 其他类型
         */
        OTHERS("others");

        private String value;

        OperateSystem(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 消息类型
     */
    public enum MessageType {
        /**
         * 设备告警
         */
        DEVICE_ALARM(1),
        /**
         * 设备通知
         */
        DEVICE_NOTIFY(2),
        /**
         * 系统广播(如官方推送)
         */
        SYSTEM_BRODCAST(3),
        /**
         * P2P,用户与用户(如好友分享)
         */
        P2P(4),
        /**
         * 家庭留言(留言提醒)
         */
        FAMILY_MESSAGE(5);
        private int value;

        MessageType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 消息提示方式类型
     */
    public enum MessageInformType {
        /**
         * 提示音
         */
        WARNING_TONE(1),
        /**
         * 震动
         */
        SHOCK(2);
        private int value;

        MessageInformType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    /* ---------------- 设备功能相关 ---------------- */

    /**
     * 分享方式
     */
    public enum ShareMode {
        /**
         * 通过用户ID分享
         */
        APP("app"),
        /**
         * 二维码方式分享
         */
        QR_CODE("qrcode"),
        /**
         * 邮件方式分享
         */
        EMAIL("email");

        private String value;

        ShareMode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 分享状态
     */
    public enum ShareStatus {
        /**
         * 等待接收
         */
        PENDING("pending"),
        /**
         * 已接收
         */
        ACCEPT("accept"),
        /**
         * 拒绝
         */
        DENY("deny"),
        /**
         * 超时
         */
        OVERTIME("overtime"),
        /**
         * 已取消
         */
        CANCEL("cancel"),
        /**
         * 无效的请求
         */
        INVALID("invalid");

        private String value;

        ShareStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 产生订阅关系的来源
     */
    public enum SubscribeSource {
        /**
         * 外网扫描
         */
        OUTER_NET_SCAN(1),
        /**
         * 其他用户分享
         */
        USER_SHARE(2),
        /**
         * 二维码订阅
         */
        QR_CODE(3),
        /**
         * Home家庭
         */
        HOME_FAMILY(4),
        /**
         * 用户手动添加设备
         */
        MANUALLY_ADD(5),
        /**
         * 微信公众号同步
         */
        WECHAT_PUBLIC(6);

        private int value;

        SubscribeSource(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 数据类型
     */
    public enum DataPointType {
        /**
         * 布尔类型
         */
        BOOLEAN(1),
        /**
         * 单字节(无符号)
         */
        UNSIGNED_BYTE(2),
        /**
         * 16位短整型（有符号）
         */
        SHORT(3),
        /**
         * 32位整型（有符号）
         */
        INT(4),
        /**
         * 浮点
         */
        FLOAT(5),
        /**
         * 字符串
         */
        STRING(6),
        /**
         * 字节数组
         */
        BINS(7),
        /**
         * 16位短整型（无符号）
         */
        UNSIGNED_SHORT(8),
        /**
         * 32位整型（无符号）
         */
        UNSIGNED_INT(9);

        private int value;

        DataPointType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设备创建者类型
     */
    public enum CreatorType {
        /**
         * 企业管理台成员创建
         */
        COMPANY(1),
        /**
         * 用户创建
         */
        USER(2);

        private int value;

        CreatorType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
