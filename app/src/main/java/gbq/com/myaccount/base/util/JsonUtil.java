package gbq.com.myaccount.base.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * json相关操作
 * Created by gbq on 2016-10-3.
 */

public class JsonUtil {
    private static Gson gson = new Gson();

    /**
     * 将json字符串转换为指定的bean对象
     * @param jsonString
     * @param cls 要转换为对象的类型
     * @return
     */
    public static <T> T createJsonBean(String jsonString, Class<T> cls) {
        jsonString = jsonString.replace("\\", "");//去掉'/'
        return gson.fromJson(jsonString,cls);
    }

    /**
     * 将json字符串转换为List<T>
     * @param jsonString
     * @param cls
     * @return
     */
    @SuppressWarnings("unused")
    public static <T> List<T> createJsonToListBean(String jsonString, Class<T> cls) {
        jsonString = jsonString.replace("\\", "");//去掉'/'
        ArrayList<T> mList = new ArrayList<>();
        JsonArray array = new JsonParser().parse(jsonString).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem,cls));
        }
        return mList;
    }

    /**
     * 转换成json格式的字符串
     * @param object 要转换的对象
     * @return
     */
    public static String createJsonString(Object object) {
        return gson.toJson(object);
    }
}
