package cn.xlink.common.http.restful.external.mode;

import java.util.List;

/**
 * Google map api mode
 *
 * @author sswukang on 2016/11/14 17:54
 * @version 1.0
 */
public class GoogleMap {

    public static class MapApiGeocodeResponse {
        public List<AddressComponents> results;

        public static class AddressComponents {
            public List<LocItem> address_components;

            public static class LocItem {
                public String long_name;
                public List<String> types;

                @Override
                public String toString() {
                    return "\n      LocItem{" +
                            "\n         long_name='" + long_name + '\'' +
                            ", \n         types=" + types +
                            "\n      }";
                }
            }

            @Override
            public String toString() {
                return "AddressComponents{" +
                        "\n   address_components=" + address_components +
                        '}';
            }
        }
    }

}
