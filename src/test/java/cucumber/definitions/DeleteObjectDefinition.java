package cucumber.definitions;

import org.testng.Assert;

import com.demo.testng.program.model.ResponseModel.DeleteObjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class DeleteObjectDefinition {
    public static String token;
    public static Response response;

    @And("The status in the response must be {string}")
    public void assert_status_deleted(String expectedStatus) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DeleteObjectResponse deleteObjectResponse = objectMapper.readValue(response.body().asString(), DeleteObjectResponse.class);

        String actualStatus = deleteObjectResponse.getStatus();
        Assert.assertEquals(actualStatus, expectedStatus, "Expected status to be " + expectedStatus + " but got " + actualStatus);
        System.out.println(actualStatus);
    }

    @And("The message in the response must be {string}")
    public void assert_message_deleted(String expectedMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DeleteObjectResponse deleteObjectResponse = objectMapper.readValue(response.body().asString(), DeleteObjectResponse.class);

        String actualMessage = deleteObjectResponse.getMessage();
        String id = GetListObjectDefinition.id.toString();
        id = id != null ? id : "UNKNOWN";
        expectedMessage = expectedMessage.replace("{id}", id);

        Assert.assertEquals(actualMessage, expectedMessage, "Expected message to be " + expectedMessage + " but got " + actualMessage);
        System.out.println(actualMessage);
    }
}
