package cn.xlink.common.http.restful.external.mode;

/**
 * external枚举附录
 *
 * @author sswukang on 2016/11/14 17:48
 * @version 1.0
 */
public class ExternalEnum {

    /**
     * 语言 (可选)
     */
    enum ThinkPageLanguage {
        /**
         * 简体中文
         */
        ZH_HANS("zh-Hans"),
        /**
         * 繁体中文
         */
        ZH_HANT("zh-Hant"),
        /**
         * 英文
         */
        EN("en"),
        /**
         * 日语
         */
        JA("ja"),
        /**
         * 德语
         */
        DE("de"),
        /**
         * 法语
         */
        FR("fr"),
        /**
         * 印地语
         */
        HI("hi"),
        /**
         * 印度尼西亚语
         */
        ID("id"),
        /**
         * 俄语
         */
        RU("ru"),
        /**
         * 泰语
         */
        TH("th");

        private String value;

        ThinkPageLanguage(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 范围 (可选)
     */
    enum ThinkPageScope {
        /**
         * 城市平均值
         */
        CITY("city"),
        /**
         * 所有监测站监测值
         */
        All("all");

        private String value;

        ThinkPageScope(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    /**
     * google map 响应语言
     */
    enum GoogleMapLanguage {
        /**
         * 中文
         */
        ZH("CN"),
        /**
         * 英文
         */
        EN("EN"),
        /**
         * 日语
         */
        JA("JA");

        private String value;

        GoogleMapLanguage(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
