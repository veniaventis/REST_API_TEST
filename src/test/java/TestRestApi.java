import api.models.Post;
import api.models.User;
import aquality.selenium.browser.AqualityServices;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static api.ApiRequest.*;
import static utils.ConfigUtils.*;
import static utils.JsonUtils.*;
import static utils.RandomUtils.*;

public class TestRestApi {
    @Test
    public void sendGetPostRequests() {
        SoftAssert softAssert = new SoftAssert();
        AqualityServices.getLogger().info("Send a GET request to get all posts (/posts)");
        Post[] persons = getJsonPersons();
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));
        Assert.assertTrue(fileIsJson(RESPONSE_JSON.getBody().toString()), "file the file is not json");
        Assert.assertEquals(Arrays.stream(persons).map(Post::getId).toList(),
                Arrays.stream(persons).map(Post::getId).sorted().toList(), "the tags are not in ascending order");

        AqualityServices.getLogger().info("Send a GET request to receive post /posts/99");
        Post actualPerson = getJsonPerson(getTestDataInt("post"));
        Post expectedPerson = getJsonPersonFromFile(getExpectedJson("ExpectedPost"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));
        softAssert.assertEquals(actualPerson.getUserId(), expectedPerson.getUserId(), "userId don't match");
        softAssert.assertEquals(actualPerson.getId(), expectedPerson.getId(), "id don't match");
        softAssert.assertFalse(actualPerson.getBody().isEmpty(), "body is empty");
        softAssert.assertFalse(actualPerson.getTitle().isEmpty(), "title is empty");

        AqualityServices.getLogger().info("Send a GET request to receive post 150");
        Post jsonPersonModelEmpty = getJsonPerson(getTestDataInt("nullPost"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_NOT_FOUND,
                String.format("status code is not %d status is:%d", HttpStatus.SC_NOT_FOUND, RESPONSE_JSON.getStatusCode()));
        Assert.assertNull(jsonPersonModelEmpty.getBody(), "the request body is not empty");

        AqualityServices.getLogger().info("Send a POST request to create a record /posts.In the request body," +
                " add randomly generated text to the body and title fields.The user Id field must contain 1.");
        Post expectedPersonPost = new Post(getTestDataInt("newUserID"), getTestDataInt("newId"), getRandomString(expectedPerson.getUserId()), getRandomString(expectedPerson.getUserId()));
        Post actualJsonPersonModelPost = postJsonPerson(expectedPersonPost);
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_CREATED,
                String.format("status code is not %d status is:%d", HttpStatus.SC_CREATED, RESPONSE_JSON.getStatusCode()));
        Assert.assertEquals(expectedPersonPost, actualJsonPersonModelPost, " the JSONs doesn't match ");

        AqualityServices.getLogger().info("Send a GET request to get users /users");
        User[] userModels = getJsonUsers();
        User expectedUserModel = getJsonUserFromFile(getExpectedJson("ExpectedUser5"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));
        Assert.assertTrue(fileIsJson(RESPONSE_JSON.getBody().toString()), "file the file is not json");
        softAssert.assertEquals(expectedUserModel, userModels[getTestDataInt("expectedUser") - 1],
                String.format("The user with the number %d does not match the expected user with this number", getTestDataInt("expectedUser")));

        AqualityServices.getLogger().info("Send a GET request to get user 5 /users/5");
        User userModel = getJsonUser(getTestDataInt("expectedUser"));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));
        softAssert.assertEquals(userModels[getTestDataInt("expectedUser") - 1], userModel,
                String.format("the user does not match with the user from the list number %d from the previous request", getTestDataInt("expectedUser")));
        softAssert.assertAll();
    }
}