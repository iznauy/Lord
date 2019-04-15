package top.iznauy.framework.web;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public final class JsonUtil {

    private static final Gson gson = new Gson();


    public static <T> String toJson(T obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }
}
