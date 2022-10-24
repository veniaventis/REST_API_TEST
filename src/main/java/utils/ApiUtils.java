package utils;

import api.models.ResponseModelJson;
import aquality.selenium.core.logging.Logger;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;

public class ApiUtils {
    public static ResponseModelJson sendGetRequest(String httpBody, String get) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(String.format("%s%s", httpBody, get)).asJson();
        } catch (UnirestException e) {
            Logger.getInstance().debug("Get request doesn't send ", e );
        }
        return new ResponseModelJson(jsonResponse.getStatus(), jsonResponse.getBody());
    }

    public static ResponseModelJson sendPostRequest(String httpBody, String path, String json) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(String.format("%s%s", httpBody, path))
                    .header("Content-Type", "application/json").body(json).asJson();
        } catch (UnirestException e) {
            Logger.getInstance().debug("New Post doesn't send", e );
        }
        return new ResponseModelJson(jsonResponse.getStatus(), jsonResponse.getBody());
    }
}
