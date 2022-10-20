package utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static Object jsonToObject(String path, Class object){
            ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(FileUtils.parseJson(path), object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
