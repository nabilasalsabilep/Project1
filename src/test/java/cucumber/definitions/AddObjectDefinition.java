package cucumber.definitions;

import org.testng.Assert;

import com.demo.testng.program.model.ResponseModel.AddObjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import cucumber.context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class AddObjectDefinition {
    public static Response response;
    public static Integer id;
    private final TestContext context;

    public AddObjectDefinition(TestContext context) {
        this.context = context;
    }

    @Given("Make sure token in local storage not empty")
    public void assert_token_in_variable() {
        String tokenFromContext = context.get("token", String.class);
        Assert.assertNotNull(tokenFromContext, "Token in context null");
        System.out.println("Token from context: " + tokenFromContext);
    }

    @And("Object ID in the response must be not null")
    public void get_object_id() throws JsonProcessingException{
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertNotNull(addObjectResponses[0].getObjectID(), "Expected ID not to be null");

        //Get Object ID
        AddObjectDefinition.id = addObjectResponses[0].getObjectID();
        System.out.println(AddObjectDefinition.id);
    }

    @And("Object name in the response must be {string}")
    public void assert_object_name(String objectName) throws Exception {
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertEquals(addObjectResponses[0].getObjectName(), objectName, "Expected name " + objectName + " but got " + addObjectResponses[0].getObjectName());
    }

    @And("Object year in the response must be {string}")
    public void assert_object_year(String objectYear) throws Exception {
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertEquals(addObjectResponses[0].getData().getYear(), objectYear, "Expected year " + objectYear + " but got " + addObjectResponses[0].getData().getYear());
    }

    @And("Object price in the response must be {double}")
    public void assert_object_price(double objectPrice) throws Exception {
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertEquals(addObjectResponses[0].getData().getPrice(), objectPrice, "Expected price " + objectPrice + addObjectResponses[0].getData().getPrice());
    }

    @And("Object cpu model in the response must be {string}")
    public void assert_object_cpu_model(String objectCPUModel) throws Exception {
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertEquals(addObjectResponses[0].getData().getCpuModel(), objectCPUModel, "Expected cpu model " + objectCPUModel + " but got " + addObjectResponses[0].getData().getCpuModel());
    }

    @And("Object hard disk size in the response must be {string}")
    public void assert_object_hard_disk_size(String objectHardDiskSize) throws Exception {
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertEquals(addObjectResponses[0].getData().getHardDiskSize(), objectHardDiskSize, "Expected hard disk size " + objectHardDiskSize + " but got " + addObjectResponses[0].getData().getHardDiskSize());        
    }

    @And("Object capacity in the response must be {string}")
    public void assert_object_capacity(String objectCapacity) throws Exception {
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertEquals(addObjectResponses[0].getData().getCapacity(), objectCapacity, "Expected capacity " + objectCapacity + " but got " + addObjectResponses[0].getData().getCapacity());
    }

    @And("Object screen size in the response must be {string}")
    public void assert_object_screen_size(String objectScreenSize) throws Exception {
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertEquals(addObjectResponses[0].getData().getScreenSize(), objectScreenSize, "Expected capacity " + objectScreenSize + " but got " + addObjectResponses[0].getData().getScreenSize());
    }

    @And("Object color in the response must be {string}")
    public void assert_object_color(String objectColor) throws Exception {
        AddObjectResponse[] addObjectResponses = context.getResponse().as(AddObjectResponse[].class);
        Assert.assertEquals(addObjectResponses[0].getData().getColor(), objectColor, "Expected capacity " + objectColor + " but got " + addObjectResponses[0].getData().getColor());
    }
}
