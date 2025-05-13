package utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static io.restassured.RestAssured.*;

public class APIUtils {

    private static final Logger logger = LoggerFactory.getLogger(APIUtils.class);
    private static final String API_KEY = ConfigReader.getProperty("api.key");
    
    private static final String HEADER_NAME = "x-api-key";

    public static Response getRequest(String endpoint) {
        logger.info("Sending GET request to endpoint: {}", endpoint);
        Response response = given()
                .baseUri(ConfigReader.getProperty("base.uri"))
                .header(HEADER_NAME, API_KEY)
                .when()
                .get(endpoint);
        logger.info("Received response with status code: {}", response.getStatusCode());
        return response;
    }

    public static Response postRequest(String endpoint, Object body) {
        logger.info("Sending POST request to endpoint: {} with body: {}", endpoint, body);
        Response response = given()
                .baseUri(ConfigReader.getProperty("base.uri"))
                .header(HEADER_NAME, API_KEY)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);
        logger.info("Received response with status code: {}", response.getStatusCode());
        return response;
    }

    public static Response putRequest(String endpoint, Object body) {
        logger.info("Sending PUT request to endpoint: {} with body: {}", endpoint, body);
        Response response = given()
                .baseUri(ConfigReader.getProperty("base.uri"))
                .header(HEADER_NAME, API_KEY)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint);
        logger.info("Received response with status code: {}", response.getStatusCode());
        return response;
    }

    public static Response deleteRequest(String endpoint) {
        logger.info("Sending DELETE request to endpoint: {}", endpoint);
        Response response = given()
                .baseUri(ConfigReader.getProperty("base.uri"))
                .header(HEADER_NAME, API_KEY)
                .when()
                .delete(endpoint);
        logger.info("Received response with status code: {}", response.getStatusCode());
        return response;
    }
}
