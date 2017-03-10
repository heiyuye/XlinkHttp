package cn.xlink.retrofithttp.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by liucr on 2017/3/2.
 */

public class JacksonUtil {

    static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将原始Json转为T类型实体
     */
    public static <T> T toEntity(String content, Class<T> classT) {
        T result = null;
        try {
            result = mapper.readValue(content, classT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将原始Json转为T类型实体集合
     */
    public static <T> List<T> toEntityS(String content, Class<T> classT) {
        List<T> result = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, classT);
            result = mapper.readValue(content, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Object 转为  Json
     */
    public static String toJson(Object obj) {
        String result = null;
        try {
            result = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
