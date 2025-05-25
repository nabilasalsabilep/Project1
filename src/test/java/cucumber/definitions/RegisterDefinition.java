package cucumber.definitions;

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

    @Given("The base url in this feature is {string}")
    public void set_base_url(String baseUrl) {
        RegisterDefinition.baseUrl = baseUrl;
    }

    @When("Send a http {string} request to {string} with body:")
    public void send_request_http(String method, String url, String body) {
        //Send request body
        response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", LoginDefinition.token != null ? "Bearer " + LoginDefinition.token : "")
                .body(body)
                .when()
                .request(method, RegisterDefinition.baseUrl + url);
        
        if (url.contains("/login")) {
            LoginDefinition.response = response;
        } else if (url.equals("/webhook/api/objects")) {
            AddObjectDefinition.response = response;
        } else if (url.contains("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/")){
            UpdateObjectDefinition.response = response;
        } else if (url.contains("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/")){
            PartiallyUpdateObjectDefinition.response = response;
        } else if (url.contains("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/")){
            DeleteObjectDefinition.response = response;
        }
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
        Assert.assertEquals(registerResponse.getEmail(), email, "Expected email " + email + " but got " + registerResponse.getEmail());
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
