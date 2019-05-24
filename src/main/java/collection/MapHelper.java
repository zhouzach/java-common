package collection;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Map;
public class MapHelper {

    public static Pair<Integer, ArrayList<String>> get(Map<Integer, ArrayList<String>> map){
        Map.Entry<Integer, ArrayList<String>> entry = map.entrySet().iterator().next();
        Integer columnSize = entry.getKey();
        ArrayList<String> aReportArray = entry.getValue();
        return Pair.with(columnSize, aReportArray);
    }


    public static Triplet<String, Integer, ArrayList<String>> getTriplet(Map<String, ArrayList<String>> map){
        Map.Entry<String, ArrayList<String>> entry = map.entrySet().iterator().next();
        String field = entry.getKey();
        ArrayList<String> aReportArray = entry.getValue();
        return Triplet.with(field, 10, aReportArray);
    }
}
