package cucumber.definitions;

import org.testng.Assert;

import com.demo.testng.program.model.ResponseModel.DeleteObjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import cucumber.context.TestContext;
import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class DeleteObjectDefinition {
    public static String token;
    public static Response response;
    private final TestContext context;

    public DeleteObjectDefinition(TestContext context) {
        this.context = context;
    }

    @And("The status in the response must be {string}")
    public void assert_status_deleted(String expectedStatus) throws JsonProcessingException {
        DeleteObjectResponse deleteObjectResponse = context.getResponse().as(DeleteObjectResponse.class);

        String actualStatus = deleteObjectResponse.getStatus();
        Assert.assertEquals(actualStatus, expectedStatus, "Expected status to be " + expectedStatus + " but got " + actualStatus);
        System.out.println(actualStatus);
    }

    @And("The message in the response must be {string}")
    public void assert_message_deleted(String expectedMessage) throws JsonProcessingException {
        DeleteObjectResponse deleteObjectResponse = context.getResponse().as(DeleteObjectResponse.class);

        String actualMessage = deleteObjectResponse.getMessage();
        String id = GetListObjectDefinition.id.toString();
        id = id != null ? id : "UNKNOWN";
        expectedMessage = expectedMessage.replace("{id}", id);

        Assert.assertEquals(actualMessage, expectedMessage, "Expected message to be " + expectedMessage + " but got " + actualMessage);
        System.out.println(actualMessage);
    }
}
