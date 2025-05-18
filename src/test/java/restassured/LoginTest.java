package restassured;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginTest {
    public static String token;
    public static String email = "testhrvstfo@gmail.com";
    String password = "@dmin123";

    @BeforeSuite
    public void testSuccessfulLogin(){
        /*
         * Define the base URL for the API
         * String base_url = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Create login request
        String requestBody = "{\n" + //
                        " \"email\": \""+ email + "\", \n" + //
                        " \"password\": \"" + password + "\" \n" + //
                        "}";
        
        //Send POST request to login endpoint
        Response response = RestAssured
                            .given()
                            .contentType("application/json")
                            .body(requestBody)
                            .log()
                            .all()
                            .when()
                            .post("/webhook/api/login");

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        //Save token to the global variable token
        token = response.jsonPath().getString("token");

        //Print the token
        System.out.println("Token: " + token);
    }

    @Test
    public void testFailedLogin(){
        /*
         * Define the base URL for the API
         * String base_url = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Define email and password for negative case
        String unregisteredemail = "testhrvst@mail.co";
        password = "test";

        //Create login request
        String requestBody = "{\n" + //
                        " \"email\": \"" + unregisteredemail + "\", \n" + //
                        " \"password\": \"" + password + "\" \n" + //
                        "}";
        
        //Send POST request to login endpoint
        Response response = RestAssured
                            .given()
                            .contentType("application/json")
                            .body(requestBody)
                            .log()
                            .all()
                            .when()
                            .post("/webhook/api/login");

        //Validate response for unregistered account
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("status"), "success", "Expected status success but got " + response.jsonPath().getString("status"));
        Assert.assertEquals(response.jsonPath().getString("message"), "This email= " + unregisteredemail + " has not been registered yet.", "Expected  but got " + response.jsonPath().getString("message"));

        //Print response
        System.out.println("Response: " + response.asPrettyString());
    }
}