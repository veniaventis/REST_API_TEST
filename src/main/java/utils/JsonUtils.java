package utils;

import api.models.Post;
import api.models.User;
import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NotFoundException;

import java.io.FileReader;
import java.io.IOException;

import static utils.ConfigUtils.getTestDataString;


public class JsonUtils {
    public static boolean fileIsJson(String file) {
        try {
            new JSONParser().parse(file);
        } catch (Exception e) {
            Logger.getInstance().debug("File isn't JSON");
            return false;
        }
        return true;
    }

    public static String deserializationObject(Post jsonPersonModel) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(jsonPersonModel);
        } catch (JsonProcessingException e) {
            Logger.getInstance().debug("Unable to deserialize object",e);
            throw new NotFoundException();
        }
    }

    public static String getExpectedJson(String fileName) {
        String path = getTestDataString("expectedFiles");
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonExpected = (JSONObject) parser.parse(new FileReader(String.format("%s%s.json", path, fileName)));
            return jsonExpected.toString();
        } catch (ParseException | IOException e) {
            Logger.getInstance().debug("Unable to get expected JSON",e);
            throw new RuntimeException(e);
        }
    }
    public static User getJsonUserFromFile(String jsonOnFile) {
        User user = null;
        try {
            user = new ObjectMapper().readValue(jsonOnFile, User.class);
        } catch (IOException e) {
            Logger.getInstance().debug("Cannot get User from file", e);
        }
        return user;
    }

    public static Post getJsonPersonFromFile(String jsonOnFile) {
        Post person = null;
        try {
            person = new ObjectMapper().readValue(jsonOnFile, Post.class);
        } catch (IOException e) {
            Logger.getInstance().debug("Cannot get JSON Person from file", e);
        }
        return person;
    }
}
