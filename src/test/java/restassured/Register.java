package restassured;

import org.testng.annotations.Test;

import java.util.UUID;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;


public class Register {
    String full_name = "User Test";
    String password = "test@123";
    String department = "Executive";
    String phone_number = "08123982401";

    @Test(priority = 2)
    public void testSuccessfulRegister(){
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Setting variable for request body
        String randomEmail = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";

        //Create register request
        String requestBody = "{\n" + //
                        "  \"email\": \"" + randomEmail + "\",\n" + //
                        "  \"full_name\": \"" + full_name + "\",\n" + //
                        "  \"password\": \"" + password + "\",\n" + //
                        "  \"department\": \"" + department + "\",\n" + //
                        "  \"phone_number\": \"" + phone_number + "\"\n" + //
                        "}";

        //Send POST request to register new account
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header(new Header("Authorization", "Bearer " + LoginTest.token))
                            .log()
                            .all()
                            .body(requestBody)
                            .when()
                            .post("/webhook/api/register");

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("email"), randomEmail, "Expected email " + randomEmail + " but got " + response.jsonPath().getString("email"));
        Assert.assertEquals(response.jsonPath().getString("full_name"), full_name, "Expected full name " + full_name + " but got " + response.jsonPath().getString("full_name"));
        Assert.assertEquals(response.jsonPath().getString("department"), department, "Expected department " + department + " but got " + response.jsonPath().getString("department"));
        Assert.assertEquals(response.jsonPath().getString("phone_number"), phone_number, "Expected phone number " + phone_number + " but got " + response.jsonPath().getString("phone_number"));
        Assert.assertNotNull(response.jsonPath().getString("create_at"));
        Assert.assertNotNull(response.jsonPath().getString("update_at"));

        //Print response
        System.out.println(response.asPrettyString());
    }

    @Test(priority = 3)
    public void testRegisterWithExistingEmail(){
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Create register request
        String requestBody = "{\n" + //
                        "  \"email\": \"" + LoginTest.email + "\",\n" +
                        "  \"full_name\": \"" + full_name + "\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "  \"department\": \"" + department + "\",\n" +
                        "  \"phone_number\": \"" + phone_number + "\"\n" +
                        "}";

        //Send POST request to register new account
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header(new Header("Authorization", "Bearer " + LoginTest.token))
                            .log()
                            .all()
                            .body(requestBody)
                            .when()
                            .post("/webhook/api/register");

         //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("result"), "failed", "Expected result failed but got " + response.jsonPath().getString("result"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Email = " + LoginTest.email + " already registered", "Expected message Email = "+ LoginTest.email + " already registered but got " + response.jsonPath().getString("message"));

        //Print response
        System.out.println(response.asPrettyString());
    }
}
