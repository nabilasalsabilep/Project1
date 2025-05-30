package cucumber.definitions;

import org.testng.Assert;
import com.demo.testng.program.model.ResponseModel.UpdateObjectResponse;

import cucumber.context.TestContext;
import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class UpdateObjectDefinition {
    public static Response response;
    private final TestContext context;

    public UpdateObjectDefinition(TestContext context) {
        this.context = context;
    }

    @And("Updated object id in the response must be {string}")
    public void assert_object_id(String objectID) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);

        objectID = objectID.replace("{id}", GetListObjectDefinition.id.toString());
        Assert.assertEquals(updateObjectResponse[0].getObjectID(), Integer.parseInt(objectID), "Expected name " + Integer.parseInt(objectID) + " but got " + updateObjectResponse[0].getObjectID());
    }

    @And("Updated object name in the response must be {string}")
    public void assert_object_name(String objectName) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);
        Assert.assertEquals(updateObjectResponse[0].getObjectName(), objectName, "Expected name " + objectName + " but got " + updateObjectResponse[0].getObjectName());
    }

    @And("Updated object year in the response must be {string}")
    public void assert_object_year(String objectYear) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);
        Assert.assertEquals(updateObjectResponse[0].getData().getYear(), objectYear, "Expected year " + objectYear + " but got " + updateObjectResponse[0].getData().getYear());
    }

    @And("Updated object price in the response must be {double}")
    public void assert_object_price(double objectPrice) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);
        Assert.assertEquals(updateObjectResponse[0].getData().getPrice(), objectPrice, "Expected price " + objectPrice + updateObjectResponse[0].getData().getPrice());
    }

    @And("Updated object cpu model in the response must be {string}")
    public void assert_object_cpu_model(String objectCPUModel) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);
        Assert.assertEquals(updateObjectResponse[0].getData().getCpuModel(), objectCPUModel, "Expected cpu model " + objectCPUModel + " but got " + updateObjectResponse[0].getData().getCpuModel());
    }

    @And("Updated object hard disk size in the response must be {string}")
    public void assert_object_hard_disk_size(String objectHardDiskSize) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);
        Assert.assertEquals(updateObjectResponse[0].getData().getHardDiskSize(), objectHardDiskSize, "Expected hard disk size " + objectHardDiskSize + " but got " + updateObjectResponse[0].getData().getHardDiskSize());        
    }

    @And("Updated object capacity in the response must be {string}")
    public void assert_object_capacity(String objectCapacity) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);
        Assert.assertEquals(updateObjectResponse[0].getData().getCapacity(), objectCapacity, "Expected capacity " + objectCapacity + " but got " + updateObjectResponse[0].getData().getCapacity());
    }

    @And("Updated object screen size in the response must be {string}")
    public void assert_object_screen_size(String objectScreenSize) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);
        Assert.assertEquals(updateObjectResponse[0].getData().getScreenSize(), objectScreenSize, "Expected capacity " + objectScreenSize + " but got " + updateObjectResponse[0].getData().getScreenSize());
    }

    @And("Updated object color in the response must be {string}")
    public void assert_object_color(String objectColor) throws Exception {
        UpdateObjectResponse[] updateObjectResponse = context.getResponse().as(UpdateObjectResponse[].class);
        Assert.assertEquals(updateObjectResponse[0].getData().getColor(), objectColor, "Expected capacity " + objectColor + " but got " + updateObjectResponse[0].getData().getColor());
    }
}