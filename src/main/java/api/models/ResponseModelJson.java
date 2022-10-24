package api.models;

import com.mashape.unirest.http.JsonNode;

public class ResponseModelJson {
    protected int statusCode;
    protected JsonNode body;

    public int getStatusCode() {
        return statusCode;
    }

    public JsonNode getBody() {
        return body;
    }

    public ResponseModelJson(int statusCode, JsonNode body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}