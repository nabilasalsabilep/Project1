package e2e.pojo;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import e2e.GlobalVar;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.demo.testng.program.model.RequestModel.*;
import com.demo.testng.program.model.ResponseModel.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.module.jsv.JsonSchemaValidator;

public class GetListObject {
    @BeforeSuite
    public void beforeSuite() throws JsonProcessingException{

        //Setting the variable
        String randomString = "user_" + UUID.randomUUID().toString().substring(0, 8);

        GlobalVar.requestRegister = new RequestRegister();
        GlobalVar.requestRegister.setEmail(randomString + "@test.com");
        GlobalVar.requestRegister.setFullName("Tester Pojo");
        GlobalVar.requestRegister.setPassword("Password1!");
        GlobalVar.requestRegister.setDepartment("Executive");
        GlobalVar.requestRegister.setPhoneNumber("0812398899327");

    }

    /*
    * Test Case - 001: Register User
    * 1. Hit the endpoint register with valid data
    * 2. Hit the endpoint login with valid data
    * 3. Hit the endpoint getListAllObject
    */

    @Test
    public void registerUser() throws JsonProcessingException{

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyString = objectMapper.writeValueAsString(GlobalVar.requestRegister);

        //Send POST request to register employee endpoint
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .body(bodyString)
                            .log()
                            .all()
                            .when()
                            .post(GlobalVar.BASE_URL + "/webhook/api/register");
        
        //Print the response
        System.out.println(response.asPrettyString());

        //Validate the response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("register_schema.json"));

        RegisterResponse registerResponse = objectMapper.readValue(response.body().asString(), RegisterResponse.class);

        Assert.assertEquals(registerResponse.getEmail(), GlobalVar.requestRegister.getEmail(), "Expected email " + GlobalVar.requestRegister.getEmail() + " but got " + registerResponse.getEmail());
        Assert.assertEquals(registerResponse.getFullName(), GlobalVar.requestRegister.getFullName(), "Expected full name " + GlobalVar.requestRegister.getFullName() + " but got " + registerResponse.getFullName());
        Assert.assertEquals(registerResponse.getDepartment(), GlobalVar.requestRegister.getDepartment(), "Expected department " + GlobalVar.requestRegister.getDepartment() + " but got " + registerResponse.getDepartment());
        Assert.assertEquals(registerResponse.getPhoneNumber(), GlobalVar.requestRegister.getPhoneNumber(), "Expected phone number " + GlobalVar.requestRegister.getPhoneNumber() + " but got " + registerResponse.getPhoneNumber());
        Assert.assertNotNull(registerResponse.getCreateAt());
        Assert.assertNotNull(registerResponse.getUpdateAt());
    }

    @Test (dependsOnMethods = "registerUser")
    public void loginUser() throws JsonProcessingException{

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyString = objectMapper.writeValueAsString(GlobalVar.requestRegister);
        
        //Send POST request to login endpoint
        Response response = RestAssured
                            .given()
                            .contentType("application/json")
                            .body(bodyString)
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