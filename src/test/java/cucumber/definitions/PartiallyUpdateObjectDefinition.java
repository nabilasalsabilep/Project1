package cucumber.definitions;

import org.testng.Assert;

import com.demo.testng.program.model.ResponseModel.PartiallyUpdateObjectResponse;

import cucumber.context.TestContext;
import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class PartiallyUpdateObjectDefinition {
    public static Response response;
    private final TestContext context;

    public PartiallyUpdateObjectDefinition(TestContext context) {
        this.context = context;
    }

    @And("Partially updated object id in the response must be {string}")
    public void assert_partially_update_object_id(String objectID) throws Exception {
        PartiallyUpdateObjectResponse partiallyUpdateObjectResponse = context.getResponse().as(PartiallyUpdateObjectResponse.class);

        objectID = objectID.replace("{id}", GetListObjectDefinition.id.toString());
        Assert.assertEquals(partiallyUpdateObjectResponse.getObjectID(), Integer.parseInt(objectID), "Expected id " + Integer.parseInt(objectID) + " but got " + partiallyUpdateObjectResponse.getObjectID());
    }


    @And("Partially updated object name in the response must be {string}")
    public void assert_partially_update_object_name(String objectName) throws Exception {
        PartiallyUpdateObjectResponse partiallyUpdateObjectResponse = context.getResponse().as(PartiallyUpdateObjectResponse.class);
        Assert.assertEquals(partiallyUpdateObjectResponse.getObjectName(), objectName, "Expected name " + objectName + " but got " + partiallyUpdateObjectResponse.getObjectName());
    }

    @And("Partially updated object year in the response must be {string}")
    public void assert_partially_update_object_year(String objectYear) throws Exception {
        PartiallyUpdateObjectResponse partiallyUpdateObjectResponse = context.getResponse().as(PartiallyUpdateObjectResponse.class);
        Assert.assertEquals(partiallyUpdateObjectResponse.getData().getYear(), objectYear, "Expected year " + objectYear + " but got " + partiallyUpdateObjectResponse.getData().getYear());
    }
}
