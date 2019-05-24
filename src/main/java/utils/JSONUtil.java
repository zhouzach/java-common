package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import models.Report;

public class JSONUtil {
    public static JSONObject pase(String responseBody) {
        return JSON.parseObject(responseBody, JSONObject.class);
    }

    public static JSONArray get(JSONObject jsonObject, String key){
        return jsonObject.getJSONArray(key);
    }

    public static JSONObject getNode(JSONObject jsonObject, String key){
        return jsonObject.getJSONObject(key);
    }

    public Report.Cell convert(Object object) {
        JSONObject jsonObject= (JSONObject) object;
        Report.Cell cell = new Report.Cell();
        Integer x = jsonObject.getInteger("x-index");
        Integer y = jsonObject.getInteger("y-index");
        String value = jsonObject.getString("value");
        cell.setX(x);
        cell.setY(y);
        cell.setValue(value);
        return cell;
    }
}
