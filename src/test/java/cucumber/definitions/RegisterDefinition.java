package cucumber.definitions;

import org.testng.Assert;

import com.demo.testng.program.model.ResponseModel.RegisterResponse;

import cucumber.apiengine.Endpoints;
import cucumber.context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class RegisterDefinition extends Endpoints{
    public static Response response;
    public static String email;
    private final TestContext context;

    public RegisterDefinition(TestContext context) {
        this.context = context;
    }

    @When("Send a http {string} request to {string} with body:")
    public void send_request_http(String method, String url, String body) {
        response = sendRequest(method, url, body);
        context.setResponse(response);
    }

    @Then("The response status must be {int}")
    public void send_request_http(int statusCode) {
        assert response.statusCode() == statusCode : "Error, due to actual status code is " + response.statusCode();
    }

    @And("The response schema should be match with schema {string}")
    public void schema_validation(String schemaPath) {
        context.getResponse().then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @And("Email in the response must be {string}")
    public void assert_email(String email) throws Exception {
        RegisterResponse registerResponse = context.getResponse().as(RegisterResponse.class);

        email = email.replace("{email}", RegisterDefinition.email);
        Assert.assertEquals(registerResponse.getEmail(), RegisterDefinition.email, "Expected email " + RegisterDefinition.email + " but got " + registerResponse.getEmail());
    }

    @And("Full name in the response must be {string}")
    public void assert_full_name(String fullName) throws Exception {
        RegisterResponse registerResponse = context.getResponse().as(RegisterResponse.class);
        Assert.assertEquals(registerResponse.getFullName(), fullName, "Expected full name " + fullName + " but got " + registerResponse.getFullName());
    }
    
    @And("Department in the response must be {string}")
    public void assert_department(String department) throws Exception {
        RegisterResponse registerResponse = context.getResponse().as(RegisterResponse.class);
        Assert.assertEquals(registerResponse.getDepartment(), department, "Expected department " + department + " but got " + registerResponse.getDepartment());
    }

    @And("Phone number in the response must be {string}")
    public void assert_title(String phoneNumber) throws Exception {
        RegisterResponse registerResponse = context.getResponse().as(RegisterResponse.class);
        Assert.assertEquals(registerResponse.getPhoneNumber(), phoneNumber, "Expected phone number " + phoneNumber + " but got " + registerResponse.getPhoneNumber());
    }
}
