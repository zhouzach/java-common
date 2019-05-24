package utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * handle requestBody
 * https://www.programcreek.com/java-api-examples/?api=com.squareup.okhttp.ResponseBody
 */
public class HttpClientStarter {

    private static Logger logger = LoggerFactory.getLogger(HttpClientStarter.class);

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient buildeClient(long connectTimeout, long readTimeout, long writeTimeout) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        return builder.build();
    }

    public static String post(String url, RequestBody body) {
        return post(url, body, 60 * 60 * 24, 60 * 60 * 24, 60 * 60 * 24);
    }

    public static String post(String url, RequestBody body, long connectTimeout, long readTimeout, long writeTimeout) {

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = buildeClient(connectTimeout, readTimeout, writeTimeout).newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    public static void main(String[] args) {
        HttpClientStarter starter = new HttpClientStarter();

        String postUrl = "";

        JSONObject postJson = new JSONObject();
        postJson.put("begin", "");
        postJson.put("end", "");
        RequestBody body = RequestBody.create(JSON, postJson.toString());

        String response = post(postUrl, body);

        System.out.println(response);

        String url = "";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PARAM", "");
        jsonObject.put("VALUE", "");
        jsonObject.put("OPERATOR", "=");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);

        RequestBody formBody = new FormBody.Builder()
                .add("", "")
                .add("", jsonArray.toString())
                .build();

        String response1 = post(url, formBody);

        System.out.println(response1);
    }
}
