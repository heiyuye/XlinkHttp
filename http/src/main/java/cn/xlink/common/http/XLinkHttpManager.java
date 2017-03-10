package cn.xlink.common.http;

import cn.xlink.common.http.restful.external.api.ExternalHttpApi;
import cn.xlink.common.http.restful.xlink.AuthProvider;
import cn.xlink.common.http.restful.xlink.api.XLinkHttpApi;

/**
 * xlink http 请求
 *
 * @author sswukang on 2016/11/8 13:49
 * @version 1.0
 */
public class XLinkHttpManager {

    public void init(Config httpConfig) {
        getXLinkHttpApi().init(httpConfig);
        getExternalHttpApi().init(httpConfig);
    }

    public XLinkHttpApi getXLinkHttpApi() {
        return XLinkHttpApi.getInstance();
    }

    public ExternalHttpApi getExternalHttpApi() {
        return ExternalHttpApi.getInstance();
    }

    private static class LazyHolder {
        private static final XLinkHttpManager INSTANCE = new XLinkHttpManager();
    }

    public static XLinkHttpManager getInstance() {
        return LazyHolder.INSTANCE;
    }


    public static class Config {
        // 基址
        private String baseUrl;
        // 日志开关
        private boolean logging;
        // 自动重认证
        private AuthProvider provider;

        public Config() {
            baseUrl = "http://api2.xlink.cn";
            logging = true;
            provider = null;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public Config setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public boolean isLogging() {
            return logging;
        }

        public Config setLogging(boolean logging) {
            this.logging = logging;
            return this;
        }

        public AuthProvider getProvider() {
            return provider;
        }

        public Config setProvider(AuthProvider provider) {
            this.provider = provider;
            return this;
        }
    }
}
