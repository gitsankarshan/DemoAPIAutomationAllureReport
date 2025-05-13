package testbase;

import io.restassured.RestAssured;
import utils.ConfigReader;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;

public class BaseTest {
	
	 private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	@BeforeClass
	public void setupClass() {
		
		ConfigReader.loadProperties();
		// Load properties from config file
	    // This will run before any tests in the class
	    RestAssured.baseURI = ConfigReader.getProperty("base.uri"); // Use ConfigReader to dynamically set the base URI
	    if (RestAssured.baseURI == null || RestAssured.baseURI.isEmpty()) {
	    	logger.error("Base URI is missing or empty in config.properties!");
	        throw new IllegalStateException("Base URI is not configured properly");
	    }
	    logger.info("Base URI set to: " + RestAssured.baseURI);
	}


    @BeforeMethod
    public void setup() {
        // Setup before each test method
        // E.g., reset global variables, clear cookies, authentication tokens, etc.
        System.out.println("Setting up test execution...");
        logger.info("Setting up test execution...");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture test results
        if (ITestResult.FAILURE == result.getStatus()) {
        	 logger.error("Test {} FAILED - {}", result.getName(), result.getThrowable());
            // Capture failure details in Allure
            Allure.addAttachment("Failure Details", result.getThrowable().toString());
            logger.info("Test {} PASSED", result.getName());
        }

        // Optional: Clean up actions after each test method
        // E.g., clear any session data, logout, etc.
        System.out.println("Test completed: " + result.getName());
    }

    @AfterClass
    public void tearDownClass() {
        // Clean up after all tests in the class
        // Reset configurations or system properties if needed
        RestAssured.reset();
        logger.info("Test class cleanup complete.");
       // System.out.println("Class-level cleanup done.");
    }

    // Optional helper methods to support tests
    public String getToken() {
        // Placeholder: Implement token retrieval logic here (e.g., from a login endpoint)
        return "your-authentication-token";
    }

    // Custom assertion or logging methods can also go here
}

//public class BaseTest {
//    @BeforeClass
//    public void setUp() {
//        ConfigReader.loadProperties();
//        RestAssured.baseURI = ConfigReader.getProperty("base.uri");
//    }
//}
