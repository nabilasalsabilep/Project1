package cucumber.definitions;

import org.testng.Assert;

import com.demo.testng.program.model.ResponseModel.PartiallyUpdateObjectResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class PartiallyUpdateObjectDefinition {
    public static Response response;

    @And("Partially updated object id in the response must be {int}")
    public void assert_partially_update_object_id(Integer objectID) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PartiallyUpdateObjectResponse partiallyUpdateObjectResponse = objectMapper.readValue(response.body().asString(), PartiallyUpdateObjectResponse.class);
        Assert.assertEquals(partiallyUpdateObjectResponse.getObjectID(), objectID, "Expected id " + objectID + " but got " + partiallyUpdateObjectResponse.getObjectID());
    }


    @And("Partially updated object name in the response must be {string}")
    public void assert_partially_update_object_name(String objectName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PartiallyUpdateObjectResponse partiallyUpdateObjectResponse = objectMapper.readValue(response.body().asString(), PartiallyUpdateObjectResponse.class);
        Assert.assertEquals(partiallyUpdateObjectResponse.getObjectName(), objectName, "Expected name " + objectName + " but got " + partiallyUpdateObjectResponse.getObjectName());
    }

    @And("Partially updated object year in the response must be {string}")
    public void assert_partially_update_object_year(String objectYear) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PartiallyUpdateObjectResponse partiallyUpdateObjectResponse = objectMapper.readValue(response.body().asString(), PartiallyUpdateObjectResponse.class);
        Assert.assertEquals(partiallyUpdateObjectResponse.getData().getYear(), objectYear, "Expected year " + objectYear + " but got " + partiallyUpdateObjectResponse.getData().getYear());
    }
}
