package cn.xlink.common.http.restful.external.api;

import cn.xlink.common.http.XLinkHttpManager;
import cn.xlink.common.http.restful.external.mode.GoogleMap;
import cn.xlink.common.http.restful.external.mode.ThinkPage;
import retrofit2.Call;
import retrofit2.http.Query;
import rx.Observable;

/**
 * external restful api
 *
 * @author sswukang on 2016/11/14 18:01
 * @version 1.0
 */
public class ExternalHttpApi {
    private GoogleMapApi googleMapApi;
    private ThinkPageApi thinkPageApi;

    public void init(XLinkHttpManager.Config httpConfig) {
        googleMapApi = GoogleMapApi.Builder.createRetrofit(httpConfig.isLogging()).create(GoogleMapApi.class);
        thinkPageApi = ThinkPageApi.Builder.createRetrofit(httpConfig.isLogging()).create(ThinkPageApi.class);
    }

    private static class LazyHolder {
        private static final ExternalHttpApi INSTANCE = new ExternalHttpApi();
    }

    private ExternalHttpApi() {
    }

    public static ExternalHttpApi getInstance() {
        return ExternalHttpApi.LazyHolder.INSTANCE;
    }


    /* -------------------------------- GoogleMapApi -------------------------------- */

    public Call<GoogleMap.MapApiGeocodeResponse> mapApiGeocode(@Query("latlng") String latlng, @Query("language") String lang) {
        return googleMapApi.mapApiGeocode(latlng, lang);
    }

    public Observable<GoogleMap.MapApiGeocodeResponse> mapApiGeocodeObservable(@Query("latlng") String latlng, @Query("language") String lang) {
        return googleMapApi.mapApiGeocodeObservable(latlng, lang);
    }


    /* -------------------------------- ThinkPageApi -------------------------------- */

    public Call<ThinkPage.AirNowResponse> airNow(@Query("key") String key, @Query("location") String location, @Query("language") String language, @Query("scope") String scope) {
        return thinkPageApi.airNow(key, location, language, scope);
    }

    public Observable<ThinkPage.AirNowResponse> airNowObservable(@Query("key") String key, @Query("location") String location, @Query("language") String language, @Query("scope") String scope) {
        return thinkPageApi.airNowObservable(key, location, language, scope);
    }

}
