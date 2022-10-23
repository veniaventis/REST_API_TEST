package utils;

import api.models.Post;
import api.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;

public class ModelsUtils {

    public static boolean getEmptyBodyPerson(Post person){
        try {
            person.getBody().isEmpty();
            return false;
        }catch (NullPointerException e){
            return true;
        }
    }

    public static boolean dataIsSortedById(Post[] persons){
        for(int i = 0; i < persons.length - 1; i++){
            if(persons[i].getId() >= persons[i+1].getId()) {
                return false;
            }
        }
        return true;
    }

    public static User getJsonUserFromFile(String jsonOnFile){
        User user = null;
        try {
            user = new ObjectMapper().readValue(jsonOnFile, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Post getJsonPersonFromFile(String jsonOnFile){
        Post person = null;
        try {
            person = new ObjectMapper().readValue(jsonOnFile, Post.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    public static String getRandomString() {
        return RandomStringUtils.randomAlphanumeric(50);
    }
}