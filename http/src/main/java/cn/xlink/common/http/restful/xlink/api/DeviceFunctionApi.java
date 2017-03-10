package cn.xlink.common.http.restful.xlink.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cn.xlink.common.http.restful.AccessTokenIntercept;
import cn.xlink.common.http.restful.xlink.AuthProvider;
import cn.xlink.common.http.restful.xlink.mode.DeviceFunction;
import cn.xlink.common.http.restful.xlink.mode.XLinkQuery;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 设备功能接口
 *
 * @author sswukang on 2016/11/11 18:24
 * @version 1.0
 */
interface DeviceFunctionApi {

    class Builder {
        static Retrofit createRetrofit(String baseUrl, final AuthProvider authProvider, boolean logging) {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .registerTypeAdapter(DeviceFunction.ProductVDeviceResponse.class, new VDeviceJsonDeserializer())
                    .registerTypeAdapter(DeviceFunction.ProductDeviceSnapshotResponse.Snapshot.class, new DeviceSnapshotJsonDeserializer())
                    .registerTypeAdapter(XLinkQuery.class, new XLinkQueryJsonSerializer())
                    .create();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson));

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

    class VDeviceJsonDeserializer implements JsonDeserializer<DeviceFunction.ProductVDeviceResponse> {
        private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

        @Override
        public DeviceFunction.ProductVDeviceResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            DeviceFunction.ProductVDeviceResponse vDevice = new DeviceFunction.ProductVDeviceResponse();
            JsonObject jsonObject = json.getAsJsonObject();
            if (jsonObject.has("device_id"))
                vDevice.device_id = jsonObject.get("device_id").getAsInt();
            if (jsonObject.has("cm_id"))
                vDevice.cm_id = jsonObject.get("cm_id").getAsString();
            if (jsonObject.has("ip"))
                vDevice.ip = jsonObject.get("ip").getAsString();
            if (jsonObject.has("online"))
                vDevice.online = jsonObject.get("online").getAsBoolean();
            if (jsonObject.has("last_login")) {
                String last_login = jsonObject.get("last_login").getAsString();
                try {
                    vDevice.last_login = sDateFormat.parse(last_login);
                } catch (ParseException e) {
                    vDevice.last_login = new Date(0);
                }
            }
            if (jsonObject.has("last_logout")) {
                String last_logout = jsonObject.get("last_logout").getAsString();
                try {
                    vDevice.last_logout = sDateFormat.parse(last_logout);
                } catch (ParseException e) {
                    vDevice.last_logout = new Date(0);
                }
            }
            if (jsonObject.has("last_update")) {
                String last_update = jsonObject.get("last_update").getAsString();
                try {
                    vDevice.last_update = sDateFormat.parse(last_update);
                } catch (ParseException e) {
                    vDevice.last_update = new Date(0);
                }
            }
            if (jsonObject.has("online_count"))
                vDevice.online_count = jsonObject.get("online_count").getAsLong();

