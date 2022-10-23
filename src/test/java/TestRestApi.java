import api.StatusCodes;
import api.models.Post;
import api.models.User;
import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static api.ApiRequest.*;
import static utils.ConfigUtils.*;
import static utils.JsonUtils.*;
import static utils.ModelsUtils.*;


public class TestRestApi {

    @Test
    public void sendGetPostRequests (){
        SoftAssert softAssert = new SoftAssert();
        AqualityServices.getLogger().info("Send a GET request to get all posts (/posts)");
        Post[] persons = getJsonPersons();
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.OK.getSTATUS(),
                 String.format("status code is not %d status is:%d",StatusCodes.OK.getSTATUS(),RESPONSE_JSON.getStatusCode()));
        Assert.assertTrue(fileIsJson(RESPONSE_JSON.getBody().toString()), "file the file is not json");
        Assert.assertTrue(dataIsSortedById(persons), "the tags are not in ascending order");

        AqualityServices.getLogger().info("Send a GET request to receive post /posts/99");
        Post actualPerson = getJsonPerson(getConfigInt("post"));
        Post expectedPerson = getJsonPersonFromFile(getExpectedJson("Post99"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.OK.getSTATUS(),
                String.format("status code is not %d status is:%d",StatusCodes.OK.getSTATUS(),RESPONSE_JSON.getStatusCode()));
        softAssert.assertEquals(actualPerson.getUserId(),expectedPerson.getUserId(),"userId don't match");
        softAssert.assertEquals(actualPerson.getId(),expectedPerson.getId(), "id don't match");
        softAssert.assertFalse(actualPerson.getBody().isEmpty(), "body is empty");
        softAssert.assertFalse(actualPerson.getTitle().isEmpty(), "title is empty");

        AqualityServices.getLogger().info("Send a GET request to receive post 150");
        Post jsonPersonModelEmpty = getJsonPerson(getConfigInt("nullPost"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.EMPTY.getSTATUS(),
                String.format("status code is not %d status is:%d",StatusCodes.OK.getSTATUS(),RESPONSE_JSON.getStatusCode()));
        Assert.assertTrue(getEmptyBodyPerson(jsonPersonModelEmpty), "the request body is not empty");

        AqualityServices.getLogger().info("Send a POST request to create a record /posts.In the request body," +
                " add randomly generated text to the body and title fields.The user Id field must contain 1.");
        Post expectedPersonPost = new Post(getConfigInt("newUserID"),getConfigInt("newId"), getRandomString(), getRandomString());
        Post actualJsonPersonModelPost = postJsonPerson(expectedPersonPost);
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.POST.getSTATUS(),
                String.format("status code is not %d status is:%d",StatusCodes.OK.getSTATUS(),RESPONSE_JSON.getStatusCode()));
        softAssert.assertEquals(expectedPersonPost.getTitle(), actualJsonPersonModelPost.getTitle(), "the title does not match");
        softAssert.assertEquals(expectedPersonPost.getBody(), actualJsonPersonModelPost.getBody(), "the body does not match");
        softAssert.assertEquals(expectedPersonPost.getUserId(), actualJsonPersonModelPost.getUserId(), "the UserID does not match");
        softAssert.assertEquals(expectedPersonPost.getId(), actualJsonPersonModelPost.getId(), "the id does not match");

        AqualityServices.getLogger().info("Send a GET request to get users /users");
        User[] userModels = getJsonUsers();
        User expectedUserModel = getJsonUserFromFile(getExpectedJson("ExpectedUser5"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.OK.getSTATUS(),
                String.format("status code is not %d status is:%d",StatusCodes.OK.getSTATUS(),RESPONSE_JSON.getStatusCode()));
        Assert.assertTrue(fileIsJson(RESPONSE_JSON.getBody().toString()), "file the file is not json");
        softAssert.assertEquals(expectedUserModel, userModels[getConfigInt("expectedUser")-1],
                "The user with the number "+getConfigInt("expectedUser")+" does not match the expected user with this number");

        AqualityServices.getLogger().info("Send a GET request to get user 5 /users/5");
        User userModel = getJsonUser(getConfigInt("expectedUser"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), StatusCodes.OK.getSTATUS(),
                String.format("status code is not %d status is:%d",StatusCodes.OK.getSTATUS(),RESPONSE_JSON.getStatusCode()));
        softAssert.assertEquals(userModels[getConfigInt("expectedUser")-1], userModel,
                "the user does not match with the user from the list number "+getConfigInt("expectedUser")+" from the previous request");
        softAssert.assertAll();
    }
}