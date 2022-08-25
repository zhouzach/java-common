package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


public class JsonUtils {

    private static ObjectMapper objectMapper = null;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));

        //忽略NULL值
        //objectMapper.setDefaultPropertyInclusion(
        //        JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.NON_NULL));
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    public static JsonNode readTree(String json) throws IOException {
        return objectMapper.readTree(json);
    }

    public static JsonNode addNode(JsonNode root, String fieldName, String value) {
        ObjectNode on = (ObjectNode) root;
        return on.put(fieldName, value);
    }

    public static JsonNode addNode(JsonNode root, String fieldName, JsonNode value) {
        ObjectNode on = (ObjectNode) root;

        return on.set(fieldName, value);
    }

    public static JsonNode addTree(JsonNode root, String key, String fieldName, String value) {
        ObjectNode tree = objectMapper.createObjectNode();
        tree.put(fieldName, value);

        ObjectNode obj = (ObjectNode) root;
        return obj.set(key, tree);
    }

    public static JsonNode addTree(JsonNode root, JsonNode tree) {
        ObjectNode rootNode = (ObjectNode) root;
        ObjectNode treeNode = (ObjectNode) tree;
        return rootNode.setAll(treeNode);
    }

    public static JsonNode removeNode(JsonNode root, String fieldName){
        if (root.isObject()) {
            ObjectNode node = (ObjectNode) root;
            return node.remove(fieldName);

        } else if (root.isArray()) {
            ArrayNode arrayField = (ArrayNode) root;

            arrayField.forEach(node -> {
                removeNode(node, fieldName);

            });
        }

        return root;
    }

    public static JsonNode renameNode(JsonNode root, String fieldName, String newName){
        return addNode(root, newName, removeNode(root, fieldName));
    }


    public static String writeValueAsString(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static void write(File file, Object obj) throws IOException {
        objectMapper.writeValue(file, obj);
    }

    public static <T> T readTree(File file, Class<T> clazz) throws IOException {
        if (!file.exists() || file.isDirectory()) {
            return null;
        }

        return objectMapper.readValue(file, clazz);
    }

    public static <E> List<E> convertList(List list, Class<E> clazz) {
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.convertValue(list, type);
    }


    public static <T> T readTree(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public static <T> T readTree(InputStream input, Class<T> clazz) throws IOException {
        return objectMapper.readValue(input, clazz);
    }

    public static <T> T parse(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public static <E> List<E> parseList(String json, Class<E> clazz) throws Exception {
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.readValue(json, type);
    }

    public static <T> T convert(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static <T> T readTree(String json, TypeReference reference) throws IOException {
        if (json == null || json.length() == 0) {
            return null;
        }

        return objectMapper.readValue(json, reference);
    }

}
