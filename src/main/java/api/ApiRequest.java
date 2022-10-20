package api;

import api.posts.ExpectedPost;
import api.posts.Posts;
import api.users.Users;
import utils.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiRequest {
    private static final String URL = "https://jsonplaceholder.typicode.com";
    private static final String POSTS = "/posts/";
    private static final String USERS = "/users";

    public static List<Posts> getPosts(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOK200());
        return given()
                .when()
                .get(POSTS)
                .then().log().all()
                .extract().jsonPath().getList(".", Posts.class);
    }

    public static Posts getPosts(int postNumber){
        if (postNumber == 150){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpec404());}
        else {Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOK200());}

        return  given()
                .when()
                .get(POSTS + postNumber)
                .then().log().all()
                .extract().as(Posts.class);
    }


    public static ExpectedPost getExpectedPosts(Object post){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec201());
        return  given()
                .body(post)
                .when()
                .post(POSTS)
                .then().log().all()
                .extract().as(ExpectedPost.class);
    }

    public static List<Users> getUsers(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        return given()
                .when()
                .get(USERS)
                .then().log().all()
                .extract().body().jsonPath().getList(".",Users.class);
    }
}
