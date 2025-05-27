package cucumber.definitions;

import java.util.UUID;

import org.testng.Assert;

import com.demo.testng.program.model.ResponseModel.RegisterResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class RegisterDefinition {
    public static String baseUrl;
    public static Response response;
    public static String email;

    @Given("The base url in this feature is {string}")
    public void set_base_url(String baseUrl) {
        RegisterDefinition.baseUrl = baseUrl;
    }

    @When("Send a http {string} request to {string} with body:")
    public void send_request_http(String method, String url, String body) {
        if(url.equals("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/{id}")){
            url = "/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + GetListObjectDefinition.id;
        } else if(url.equals("/webhook/api/register")){
            String randomString = "user_" + UUID.randomUUID().toString().substring(0, 8);
            RegisterDefinition.email = randomString + "@test.com";
            body = body.replace("{email}", RegisterDefinition.email);
        }

        //Send request body
        response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", LoginDefinition.token != null ? "Bearer " + LoginDefinition.token : "")
                .body(body)
                .when()
                .request(method, RegisterDefinition.baseUrl + url);
        
        switch (url) {
            case "/webhook/login":
                LoginDefinition.response = response;
                break;
            case "/webhook/api/objects":
                if (method.equals("POST")){
                    AddObjectDefinition.response = response;
                }
                if (method.equals("GET")){
                    GetListObjectDefinition.response = response;
                }
                break;
            case "/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/566":
                UpdateObjectDefinition.response = response;
                break;
            case "/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/567":
                PartiallyUpdateObjectDefinition.response = response;
                break;
            default:
                if (url.contains("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/")) {
                    DeleteObjectDefinition.response = response;
                }
                break;
        }
        
        // if (url.contains("/login")) {
        //     LoginDefinition.response = response;
        // } else if (method.equals("POST") && url.equals("/webhook/api/objects")) {
        //     AddObjectDefinition.response = response;
        // } else if (url.contains("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/")){
        //     UpdateObjectDefinition.response = response;
        // } else if (url.contains("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/")){
        //     PartiallyUpdateObjectDefinition.response = response;
        // } else if (method.equals("GET") && url.equals("/webhook/api/objects")){
        //     GetListObject.response = response;
        // } else if (url.contains("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/")){
        //     DeleteObjectDefinition.response = response;
        // }
    }

    @Then("The response status must be {int}")
    public void send_request_http(int statusCode) {
        assert response.statusCode() == statusCode : "Error, due to actual status code is " + response.statusCode();
    }

    @And("The response schema should be match with schema {string}")
    public void schema_validation(String schemaPath) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @And("Email in the response must be {string}")
    public void assert_email(String email) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RegisterResponse registerResponse = mapper.readValue(response.asString(), RegisterResponse.class);

        email = email.replace("{email}", RegisterDefinition.email);
        Assert.assertEquals(registerResponse.getEmail(), RegisterDefinition.email, "Expected email " + RegisterDefinition.email + " but got " + registerResponse.getEmail());
    }

    @And("Full name in the response must be {string}")
    public void assert_full_name(String fullName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RegisterResponse registerResponse = mapper.readValue(response.asString(), RegisterResponse.class);
        Assert.assertEquals(registerResponse.getFullName(), fullName, "Expected full name " + fullName + " but got " + registerResponse.getFullName());
    }
    
    @And("Department in the response must be {string}")
    public void assert_department(String department) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RegisterResponse registerResponse = mapper.readValue(response.asString(), RegisterResponse.class);
        Assert.assertEquals(registerResponse.getDepartment(), department, "Expected department " + department + " but got " + registerResponse.getDepartment());
    }

    @And("Phone number in the response must be {string}")
    public void assert_title(String phoneNumber) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RegisterResponse registerResponse = mapper.readValue(response.asString(), RegisterResponse.class);
        Assert.assertEquals(registerResponse.getPhoneNumber(), phoneNumber, "Expected phone number " + phoneNumber + " but got " + registerResponse.getPhoneNumber());
    }
}
