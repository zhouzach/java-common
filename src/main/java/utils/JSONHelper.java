package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import models.Report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JSONHelper {

    public static String toJson(Report.Cell cell) {
        return JSON.toJSONString(cell);
    }

    public static JSONObject pase(String responseBody) {
        return JSON.parseObject(responseBody, JSONObject.class);
    }

    public static JSONArray get(JSONObject jsonObject, String key) {
        return jsonObject.getJSONArray(key);
    }

    public static JSONObject getNode(JSONObject jsonObject, String key) {
        return jsonObject.getJSONObject(key);
    }

    public Report.Cell convert(Object object) {
        JSONObject jsonObject = (JSONObject) object;
        Report.Cell cell = new Report.Cell();
        Integer x = jsonObject.getInteger("x-index");
        Integer y = jsonObject.getInteger("y-index");
        String value = jsonObject.getString("value");
        cell.setX(x);
        cell.setY(y);
        cell.setValue(value);
        return cell;
    }

    /**
     *
     * https://github.com/json-path/JsonPath
     *
     * JsonPath will automatically try to cast the result to the type expected by the invoker.
     *
     */
    public static void readJson2List(String json) {
        List<String> authors = JsonPath.read(json, "$..book[?(@.author =~ /.*REES/i)]");
        System.out.println(Arrays.toString(authors.toArray()));
    }

    public static void readJson2Map(String json) {
        String name = "store1";
        Map store = JsonPath.read(json, "$['" + name + "']");

        System.out.println(store);
    }

    public static String getJsonStr(){
        String str =
              "{\n" +
                "\"store1\": {\n" +
                "        \"pic\": [\n" +
                "            {\n" +
                "                \"category\": \"aaaa\",\n" +
                "                \"author\": \"Tom\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 108.95\n" +
                "            },\n" +
                "            \n" +
                "            {\n" +
                "                \"category\": \"bbbbb\",\n" +
                "                \"author\": \"Jack\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 322.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"black\",\n" +
                "            \"price\": 222222222\n" +
                "        }\n" +
                "    }," +

                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";
        return str;
    }


    public static String getKafkaMessage(){
        String string = "{\"@timestamp\":\"2020-04-29T03:29:37.696Z\",\"@metadata\":{\"beat\":\"filebeat\",\"type\":\"_doc\",\"version\":\"7.1.0\",\"topic\":\"qile-report\"},\"agent\":{\"ephemeral_id\":\"65137625-0d8c-47c5-9f17-59538c214549\",\"hostname\":\"iZuf60wmslifw4wptk9cqeZ\",\"id\":\"6cdba9e6-4d3d-4909-9562-536decc4e712\",\"version\":\"7.1.0\",\"name\":\"test\",\"type\":\"filebeat\"},\"log\":{\"offset\":874,\"file\":{\"path\":\"/data/log/qile-zhibo-game/qile-zhibo-game-treasure_box-2020-04-29.log\"}},\"message\":\"85517,20200429,1000,384,3,700\",\"fields\":{\"tags\":\"treasure_box\",\"tag\":\"qile-report\"},\"input\":{\"type\":\"log\"},\"ecs\":{\"version\":\"1.0.0\"},\"host\":{\"name\":\"test\"}}";
        return string;
    }

    public static void main(String[] args) {
//        String json = getJsonStr();
//        readJson2Map(json);
//        readJson2List(json);

//        List<Integer> obj = new ArrayList<>();
//        obj.add(1);
//        obj.add(2);
//
//        List<String> s = new ArrayList<>();
//        s.add("a");
//        s.add("b");
//
//        String json = JSON.toJSONString(s);
//        System.out.println(json);



        String json = getKafkaMessage();
        Object o=JsonPath.read(json, "$.message");
        System.out.println(o);
    }
}
