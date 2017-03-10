package cn.xlink.common.http.restful.external.api;


import cn.xlink.common.http.restful.external.mode.ExternalEnum;
import cn.xlink.common.http.restful.external.mode.ThinkPage;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 心知天气 api
 *
 * @author sswukang on 2016/11/14 17:28
 * @version 1.0
 */
interface ThinkPageApi {
    String HOST = "https://api.thinkpage.cn";

    class Builder {
        static Retrofit createRetrofit(boolean logging) {

            Retrofit.Builder builder = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(HOST)
                    .addConverterFactory(GsonConverterFactory.create());

            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
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
     * 空气质量实况
     *
     * @param key      你的API密钥
     * @param location 所查询的位置
     *                 城市ID 例如：location=WX4FBXXFKE4F
     *                 城市中文名 例如：location=北京
     *                 城市拼音/英文名 例如：location=beijing
     *                 经纬度 例如：location=39.93:116.40 （注意纬度前经度在后，冒号分隔）
     *                 IP地址 例如：location=220.181.111.86
     *                 “ip”两个字母 自动识别请求IP地址，例如：location=ip
     * @param language 语言 (可选) {@link ExternalEnum.ThinkPageLanguage}
     * @param scope    范围 (可选) {@link ExternalEnum.ThinkPageScope}
     * @return 获取指定城市的AQI、PM2.5、PM10、一氧化碳、二氧化氮、臭氧等空气质量信息
     */
    @GET("/v3/air/now.json")
    // ?key=l5ktthtkjvlmoofx&location=beijing&language=zh-Hans&scope=city
    Call<ThinkPage.AirNowResponse> airNow(@Query("key") String key, @Query("location") String location,
                                          @Query("language") String language, @Query("scope") String scope);

    /**
     * 空气质量实况
     *
     * @param key      你的API密钥
     * @param location 所查询的位置
     *                 城市ID 例如：location=WX4FBXXFKE4F
     *                 城市中文名 例如：location=北京
     *                 城市拼音/英文名 例如：location=beijing
     *                 经纬度 例如：location=39.93:116.40 （注意纬度前经度在后，冒号分隔）
     *                 IP地址 例如：location=220.181.111.86
     *                 “ip”两个字母 自动识别请求IP地址，例如：location=ip
     * @param language 语言 (可选) {@link ExternalEnum.ThinkPageLanguage}
     * @param scope    范围 (可选) {@link ExternalEnum.ThinkPageScope}
     * @return 获取指定城市的AQI、PM2.5、PM10、一氧化碳、二氧化氮、臭氧等空气质量信息
     */
    @GET("/v3/air/now.json")
    // ?key=l5ktthtkjvlmoofx&location=beijing&language=zh-Hans&scope=city
    Observable<ThinkPage.AirNowResponse> airNowObservable(@Query("key") String key, @Query("location") String location,
                                                          @Query("language") String language, @Query("scope") String scope);

}
