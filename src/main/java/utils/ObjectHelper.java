package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ObjectHelper {

    public static Map<String, Object> toMap(Object object) {
        ObjectMapper oMapper = new ObjectMapper();
        return oMapper.convertValue(object, Map.class);
    }
}
