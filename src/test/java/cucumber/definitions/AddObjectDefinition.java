package cucumber.definitions;

import java.util.List;

import org.testng.Assert;

import com.demo.testng.program.model.ResponseModel.AddObjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class AddObjectDefinition {
    public static Response response;
    public static Integer id;

    @Given("Make sure token in local storage not empty")
    public void assert_token_in_variable() {
        assert LoginDefinition.token != null : "Token null";
        System.out.println(LoginDefinition.token);
    }

    @And("Object ID in the response must be not null")
    public void get_object_id() throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertNotNull(addObjectResponses.get(0).getObjectID(), "Expected ID not to be null");

        //Get Object ID
        AddObjectDefinition.id = addObjectResponses.get(0).getObjectID();
        System.out.println(AddObjectDefinition.id);
    }

    @And("Object name in the response must be {string}")
    public void assert_object_name(String objectName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponses.get(0).getObjectName(), objectName, "Expected name " + objectName + " but got " + addObjectResponses.get(0).getObjectName());
    }

    @And("Object year in the response must be {string}")
    public void assert_object_year(String objectYear) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponses.get(0).getData().getYear(), objectYear, "Expected year " + objectYear + " but got " + addObjectResponses.get(0).getData().getYear());
    }

    @And("Object price in the response must be {double}")
    public void assert_object_price(double objectPrice) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponses.get(0).getData().getPrice(), objectPrice, "Expected price " + objectPrice + addObjectResponses.get(0).getData().getPrice());
    }

    @And("Object cpu model in the response must be {string}")
    public void assert_object_cpu_model(String objectCPUModel) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponses.get(0).getData().getCpuModel(), objectCPUModel, "Expected cpu model " + objectCPUModel + " but got " + addObjectResponses.get(0).getData().getCpuModel());
    }

    @And("Object hard disk size in the response must be {string}")
    public void assert_object_hard_disk_size(String objectHardDiskSize) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponses.get(0).getData().getHardDiskSize(), objectHardDiskSize, "Expected hard disk size " + objectHardDiskSize + " but got " + addObjectResponses.get(0).getData().getHardDiskSize());        
    }

    @And("Object capacity in the response must be {string}")
    public void assert_object_capacity(String objectCapacity) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponses.get(0).getData().getCapacity(), objectCapacity, "Expected capacity " + objectCapacity + " but got " + addObjectResponses.get(0).getData().getCapacity());
    }

    @And("Object screen size in the response must be {string}")
    public void assert_object_screen_size(String objectScreenSize) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponses.get(0).getData().getScreenSize(), objectScreenSize, "Expected capacity " + objectScreenSize + " but got " + addObjectResponses.get(0).getData().getScreenSize());
    }

    @And("Object color in the response must be {string}")
    public void assert_object_color(String objectColor) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddObjectResponse>>() {});
        Assert.assertEquals(addObjectResponses.get(0).getData().getColor(), objectColor, "Expected capacity " + objectColor + " but got " + addObjectResponses.get(0).getData().getColor());
    }
}
