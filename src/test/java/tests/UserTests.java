package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestPayloads.UserPayload;
import testbase.BaseTest;
import utils.APIUtils;
import utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;

public class UserTests extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTests.class);

    @Test
    public void testBaseUri() {
        logger.info("Base URI: {}", ConfigReader.getProperty("base.uri"));
        System.out.println("Base URI: " + ConfigReader.getProperty("base.uri"));
    }

    @Test
    @Description("POST - Create user with valid data")
    public void createUser() {
        logger.info("Starting 'createUser' test...");

        UserPayload user = new UserPayload("John Doe", "Software Engineer");

        logger.info("Sending POST request to create a user with name: {}, job: {}", user.getName(), user.getJob());
        
        // Make a POST request to create a new user
        Response response = APIUtils.postRequest("/api/users", user);
        

        logger.info("Received response with status code: {}", response.getStatusCode());
        
        // Validate response
        response.then().statusCode(201)
                .body("name", equalTo("John Doe"))
                .body("job", equalTo("Software Engineer"));

        // Optionally: Assert response as well
        Assert.assertEquals(response.getStatusCode(), 201, "Expected HTTP status code is 201");
        
        logger.info("'createUser' test completed successfully.");
    }

    @Test
    @Description("GET - Retrieve user details by ID")
    public void getUser() {
        logger.info("Starting 'getUser' test...");

        // Get user with ID 2
        Response response = APIUtils.getRequest("/api/users/2");

        logger.info("Received response with status code: {}", response.getStatusCode());

        // Validate response
        response.then().statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
        
        logger.info("'getUser' test completed successfully.");
    }

    @Test
    @Description("PUT - Update user details")
    public void updateUser() {
        logger.info("Starting 'updateUser' test...");

        UserPayload user = new UserPayload("John Doe", "Senior Software Engineer");

        logger.info("Sending PUT request to update user with name: {}, job: {}", user.getName(), user.getJob());
        
        // Make a PUT request to update user details
        Response response = APIUtils.putRequest("/api/users/2", user);

        logger.info("Received response with status code: {}", response.getStatusCode());

        // Validate response
        response.then().statusCode(200)
                .body("name", equalTo("John Doe"))
                .body("job", equalTo("Senior Software Engineer"));

        logger.info("'updateUser' test completed successfully.");
    }

    @Test
    @Description("DELETE - Delete user")
    public void deleteUser() {
        logger.info("Starting 'deleteUser' test...");

        // Delete user with ID 2
        Response response = APIUtils.deleteRequest("/api/users/2");

        logger.info("Received response with status code: {}", response.getStatusCode());

        // Validate response
        response.then().statusCode(204);

        logger.info("'deleteUser' test completed successfully.");
    }
}
