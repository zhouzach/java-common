package collection;

import com.google.common.collect.ImmutableMap;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Java tuple – Working with tuples in Java
 * https://howtodoinjava.com/java/basics/java-tuples/
 */
public class MapHelper {

    private static final Map<String, String> urlMap = new HashMap<String, String>() {
        {
            put("url1", "http://news.sina.com.cn");//新浪新闻
            put("url2", "http://news.163.com");//网易新闻
            put("url3", "http://news.qq.com");//腾讯新闻
            put("url4", "http://news.baidu.com");//百度新闻
            put("url5", "http://www.ifeng.com");//凤凰网
        }
    };

    // must import guava lib
    public static void initByGuava() {

        Map<String, Integer> one = ImmutableMap.of("a", 1, "b", 2, "c", 3);

        Map<String, String> two = ImmutableMap.<String, String>builder()
                .put("k1", "v1")
                .put("k2", "v2")
                .build();
    }

    public static Pair<Integer, ArrayList<String>> get(Map<Integer, ArrayList<String>> map) {
        Map.Entry<Integer, ArrayList<String>> entry = map.entrySet().iterator().next();
        Integer columnSize = entry.getKey();
        ArrayList<String> aReportArray = entry.getValue();
        return Pair.with(columnSize, aReportArray);
    }


    public static Triplet<String, Integer, ArrayList<String>> getTriplet(Map<String, ArrayList<String>> map) {
        Map.Entry<String, ArrayList<String>> entry = map.entrySet().iterator().next();
        String field = entry.getKey();
        ArrayList<String> aReportArray = entry.getValue();
        return Triplet.with(field, 10, aReportArray);
    }


}
