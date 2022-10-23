package api.models;

import com.mashape.unirest.http.JsonNode;

public class ResponseModelJSON {
    protected int statusCode;
    protected JsonNode body;

    public int getStatusCode() {
        return statusCode;
    }

    public JsonNode getBody() {
        return body;
    }

    public ResponseModelJSON(int statusCode, JsonNode body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}