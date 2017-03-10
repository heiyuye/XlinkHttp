package cn.xlink.common.http.restful.xlink.mode;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 设备功能接口 mode
 *
 * @author sswukang on 2016/11/14 14:51
 * @version 1.0
 */
public class DeviceFunction {

    /**
     * 用户注册一个新的设备 请求
     */
    public static class UserRegisterDeviceRequest {
        /**
         * 产品ID
         */
        public String product_id; // 是
        /**
         * MAC地址
         */
        public String mac; // 是
        /**
         * 设备名称
         */
        public String name; // 否
        /**
         * 本地设备密码
         */
        public String access_key; // 否
        /**
         * MCU固件型号
         */
        public String mcu_mod; // 否
        /**
         * MCU固件版本
         */
        public int mcu_version; // 否
        /**
         * 固件型号
         */
        public String firmware_mod; // 否
        /**
         * 固件版本
         */
        public int firmware_version; // 否
    }

    /**
     * 用户注册一个新的设备 响应
     */
    public static class UserRegisterDeviceResponse {
        /**
         * 设备ID
         */
        private int device_id; // 是
        /**
         * 产品ID
         */
        public String product_id; // 是
        /**
         * MAC地址
         */
        public String mac; // 是
        /**
         * 设备名称
         */
        public String name; // 否
        /**
         * 本地设备密码
         */
        public String access_key; // 否
        /**
         * MCU固件型号
         */
        public String mcu_mod; // 否
        /**
         * MCU固件版本
         */
        public int mcu_version; // 否
        /**
         * 固件型号
         */
        public String firmware_mod; // 否
        /**
         * 固件版本
         */
        public int firmware_version; // 否
    }


    /**
     * 用户获取设备列表 响应
     */
    public static class UserSubscribeDevicesResponse {
        /**
         * 设备ID
         */
        public int id; // 是
        /**
         * 设备MAC地址
         */
        public String mac; // 是
        /**
         * 是否激活，布尔值，true或false
         */
        public boolean is_active; // 是
        /**
         * 激活时间，例：2015-10-09T08 : 15 : 40.843Z
         */
        public Date active_date; // 是
        /**
         * 是否在线，布尔值，true或false
         */
        public boolean is_online; // 是
        /**
         * 最近登录时间，例：2015-10-09T08 : 15 : 40.843Z
         */
        public Date last_login; // 是
        /**
         * 激活码
         */
        public String active_code; // 是
        /**
         * 认证码
         */
        public String authorize_code; // 是
        /**
         * MCU型号
         */
        public String mcu_mod; // 是
        /**
         * MCU版本号
         */
        public int mcu_version; // 是
        /**
         * 固件型号
         */
        public String firmware_mod; // 是
        /**
         * 固件版本号
         */
        public int firmware_version; // 是
        /**
         * 所属的产品ID
         */
        public String product_id; // 是
        /**
         * 设备本地密码
         */
        public int access_key; // 是
        /**
         * 设备名称，设备名称可以通过修改设备属性进行更改
         */
        public String name; // 否
        /**
         * 用户和设备的订阅关系，admin还user
         */
        public int role; // 否
        /**
         * 对设备的控制权限，R可读，W可写，RW可读可写，默认为null相当于RW，见{@link XLinkEnum.DeviceAuthority}
         */
        public String authority; // 否
        /**
         * 产生订阅的来源，见{@link XLinkEnum.SubscribeSource}
         */
        public int source; // 否
        /**
         * 设备序列号，如果设备有设置序列号才会返回
         */
        public String sn; // 否
        /**
         * 设备销售时间，需要添加filter才能返回
         */
        public String soft_init_date; // 否
    }


    /**
     * 设置设备的扩展属性
     */
    public static class DeviceProperty {
        public ExternalProperty externalProperty;

        public static class ExternalProperty {
            public String name;
        }
    }


    /**
     * 取消订阅设备 请求
     */
    public static class UserUnSubscribeRequest {
        /**
         * 设备ID
         */
        public int device_id; // 是
    }


    /**
     * 获取设备用户列表 响应
     */
    public static class DeviceSubscribeUsersResponse {
        /**
         * 条目数
         */
        public int count; // 是
        public List<UserBean> list;

        public static class UserBean {
            /**
             * 用户ID
             */
            public int user_id; // 是
            /**
             * 用户和设备的订阅关系；0：管理员；1：普通用户；
             */
            public int role; // 是
            /**
             * 用户昵称
             */
            public String nickname; // 是
            /**
             * 设备分享者ID
             */
            public int from_id; // 是
        }
    }


