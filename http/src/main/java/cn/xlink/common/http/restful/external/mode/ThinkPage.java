package cn.xlink.common.http.restful.external.mode;

import java.util.List;

/**
 * 心知天气接口 mode
 *
 * @author sswukang on 2016/11/14 17:45
 * @version 1.0
 */
public class ThinkPage {

    public static class AirNowResponse {
        public List<Results> results;

        public static class Results {
            public Location location;
            public Air air;
            public String last_update;

            public static class Location {
                public String id;
                public String name;
                public String country;
                public String path;
                public String timezone;
                public String timezone_offset;
            }

            public static class Air {
                public City city;
                public List<Stations> stations;

                public static class City {
                    public double aqi;
                    public double pm25;
                    public double pm10;
                    public double so2;
                    public double no2;
                    public double co;
                    public double o3;
                    public String quality;
                    public String last_update;
                }

                public static class Stations {
                    public double aqi;
                    public double pm25;
                    public double pm10;
                    public double so2;
                    public double no2;
                    public double co;
                    public double o3;
                    public String station;
                    public double latitude;
                    public double longitude;
                    public String last_update;
                }
            }
        }
    }

}