            vDevice.dataPoints = new HashMap<>();
            for (int i = 0; i < 30; i++) {
                String idx = String.valueOf(i);
                if (jsonObject.has(idx)) {
                    JsonElement value = jsonObject.get(idx);
                    if (value.isJsonPrimitive()) {
                        JsonPrimitive primitive = value.getAsJsonPrimitive();
                        if (primitive.isBoolean()) {
                            vDevice.dataPoints.put(idx, String.valueOf(primitive.getAsBoolean()));
                        } else if (primitive.isNumber()) {
                            vDevice.dataPoints.put(idx, String.valueOf(primitive.getAsNumber()));
                        } else if (primitive.isString()) {
                            vDevice.dataPoints.put(idx, primitive.getAsString());
                        } else {
                            Log.d("DeviceSnapshotApi", "deserialize device snapshot unknown value type: " + primitive);
                        }
                    }
                }
            }
            return vDevice;
        }
    }

    class DeviceSnapshotJsonDeserializer implements JsonDeserializer<DeviceFunction.ProductDeviceSnapshotResponse.Snapshot> {
        private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

        @Override
        public DeviceFunction.ProductDeviceSnapshotResponse.Snapshot deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            DeviceFunction.ProductDeviceSnapshotResponse.Snapshot snapshot = new DeviceFunction.ProductDeviceSnapshotResponse.Snapshot();
            JsonObject jsonObject = json.getAsJsonObject();
            if (jsonObject.has("_id"))
                snapshot._id = jsonObject.get("_id").getAsString();
            if (jsonObject.has("device_id"))
                snapshot.device_id = jsonObject.get("device_id").getAsInt();
            if (jsonObject.has("cm_id"))
                snapshot.cm_id = jsonObject.get("cm_id").getAsString();
            if (jsonObject.has("ip"))
                snapshot.ip = jsonObject.get("ip").getAsString();
            if (jsonObject.has("online"))
                snapshot.online = jsonObject.get("online").getAsBoolean();
            if (jsonObject.has("last_online")) {
                String last_online = jsonObject.get("last_online").getAsString();
                try {
                    snapshot.last_online = sDateFormat.parse(last_online);
                } catch (ParseException e) {
                    snapshot.last_online = new Date(0);
                }
            }
            if (jsonObject.has("last_offline")) {
                String last_offline = jsonObject.get("last_offline").getAsString();
                try {
                    snapshot.last_offline = sDateFormat.parse(last_offline);
                } catch (ParseException e) {
                    snapshot.last_offline = new Date(0);
                }
            }
            if (jsonObject.has("last_update")) {
                String last_update = jsonObject.get("last_update").getAsString();
                try {
                    snapshot.last_update = sDateFormat.parse(last_update);
                } catch (ParseException e) {
                    snapshot.last_update = new Date(0);
                }
            }
            if (jsonObject.has("snapshot_date")) {
                String snapshot_date = jsonObject.get("snapshot_date").getAsString();
                try {
                    snapshot.snapshot_date = sDateFormat.parse(snapshot_date);
                } catch (ParseException e) {
                    snapshot.snapshot_date = new Date(0);
                }
            }
            snapshot.dataPoints = new HashMap<>();
            for (int i = 0; i < 30; i++) {
                String idx = String.valueOf(i);
                if (jsonObject.has(idx)) {
                    JsonElement value = jsonObject.get(idx);
                    if (value.isJsonPrimitive()) {
                        JsonPrimitive primitive = value.getAsJsonPrimitive();
                        if (primitive.isBoolean()) {
                            snapshot.dataPoints.put(idx, String.valueOf(primitive.getAsBoolean()));
                        } else if (primitive.isNumber()) {
                            snapshot.dataPoints.put(idx, String.valueOf(primitive.getAsNumber()));
                        } else if (primitive.isString()) {
                            snapshot.dataPoints.put(idx, primitive.getAsString());
                        } else {
                            Log.d("DeviceSnapshotApi", "deserialize device snapshot unknown value type: " + primitive);
                        }
                    }
                }
            }
            return snapshot;
        }
    }

    class XLinkQueryJsonSerializer implements JsonSerializer<XLinkQuery> {
        private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

        @Override
        public JsonElement serialize(XLinkQuery src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            if (src == null) {
                return result;
            }
            if (src instanceof XLinkQuery.In) {
                if (((XLinkQuery.In) src).$in != null && ((XLinkQuery.In) src).$in.size() != 0) {
                    if (((XLinkQuery.In) src).$in.get(0) instanceof Date) {
                        JsonArray dateArray = new JsonArray();
                        for (Object date : ((XLinkQuery.In) src).$in) {
                            JsonObject dateObject = new JsonObject();
                            dateObject.addProperty("@date", sDateFormat.format(date));
                            dateArray.add(dateObject);
                        }
                        result.add("$in", dateArray);
                    } else {
                        result.add("$in", context.serialize(((XLinkQuery.In) src).$in));
                    }
                }
                return result;
            }
            if (src instanceof XLinkQuery.Greater) {
                if (((XLinkQuery.Greater) src).$gt != null) {
                    if (((XLinkQuery.Greater) src).$gt instanceof Date) {
                        JsonObject dateObject = new JsonObject();
                        dateObject.addProperty("@date", sDateFormat.format(((XLinkQuery.Greater) src).$gt));
                        result.add("$gt", dateObject);
                    } else {
                        result.add("$gt", context.serialize(((XLinkQuery.Greater) src).$gt));
                    }
                }
                return result;
            }
            if (src instanceof XLinkQuery.GreaterAndEqual) {
                if (((XLinkQuery.GreaterAndEqual) src).$gte != null) {
                    if (((XLinkQuery.GreaterAndEqual) src).$gte instanceof Date) {
                        JsonObject dateObject = new JsonObject();
                        dateObject.addProperty("@date", sDateFormat.format(((XLinkQuery.GreaterAndEqual) src).$gte));
                        result.add("$gte", dateObject);
                    } else {
                        result.add("$gte", context.serialize(((XLinkQuery.GreaterAndEqual) src).$gte));
                    }
                }
                return result;
            }
            if (src instanceof XLinkQuery.Less) {
                if (((XLinkQuery.Less) src).$lt != null) {
                    if (((XLinkQuery.Less) src).$lt instanceof Date) {
                        JsonObject dateObject = new JsonObject();
                        dateObject.addProperty("@date", sDateFormat.format(((XLinkQuery.Less) src).$lt));
                        result.add("$lt", dateObject);
                    } else {
                        result.add("$lt", context.serialize(((XLinkQuery.Less) src).$lt));
                    }
                }
                return result;
            }
            if (src instanceof XLinkQuery.LessAndEqual) {
                if (((XLinkQuery.LessAndEqual) src).$lte != null) {
                    if (((XLinkQuery.LessAndEqual) src).$lte instanceof Date) {
                        JsonObject dateObject = new JsonObject();
                        dateObject.addProperty("@date", sDateFormat.format(((XLinkQuery.LessAndEqual) src).$lte));
                        result.add("$lte", dateObject);
                    } else {
                        result.add("$lte", context.serialize(((XLinkQuery.LessAndEqual) src).$lte));
                    }
                }
                return result;
            }
            if (src instanceof XLinkQuery.Regex) {
                if (((XLinkQuery.Regex) src).$regex != null) {
                    if (((XLinkQuery.Regex) src).$regex instanceof Date) {
                        JsonObject dateObject = new JsonObject();
                        dateObject.addProperty("@date", sDateFormat.format(((XLinkQuery.Regex) src).$regex));
                        result.add("$regex", dateObject);
                    } else {
                        result.add("$regex", context.serialize(((XLinkQuery.Regex) src).$regex));
                    }
                }
                return result;
            }
            return result;
        }
    }


    /**
     * 1.用户注册一个新的设备
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/register_device")
    Call<DeviceFunction.UserRegisterDeviceResponse> userRegisterDevice(@Path("user_id") int userId, @Body DeviceFunction.UserRegisterDeviceRequest request);

    /**
     * 1.用户注册一个新的设备
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/register_device")
    Observable<DeviceFunction.UserRegisterDeviceResponse> userRegisterDeviceObservable(@Path("user_id") int userId, @Body DeviceFunction.UserRegisterDeviceRequest request);


    /**
     * 2.用户获取设备列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/user/{user_id}/subscribe/devices")
    Call<List<DeviceFunction.UserSubscribeDevicesResponse>> userSubscribeDevices(@Path("user_id") int userId);

    /**
     * 2.用户获取设备列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/user/{user_id}/subscribe/devices")
    Observable<List<DeviceFunction.UserSubscribeDevicesResponse>> userSubscribeDevicesObservable(@Path("user_id") int userId);


    /**
     * 3.设置设备的扩展属性
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/product/{product_id}/device/{device_id}/property")
    Call<DeviceFunction.DeviceProperty> postProductDeviceProperty(@Path("product_id") String productId, @Path("device_id") int deviceId, @Body DeviceFunction.DeviceProperty request);

    /**
     * 3.设置设备的扩展属性
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/product/{product_id}/device/{device_id}/property")
    Observable<DeviceFunction.DeviceProperty> postProductDevicePropertyObservable(@Path("product_id") String productId, @Path("device_id") int deviceId, @Body DeviceFunction.DeviceProperty request);


    /**
     * 4.获取设备的扩展属性列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/product/{product_id}/device/{device_id}/property")
    Call<DeviceFunction.DeviceProperty> getProductDeviceProperty(@Path("product_id") String productId, @Path("device_id") int deviceId);

    /**
     * 4.获取设备的扩展属性列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/product/{product_id}/device/{device_id}/property")
    Observable<DeviceFunction.DeviceProperty> getProductDevicePropertyObservable(@Path("product_id") String productId, @Path("device_id") int deviceId);


    /**
     * 5.取消订阅设备
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/unsubscribe")
    Call<Void> userUnSubscribe(@Path("user_id") int userId, @Body DeviceFunction.UserUnSubscribeRequest request);

    /**
     * 5.取消订阅设备
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/unsubscribe")
    Observable<Void> userUnSubscribeObservable(@Path("user_id") int userId, @Body DeviceFunction.UserUnSubscribeRequest request);


    /**
     * 6.获取设备用户列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/user/{user_id}/subscribe_users")
    Call<DeviceFunction.DeviceSubscribeUsersResponse> deviceSubscribeUsers(@Path("user_id") int userId, @Query("device_id") String deviceId);

    /**
     * 6.获取设备用户列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/user/{user_id}/subscribe_users")
    Observable<DeviceFunction.DeviceSubscribeUsersResponse> deviceSubscribeUsersObservable(@Path("user_id") int userId, @Query("device_id") String deviceId);


    /**
     * 7.设备分享
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/share/device")
    Call<DeviceFunction.ShareDeviceResponse> shareDevice(@Body DeviceFunction.ShareDeviceRequest request);

    /**
     * 7.设备分享
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/share/device")
    Observable<DeviceFunction.ShareDeviceResponse> shareDeviceObservable(@Body DeviceFunction.ShareDeviceRequest request);


    /**
     * 8.取消分享
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/share/device/cancel")
    Call<Void> shareDeviceCancel(@Body DeviceFunction.ShareDeviceCancelRequest request);

    /**
     * 8.取消分享
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/share/device/cancel")
    Observable<Void> shareDeviceCancelObservable(@Body DeviceFunction.ShareDeviceCancelRequest request);


    /**
     * 9.接受分享
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/share/device/accept")
    Call<Void> shareDeviceAccept(@Body DeviceFunction.ShareDeviceAcceptRequest request);

    /**
     * 9.接受分享
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/share/device/accept")
    Observable<Void> shareDeviceAcceptObservable(@Body DeviceFunction.ShareDeviceAcceptRequest request);


    /**
     * 10.拒绝分享
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/share/device/deny")
    Call<Void> shareDeviceDeny(@Body DeviceFunction.ShareDeviceDenyRequest request);

    /**
     * 10.拒绝分享
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/share/device/deny")
    Observable<Void> shareDeviceDenyObservable(@Body DeviceFunction.ShareDeviceDenyRequest request);


    /**
     * 11.获取设备分享列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/share/device/list")
    Call<List<DeviceFunction.ShareDeviceListResponse>> shareDeviceList();

    /**
     * 11.获取设备分享列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/share/device/list")
    Observable<List<DeviceFunction.ShareDeviceListResponse>> shareDeviceListObservable();


    /**
     * 12.删除设备分享记录
     */
    @Headers({
            "Content-Type: application/json"
    })
    @DELETE("/v2/share/device/delete/{invite_code}")
    Call<Void> shareDeviceDelete(@Path("invite_code") String inviteCode);

    /**
     * 12.删除设备分享记录
     */
    @Headers({
            "Content-Type: application/json"
    })
    @DELETE("/v2/share/device/delete/{invite_code}")
    Observable<Void> shareDeviceDeleteObservable(@Path("invite_code") String inviteCode);


    /**
     * 13.用户获取设备最新版本
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/upgrade/device/newest_version")
    Call<DeviceFunction.UpgradeDeviceNewestVersionResponse> upgradeDeviceNewestVersion(@Body DeviceFunction.UpgradeDeviceNewestVersionRequest request);

    /**
     * 13.用户获取设备最新版本
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/upgrade/device/newest_version")
    Observable<DeviceFunction.UpgradeDeviceNewestVersionResponse> upgradeDeviceNewestVersionObservable(@Body DeviceFunction.UpgradeDeviceNewestVersionRequest request);


    /**
     * 14. 用户手动升级设备
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/upgrade/device")
    Call<Void> upgradeDevice(@Body DeviceFunction.UpgradeDeviceRequest request);

    /**
     * 14. 用户手动升级设备
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/upgrade/device")
    Observable<Void> upgradeDeviceObservable(@Body DeviceFunction.UpgradeDeviceRequest request);


    /**
     * 15. 获取虚拟设备数据
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/product/{product_id}/v_device/{device_id}")
    Call<DeviceFunction.ProductVDeviceResponse> productVDevice(@Path("product_id") String product_id, @Path("device_id") int device_id);

    /**
     * 15. 获取虚拟设备数据
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/product/{product_id}/v_device/{device_id}")
    Observable<DeviceFunction.ProductVDeviceResponse> productVDeviceObservable(@Path("product_id") String product_id, @Path("device_id") int device_id);


    /**
     * 16. 获取设备快照
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/product/{product_id}/device/{device_id}/snapshot")
    Call<DeviceFunction.ProductDeviceSnapshotResponse> productDeviceSnapshot(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.ProductDeviceSnapshotRequest request);

    /**
     * 16. 获取设备快照
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/product/{product_id}/device/{device_id}/snapshot")
    Observable<DeviceFunction.ProductDeviceSnapshotResponse> productDeviceSnapshotObservable(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.ProductDeviceSnapshotRequest request);


    /**
     * 17. 获取数据端点列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/product/{product_id}/datapoints")
    Call<List<DeviceFunction.ProductDataPointsResponse>> productDataPoints(@Path("product_id") String productId);

    /**
     * 17. 获取数据端点列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/product/{product_id}/datapoints")
    Observable<List<DeviceFunction.ProductDataPointsResponse>> productDataPointsObservable(@Path("product_id") String productId);


    /**
     * 18. 获取设备告警日志列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/product/{product_id}/device/{device_id}/alert_logs")
    Call<DeviceFunction.ProductDeviceAlertLogsResponse> productDeviceAlertLogs(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.ProductDeviceAlertLogsRequest request);

    /**
     * 18. 获取设备告警日志列表
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/product/{product_id}/device/{device_id}/alert_logs")
    Observable<DeviceFunction.ProductDeviceAlertLogsResponse> productDeviceAlertLogsObservable(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.ProductDeviceAlertLogsRequest request);


    /**
     * 19. 通过二维码订阅设备
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/qrcode_subscribe")
    Call<DeviceFunction.UserQRCodeSubscribeResponse> userQRCodeSubscribe(@Path("user_id") int user_id, @Body DeviceFunction.UserQRCodeSubscribeRequest dataRequest);

    /**
     * 19. 通过二维码订阅设备
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/v2/user/{user_id}/qrcode_subscribe")
    Observable<DeviceFunction.UserQRCodeSubscribeResponse> userQRCodeSubscribeObservable(@Path("user_id") int user_id, @Body DeviceFunction.UserQRCodeSubscribeRequest dataRequest);


    /**
     * 20. 获取设备地理位置信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/product/{product_id}/device/{device_id}/geography")
    Call<DeviceFunction.GetProductDeviceGeographyResponse> getProductDeviceGeography(@Path("product_id") String product_id, @Path("device_id") int device_id);

    /**
     * 20. 获取设备地理位置信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/v2/product/{product_id}/device/{device_id}/geography")
    Observable<DeviceFunction.GetProductDeviceGeographyResponse> getProductDeviceGeographyObservable(@Path("product_id") String product_id, @Path("device_id") int device_id);


    /**
     * 21. 修改设备信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/product/{product_id}/device/{device_id}")
    Call<DeviceFunction.PutProductDeviceResponse> putProductDevice(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.PutProductDeviceRequest request);

    /**
     * 21. 修改设备信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/product/{product_id}/device/{device_id}")
    Observable<DeviceFunction.PutProductDeviceResponse> putProductDeviceObservable(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.PutProductDeviceRequest request);


    /**
     * 22. 设置设备的地理信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/product/{product_id}/device/{device_id}/geography")
    Call<Void> putProductDeviceGeography(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.PutProductDeviceGeographyRequest request);

    /**
     * 22. 设置设备的地理信息
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/v2/product/{product_id}/device/{device_id}/geography")
    Observable<Void> putProductDeviceGeographyObservable(@Path("product_id") String product_id, @Path("device_id") int device_id, @Body DeviceFunction.PutProductDeviceGeographyRequest request);

}