    /**
     * 设备分享 请求
     */
    public static class ShareDeviceRequest {
        /**
         * 所要分享的设备ID
         */
        public int device_id; // 是
        /**
         * 分享给对方的用户id, 即是利用用户id进行分享
         */
        public String user_id; // 否
        /**
         * 分享给谁。在进行二维码或者邮箱分享的时候，对方ID不确定，只需拿到分享码即可。
         * 可以为对方手机号或者邮箱号。user_id和user两者取其一, 如果两个都有则取user为准。
         */
        public String user; // 否
        /**
         * 分享请求有效时间，单位秒
         */
        public int expire = 7200; // 是
        /**
         * 分享方式，枚举值，见{@link XLinkEnum.ShareMode}
         */
        public String mode; // 是
        /**
         * 对设备的控制权限，R可读，W可写，RW可读可写，默认为null相当于RW，见{@link XLinkEnum.DeviceAuthority}
         */
        public String authority; // 否

    }

    /**
     * 设备分享 响应
     */
    public static class ShareDeviceResponse {
        /**
         * 分享码
         */
        public String invite_code; // 是
    }


    /**
     * 取消分享 请求
     */
    public static class ShareDeviceCancelRequest {
        /**
         * 分享码
         */
        public String invite_code; // 是
    }


    /**
     * 接受分享 请求
     */
    public static class ShareDeviceAcceptRequest {
        /**
         * 分享码
         */
        public String invite_code; // 是
    }


    /**
     * 拒绝分享 请求
     */
    public static class ShareDeviceDenyRequest {
        /**
         * 分享码
         */
        public String invite_code; // 是
        /**
         * 用户拒绝分享的原因
         */
        public String reason; // 否
    }


    /**
     * 获取设备分享列表 响应
     */
    public static class ShareDeviceListResponse {
        /**
         * 分享码
         */
        public String invite_code; // 是
        /**
         * 对设备的控制权限，R可读，W可写，RW可读可写，默认为null相当于RW，见{@link XLinkEnum.DeviceAuthority}
         * 同时用户可以扩展这个权限如RW+-等等，这个权限会在订阅时进入用户和设备的authority属性；
         */
        public String authority; // 否
        /**
         * 分享者ID。
         */
        public int from_id; // 是
        /**
         * 分享者帐号。
         */
        public String from_user; // 是
        /**
         * 分享者昵称。
         */
        public String from_name; // 否
        /**
         * 接受者ID。
         */
        public int user_id; // 是
        /**
         * 接受者帐号。
         */
        public String to_user; // 是
        /**
         * 接受者昵称。
         */
        public String to_name; // 否
        /**
         * 设备ID
         */
        public int device_id; // 是
        /**
         * 分享状态;见{@link XLinkEnum.ShareStatus}
         */
        public String state; // 是
        /**
         * 分享产生时间
         */
        public Date gen_date; // 是
        /**
         * 分享过期时间
         */
        public Date expire_date; // 是
        /**
         * 分享信息是否可见
         */
        public int visible; // 是
        /**
         * 分享方式，枚举值，见{@link XLinkEnum.ShareMode}
         */
        public String share_mode; // 是
        /**
         * 分享ID
         */
        public String id; // 是
    }


    /**
     * 用户获取设备最新版本 请求
     */
    public static class UpgradeDeviceNewestVersionRequest {
        /**
         * 产品ID
         */
        public String product_id; // 是
        /**
         * 设备ID
         */
        public String device_id; // 是
        /**
         * 固件升级任务类型, 默认为wifi.
         */
        public String type; // 否
        /**
         * 用来定位多MCU或多子设备的情况, 数字, type为mcu或多子设备时必传, 默认为0.
         */
        public int identify; // 否
    }

    /**
     * 用户获取设备最新版本 响应
     */
    public static class UpgradeDeviceNewestVersionResponse {
        /**
         * 当前的固件版本
         */
        public String current; // 是
        /**
         * 最新的固件版本
         */
        public String newest; // 是
        /**
         * 升级描述
         */
        public String description; // 否
    }


    /**
     * 用户手动升级设备 请求
     */
    public static class UpgradeDeviceRequest {
        /**
         * 产品ID
         */
        public String product_id; // 是
        /**
         * 设备ID
         */
        public String device_id; // 是
        /**
         * 固件升级任务类型, 默认为wifi.
         */
        public String type; // 否
        /**
         * 用来定位多MCU或多子设备的情况, 数字, type为mcu或多子设备时必传, 默认为0.
         */
        public int identify; // 否
    }


