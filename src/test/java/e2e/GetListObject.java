package e2e;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetListObject {
    @BeforeSuite
    public void beforeSuite(){

        //Setting the variable
        String randomString = "user_" + UUID.randomUUID().toString().substring(0, 8);
        GlobalVar.email = randomString + "@test.com";
        GlobalVar.password = "Password1!";
        GlobalVar.fullName = "Tester A";
        GlobalVar.department = "Executive";
        GlobalVar.phonenumber = "0812398899327";
    }

    /*
    * Test Case - 001: Register User
    * 1. Hit the endpoint register with valid data
    * 2. Hit the endpoint login with valid data
    * 3. Hit the endpoint getListAllObject
    */

    @Test
    public void registerUser(){
        //Create register request
        String requestBody = "{\n" + //
                        "  \"email\": \"" + GlobalVar.email + "\",\n" + //
                        "  \"full_name\": \"" + GlobalVar.fullName + "\",\n" + //
                        "  \"password\": \"" + GlobalVar.password + "\",\n" + //
                        "  \"department\": \"" + GlobalVar.department + "\",\n" + //
                        "  \"phone_number\": \"" + GlobalVar.phonenumber + "\"\n" + //
                        "}";

        //Send POST request to register employee endpoint
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .body(requestBody)
                            .log()
                            .all()
                            .when()
                            .post(GlobalVar.BASE_URL + "/webhook/api/register");
        
        //Print the response
        System.out.println(response.asPrettyString());

        //Validate the response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("email"), GlobalVar.email, "Expected email " + GlobalVar.email + " but got " + response.jsonPath().getString("email"));
        Assert.assertEquals(response.jsonPath().getString("full_name"), GlobalVar.fullName, "Expected full name " + GlobalVar.fullName + " but got " + response.jsonPath().getString("full_name"));
        Assert.assertEquals(response.jsonPath().getString("department"), GlobalVar.department, "Expected department " + GlobalVar.department + " but got " + response.jsonPath().getString("department"));
        Assert.assertEquals(response.jsonPath().getString("phone_number"), GlobalVar.phonenumber, "Expected title " + GlobalVar.phonenumber + " but got " + response.jsonPath().getString("phone_number"));
        Assert.assertNotNull(response.jsonPath().getString("create_at"));
        Assert.assertNotNull(response.jsonPath().getString("update_at"));
    }

    @Test (dependsOnMethods = "registerUser")
    public void loginUser(){
        //Create login request
        String requestBody = "{\n" + //
                        " \"email\": \""+ GlobalVar.email + "\", \n" + //
                        " \"password\": \"" + GlobalVar.password + "\" \n" + //
                        "}";
        
        //Send POST request to login endpoint
        Response response = RestAssured
                            .given()
                            .contentType("application/json")
                            .body(requestBody)
                            .log()
                            .all()
                            .when()
                            .post(GlobalVar.BASE_URL + "/webhook/api/login");

        //Print the token
        System.out.println("Token: " + GlobalVar.token);

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        //Save token to the global variable token
        GlobalVar.token = response.jsonPath().getString("token");

    }

    @Test(dependsOnMethods = "loginUser")
    public void getListAllObject(){
        //Send GET request to getListAllObject endpoint
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .when()
                            .get(GlobalVar.BASE_URL + "/webhook/api/objects");

         //Print response
         System.out.println(response.asPrettyString());

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertNotNull(response.jsonPath().getInt("[0].id"), "Expected response is not null but got empty response");
    }
}
