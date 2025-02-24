package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readJson(String fileName, Class<T> clazz) throws IOException {
        return objectMapper.readValue(new File(fileName), clazz);
    }

    public static void writeJson(String fileName, Object object) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), object);
    }

    public static <T> T readJsonFromString(String string, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(string, clazz);
    }

    public static String writeObjectToJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