    /**
     * 获取虚拟设备数据 响应
     */
    public static class ProductVDeviceResponse {
        /**
         * 设备ID
         */
        public int device_id; // 是
        /**
         * 登录CM服务器ID
         */
        public String cm_id; // 是
        /**
         * 登录IP
         */
        public String ip; // 是
        /**
         * 是否在线
         */
        public boolean online; // 是
        /**
         * 上次登录时间
         */
        public Date last_login; // 是
        /**
         * 上次离线时间
         */
        public Date last_logout; // 是
        /**
         * 上次数据端点变化时间
         */
        public Date last_update; // 是
        /**
         * 设备累计在线时长，单位：秒
         */
        public long online_count; // 是
        /**
         * 数据端点值
         */
        public Map<String, String> dataPoints; // 否
    }


    /**
     * 获取设备快照 请求
     */
    public static class ProductDeviceSnapshotRequest {
        /**
         * 请求的偏移量
         */
        public int offset; // 是
        /**
         * 请求的数量上限
         */
        public int limit; // 是
        /**
         * 是否通过时间作为查询的过滤条件
         */
        public QueryDate date; // 否
        /**
         * 快照规则id
         */
        public int rule_id; // 否
        /**
         * desc:降序, asc:升序, 见见{@link XLinkEnum.SnapshotSort}
         */
        public String sort_by_date; // 否

        public static class QueryDate {
            /**
             * 查询开始时间,时间戳
             */
            public long begin; // 是
            /**
             * 查询结束时间,时间戳
             */
            public long end; // 是
        }
    }

    /**
     * 获取设备快照 响应
     */
    public static class ProductDeviceSnapshotResponse {
        /**
         * 获取的条目个数
         */
        public int count; // 是
        /**
         * 获取的快照列表
         */
        public List<Snapshot> list; // 是

        public static class Snapshot {
            /**
             * 设备快照ID
             */
            public String _id; // 是
            /**
             * 设备ID
             */
            public int device_id; // 是
            /**
             * 登录CM服务器ID
             */
            public String cm_id; // 是
            /**
             * 登录IP
             */
            public String ip; // 是
            /**
             * 是否在线
             */
            public boolean online; // 是
            /**
             * 上次登录时间
             */
            public Date last_online; // 是
            /**
             * 上次离线时间
             */
            public Date last_offline; // 是
            /**
             * 上次数据端点变化时间
             */
            public Date last_update; // 是
            /**
             * 快照时间
             */
            public Date snapshot_date; // 是
            /**
             * 数据快照规则id
             */
            public int rule_id; // 否
            /**
             * 数据端点值
             */
            public Map<String, String> dataPoints; // 否
        }
    }


    /**
     * 获取数据端点列表 响应
     */
    public static class ProductDataPointsResponse {
        /**
         * 数据端点ID
         */
        public String id; // 是
        /**
         * 数据端点名称，32个字符以内
         */
        public String name; // 是
        /**
         * 数据类型，见{@link XLinkEnum.DataPointType}
         */
        public int type; // 是
        /**
         * 数据端点索引，16位整形
         */
        public short index; // 是
        /**
         * 数据端点描述，250个字符内
         */
        public String description; // 是
        /**
         * 符号，10个字符以内
         */
        public String symbol; // 是
        /**
         * 是否收集端点数据，默认：否
         */
        public boolean is_collect; // 否
        /**
         * 数据端点取值范围,最小值
         */
        public int min; // 是
        /**
         * 数据端点取值范围,最大值
         */
        public int max; // 是
        /**
         * 数据端点是否可读
         */
        public boolean is_read; // 是
        /**
         * 数据端点是否可写
         */
        public boolean is_write; // 是
        /**
         * 数据端点默认值
         */
        public String default_value; // 是
    }


    /**
     * 获取设备告警日志列表 请求
     */
    public static class ProductDeviceAlertLogsRequest {
        /**
         * 请求的偏移量
         */

        public int offset; // 是
        /**
         * 请求的数量上限
         */
        public int limit; // 是
        /**
         * 查询语句
         */
        public Map<String, XLinkQuery> query; // 否
    }

    /**
     * 获取设备告警日志列表 响应
     */
    public static class ProductDeviceAlertLogsResponse {
        /**
         * 总数量
         */
        public int count; // 是
        /**
         * 日志列表
         */
        public List<Log> list; // 否

