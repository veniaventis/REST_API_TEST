package utils;

import api.models.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static utils.ConfigUtils.getConfigString;


public class JsonUtils {

    public static boolean fileIsJson(String file){
        try {
            new JSONParser().parse(file);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String deserializationObject(Post jsonPersonModel){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(jsonPersonModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    public static String getExpectedJson(String fileName){
        String path = getConfigString("expectedFiles");
        JSONParser parser =  new JSONParser();
        try {
            JSONObject jsonExpected = (JSONObject) parser.parse(new FileReader(String.format("%s%s.json",path,fileName)));
            return jsonExpected.toString();
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
