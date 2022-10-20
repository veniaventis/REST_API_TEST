package api.posts;

public class NewPost {
    private String title;
    private String body;
    private Integer userId;

    public NewPost(){}

    public NewPost(String title, String body, Integer userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Integer getUserId() {
        return userId;
    }
}
