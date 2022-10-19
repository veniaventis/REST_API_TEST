import api.Posts;
import api.Posts99;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.SortUtils;
import utils.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ResApiTestGETPOST {
    private final String URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "/posts";
    private final String POSTS_99 = "/posts/99";

    @Test
    public void checkStatusCodeIsJsonSorted(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOK200());
        List<Posts> posts = given()
                .when()
                .get(POSTS)
                .then().log().all()
                .extract().jsonPath().getList(".", Posts.class);
        List<Integer> ids = posts.stream().map(Posts::getId).toList();
        Assert.assertTrue(SortUtils.isSortedList(ids),"Objects list isn't sorted by Id");
    }
    @Test
    public void checkInformation(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOK200());
        Posts99 posts99 = given()
                .when()
                .get(POSTS_99)
                .then().log().all()
                .extract().as(Posts99.class);
       Assert.assertEquals(posts99.getUserId().intValue(),10, "User ID isn't 10" );
       Assert.assertEquals(posts99.getId().intValue(), 99,"ID isn't 99");
       Assert.assertNotEquals(posts99.getTitle(), "","Title is empty");
       Assert.assertNotEquals(posts99.getBody(), "", "Body is empty");
    }


}
