package cn.xlink.common.http.restful.external.api;


import cn.xlink.common.http.restful.external.mode.ExternalEnum;
import cn.xlink.common.http.restful.external.mode.GoogleMap;
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
 * Google map api
 *
 * @author sswukang on 2016/11/14 17:38
 * @version 1.0
 */
interface GoogleMapApi {
    String HOST = "http://maps.google.cn/";

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
     * 逆地理编码
     *
     * @param latlng 维度,经度
     * @param lang   响应语言 {@link ExternalEnum.GoogleMapLanguage}
     */
    @GET("/maps/api/geocode/json")
    // ?latlng=23.1167,113.25&language=CN
    Call<GoogleMap.MapApiGeocodeResponse> mapApiGeocode(@Query("latlng") String latlng, @Query("language") String lang);

    /**
     * 逆地理编码
     *
     * @param latlng 维度,经度
     * @param lang   响应语言 {@link ExternalEnum.GoogleMapLanguage}
     */
    @GET("/maps/api/geocode/json")
    // ?latlng=23.1167,113.25&language=CN
    Observable<GoogleMap.MapApiGeocodeResponse> mapApiGeocodeObservable(@Query("latlng") String latlng, @Query("language") String lang);

}
