package utils;

import api.models.ResponseModelJSON;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;

public class ApiUtils {
    public static ResponseModelJSON sendGetRequest(String httpBody, String get) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(String.format("%s%s", httpBody, get)).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        assert jsonResponse != null;
        return new ResponseModelJSON(jsonResponse.getStatus(), jsonResponse.getBody());
    }

    public static ResponseModelJSON sendPostRequest(String httpBody, String path, String json) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(String.format("%s%s", httpBody, path))
                    .header("Content-Type", "application/json").body(json).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        assert jsonResponse != null;
        return new ResponseModelJSON(jsonResponse.getStatus(), jsonResponse.getBody());
    }
}
