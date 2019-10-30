package collection;

import com.google.common.collect.ImmutableMap;
import lombok.val;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;
import java.util.stream.Collectors;

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
            put("url5", "http://www.ifeng2.com");//凤凰网
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

    public static void travel() {

        urlMap.forEach((k, v) -> System.out.println("key : " + k + " value : " + v));

        urlMap.entrySet().forEach(entry -> {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        });

        for (Map.Entry<String, String> entry : urlMap.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        for (String key : urlMap.keySet()) {
            System.out.println("Key : " + key + " value : " + urlMap.get(key));
        }


        Object[] keys = urlMap.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            System.out.println("i: " + i);
            System.out.println("Key : " + keys[i] + " value : " + urlMap.get(keys[i]));
        }

        Iterator<Map.Entry<String, String>> iterator = urlMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        Iterator<String> keyIterator = urlMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = keyIterator.next();
            System.out.println("Key : " + key + " value : " + urlMap.get(key));
        }
    }

    public static void main(String[] args) {

        urlMap.forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("---------");
//
//        urlMap.put("url1", "http://news2.sina.com.cn");
//        urlMap.put(null, "http://news3.sina.com.cn");
//        urlMap.put("url6", null);
//
//        urlMap.forEach((k, v) -> System.out.println(k + " : " + v));

        Map<String, String> result = new LinkedHashMap<>();
        urlMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue, LinkedHashMap::new))
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
      result.forEach((k, vv) -> System.out.println(k + " : " + vv));

        System.out.println("---------");
        List<Object> keyList = new ArrayList<>(result.keySet());
        keyList.forEach(System.out::println);

    }


}