        public static class Log {
            /**
             * 日志ID
             */
            public String id; // 是
            /**
             * 通知类型, 见{@link XLinkEnum.MessageType}
             */
            public int notify_type; // 是
            /**
             * 设备ID
             */
            public int device_id; // 是
            /**
             * 产品ID
             */
            public String product_id; // 是
            /**
             * 消息内容
             */
            public String content; // 是
            /**
             * 创建时间
             */
            public Date create_date;
            /**
             * 标签
             */
            public String tag; // 否
            /**
             * 是否需要推送消息
             */
            public boolean is_push; // 是
        }
    }


    /**
     * 通过二维码订阅设备 请求
     */
    public static class UserQRCodeSubscribeRequest {
        /**
         * 二维码数据
         */
        public String qrcode; // 是
    }

    /**
     * 通过二维码订阅设备 响应
     */
    public static class UserQRCodeSubscribeResponse {
        /**
         * 设备ID
         */
        public int id; // 是
        /**
         * 设备MAC地址
         */
        public String mac; // 是
        /**
         * 产品ID
         */
        public String product_id; // 是
        /**
         * 序列号
         */
        public String sn; // 是
        /**
         * custom_property
         */
        public Map<String, String> custom_property; // 否
    }


    /**
     * 获取设备地理位置信息 响应
     */
    public static class GetProductDeviceGeographyResponse {
        /**
         * 设备ID
         */
        public int device_id; // 是
        /**
         * 设备经度；如果没有信息，返回空
         */
        public double lon; // 是
        /**
         * 设备纬度；如果没有信息，返回空
         */
        public double lat; // 是
        /**
         * 更新时间，例：2014-10-09T08:15:40.843Z；如果没有信息，返回空
         */
        public Date update_time; // 是
        /**
         * 设备所在国家
         */
        public String country; // 是
        /**
         * 设备所在省
         */
        public String province; // 是
        /**
         * 设备所在市
         */
        public String city; // 是
        /**
         * 设备所在区
         */
        public String district; // 是
    }


    /**
     * 修改设备信息 请求
     */
    public static class PutProductDeviceRequest {
        /**
         * 设备名称
         */
        public String name; // 否
        /**
         * 设备序列号
         */
        public String sn; // 否
    }


    /**
     * 修改设备信息 响应
     */
    public static class PutProductDeviceResponse {
        /**
         * 设备ID，32位数字整形
         */
        public int id; // 是
        /**
         * 设备MAC地址，12位十六进制字符
         */
        public String mac; // 是
        /**
         * 设备名称
         */
        public String name; // 否
        /**
         * 是否已激活，布尔值，true或false
         */
        public boolean is_active; // 是
        /**
         * 激活时间，例：2014-10-09T08:15:40.843Z
         */
        public Date active_date; // 否
        /**
         * 是否在线，布尔值，true或false
         */
        public boolean is_online; // 是
        /**
         * 最近登录时间
         */
        public Date last_login; // 否
        /**
         * 最近登录IP
         */
        public String last_login_ip; // 否
        /**
         * MCU型号，20个字符
         */
        public String mcu_mod; // 是
        /**
         * MCU软件版本号，32位数字整形
         */
        public int mcu_version; // 是
        /**
         * 固件型号，20个字符
         */
        public String firmware_mod; // 是
        /**
         * 固件版本号，32位数字整形
         */
        public int firmware_version; // 是
        /**
         * 企业ID
         */
        public String corp_id; // 是
        /**
         * 所属的产品ID
         */
        public String product_id; // 是
        /**
         * 所在区域ID
         */
        public String region_id; // 是
        /**
         * 设备序列号\
         */
        public String sn; // 否
        /**
         * 域
         */
        public String domain; // 否
        /**
         * 设备创建时间
         */
        public Date create_time; // 是
        /**
         * 设备创建者ID
         */
        public String creator_id; // 是
        /**
         * 设备创建者类型，见{@link XLinkEnum.CreatorType}
         */
        public int creator_type; // 是
        /**
         * 设备标签
         */
        public String tags; // 否
        /**
         * 经销商可访问的范围
         */
        public String dealer_scope; // 否
        /**
         * 大客户Id
         */
        public String heavy_buyer; // 否
        /**
         * 所属分组列表
         */
        public String groups; // 否
    }


    /**
     * 设置设备的地理信息 请求
     */
    public static class PutProductDeviceGeographyRequest {
        /**
         * 设备经度
         */
        public double lon; // 是
        /**
         * 设备纬度
         */
        public double lat; // 是
        /**
         * 设备所在国家
         */
        public String country; // 否
        /**
         * 设备所在省
         */
        public String province; // 否
        /**
         * 设备所在市
         */
        public String city; // 否
        /**
         * 设备所在区
         */
        public String district; // 否
    }

}
