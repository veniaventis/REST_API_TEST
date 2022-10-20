import api.ApiRequest;
import api.posts.ExpectedPost;
import api.posts.NewPost;
import api.posts.Posts;
import api.users.Users;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.JsonUtil;
import utils.SortUtils;

import java.util.List;


public class ResApiTestGETPOST {
    private final String RANDOM_TEXT = RandomStringUtils.randomAlphanumeric(20);
    private static final int EXPECTED_USER_ID = (int) new JsonSettingsFile("testconfig.json").getValue("/userId");
    private static final int EXPECTED_ID = (int) new JsonSettingsFile("testconfig.json").getValue("/id");
    private static final String EXPECTED_USER_PATH = "src/main/resources/expected_user.json";
    private static final Users EXPECTED_USER = (Users) JsonUtil.jsonToObject(EXPECTED_USER_PATH, Users.class);
    @Test
    public void checkStatusCodeIsJsonSorted(){
        List<Posts> posts = ApiRequest.getPosts();
        List<Integer> ids = posts.stream().map(Posts::getId).toList();
        Assert.assertTrue(SortUtils.isSortedList(ids),"Objects list isn't sorted by Id");
    }
    @Parameters("postNumber")
    @Test
    public void checkInformation(int postNumber){
       Posts postsNumber = ApiRequest.getPosts(postNumber);
       Assert.assertEquals(postsNumber.getUserId().intValue(),EXPECTED_USER_ID, "User ID isn't 10" );
       Assert.assertEquals(postsNumber.getId().intValue(), EXPECTED_ID,"ID isn't 99");
       Assert.assertNotEquals(postsNumber.getTitle(), "","Title is empty");
       Assert.assertNotEquals(postsNumber.getBody(), "", "Body is empty");
    }
    @Parameters("emptyPostNumber")
    @Test
    public void checkEmptyResponse(int emptyPostNumber){
        Posts emptyPosts = ApiRequest.getPosts(emptyPostNumber);
        Assert.assertNull(emptyPosts.getId(),"Response isn't empty");
    }

    @Test
    public void testPost(){
        NewPost newPost = new NewPost(RANDOM_TEXT,RANDOM_TEXT,1);
        ExpectedPost expectedPost = ApiRequest.getExpectedPosts(newPost);
        Assert.assertEquals(RANDOM_TEXT,expectedPost.getBody(),"Body doesn't match");
        Assert.assertEquals(RANDOM_TEXT, expectedPost.getTitle(),"Titles doesn't match");
        Assert.assertEquals(1,expectedPost.getUserId(),"UserId doesn't match");
    }
    @Parameters("userNumber")
    @Test
    public void checkUsers(int userNumber){
        List<Users> users = ApiRequest.getUsers();
        Users actualUser  = users.get(userNumber - 1);
        Assert.assertEquals(actualUser.getClass(),EXPECTED_USER.getClass(),"Objects list doesn't have expected user or empty");
    }
    @Parameters("userNumber")
    @Test
    public void checkUserId5(int userNumber){
        Users user =  ApiRequest.getUsers(userNumber);
        Assert.assertEquals(user.getClass(), EXPECTED_USER.getClass());
    }
}
