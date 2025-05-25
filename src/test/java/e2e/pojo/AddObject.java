package e2e.pojo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import e2e.GlobalVar;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.demo.testng.program.model.RequestModel.*;
import com.demo.testng.program.model.ResponseModel.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.module.jsv.JsonSchemaValidator;
import com.fasterxml.jackson.core.type.TypeReference;

public class AddObject {
    /*
    * Test Case - 002: Add Object
    * 1. Hit the endpoint add object with valid data
    * 2. Hit the endpoint get single object with id of the previous added data
    */

    @Test(dependsOnMethods = "loginUser", groups = "newObject")
    public void addObject() throws JsonProcessingException{
        //Setting variables for request body
        GlobalVar.requestAddObject = new RequestAddObject();
        GlobalVar.addDataDetails = new RequestAddObject.DataDetails();

        GlobalVar.requestAddObject.setObjectName("Apple MacBook Pro 16");
        GlobalVar.addDataDetails.setYear("2017");
        GlobalVar.addDataDetails.setPrice(1845.99);;
        GlobalVar.addDataDetails.setCpuModel("Intel Core i6");
        GlobalVar.addDataDetails.setHardDiskSize("256 GB");;
        GlobalVar.addDataDetails.setCapacity("2 cpu");
        GlobalVar.addDataDetails.setScreenSize("15 Inch");
        GlobalVar.addDataDetails.setColor("pink");

        GlobalVar.requestAddObject.setDataDetails(GlobalVar.addDataDetails);;

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyString = objectMapper.writeValueAsString(GlobalVar.requestAddObject);

        //Send POST request to add new object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .body(bodyString)
                            .when()
                            .post(GlobalVar.BASE_URL + "/webhook/api/objects");
        
        //Print response
        System.out.println(response.asPrettyString());

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("add_object_schema.json"));

        List<AddObjectResponse> addObjectResponses = objectMapper.readValue(response.body().asString(),
                new TypeReference<List<AddObjectResponse>>() {
                });

        AddObjectResponse object = addObjectResponses.get(0);
        Assert.assertNotNull(object.getObjectID(), "Expected ID not to be null");
        Assert.assertEquals(object.getObjectName(), GlobalVar.requestAddObject.getObjectName(), "Expected name " + GlobalVar.requestAddObject.getObjectName() + " but got " + object.getObjectName());
        Assert.assertEquals(object.getData().getYear(), GlobalVar.addDataDetails.getYear(), "Expected year " + GlobalVar.addDataDetails.getYear() + " but got " + object.getData().getYear());
        Assert.assertEquals(object.getData().getPrice(), GlobalVar.addDataDetails.getPrice(), "Expected price " + GlobalVar.addDataDetails.getPrice() + object.getData().getPrice());
        Assert.assertEquals(object.getData().getCpuModel(), GlobalVar.addDataDetails.getCpuModel(), "Expected cpu model " + GlobalVar.addDataDetails.getCpuModel() + " but got " + object.getData().getCpuModel());
        Assert.assertEquals(object.getData().getHardDiskSize(), GlobalVar.addDataDetails.getHardDiskSize(), "Expected hard disk size " + GlobalVar.addDataDetails.getHardDiskSize() + " but got " + object.getData().getHardDiskSize());
        Assert.assertEquals(object.getData().getCapacity(), GlobalVar.addDataDetails.getCapacity(), "Expected capacity " + GlobalVar.addDataDetails.getCapacity() + " but got " + object.getData().getCapacity());
        Assert.assertEquals(object.getData().getScreenSize(), GlobalVar.addDataDetails.getScreenSize(), "Expected screen size " + GlobalVar.addDataDetails.getScreenSize() + " but got " + object.getData().getScreenSize());
        Assert.assertEquals(object.getData().getColor(), GlobalVar.addDataDetails.getColor(), "Expected color " + GlobalVar.addDataDetails.getColor() + " but got " + object.getData().getColor());

        //Get object id
        Integer createdid = object.getObjectID();
        GlobalVar.objectid = createdid;
    }

    @Test(dependsOnMethods = "addObject", groups = "newObject")
    public void getObjectbyID() throws JsonProcessingException{
        //Send GET request to get object by id
        Response response = RestAssured
                            .given()
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .when()
                            .get(GlobalVar.BASE_URL + "/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + GlobalVar.objectid);
        
        //Print response
        System.out.println(response.asPrettyString());

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("get_object_by_id_schema.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        GetObjectbyIDResponse getObjectbyIDResponse = objectMapper.readValue(response.body().asString(), GetObjectbyIDResponse.class);

        Assert.assertEquals(getObjectbyIDResponse.getObjectID(), GlobalVar.objectid, "Expected object id " + GlobalVar.objectid + "but got " + getObjectbyIDResponse.getObjectID());
        Assert.assertEquals(getObjectbyIDResponse.getObjectName(), GlobalVar.requestAddObject.getObjectName(), "Expected name " + GlobalVar.requestAddObject.getObjectName() + " but got " + getObjectbyIDResponse.getObjectName());
        Assert.assertEquals(getObjectbyIDResponse.getData().getYear(), GlobalVar.addDataDetails.getYear(), "Expected year " + GlobalVar.addDataDetails.getYear() + " but got " + getObjectbyIDResponse.getData().getYear());
        Assert.assertEquals(getObjectbyIDResponse.getData().getPrice(), GlobalVar.addDataDetails.getPrice(), "Expected price " + GlobalVar.addDataDetails.getPrice() + getObjectbyIDResponse.getData().getPrice());
        Assert.assertEquals(getObjectbyIDResponse.getData().getCpuModel(), GlobalVar.addDataDetails.getCpuModel(), "Expected cpu model " + GlobalVar.addDataDetails.getCpuModel() + " but got " + getObjectbyIDResponse.getData().getCpuModel());
        Assert.assertEquals(getObjectbyIDResponse.getData().getHardDiskSize(), GlobalVar.addDataDetails.getHardDiskSize(), "Expected hard disk size " + GlobalVar.addDataDetails.getHardDiskSize() + " but got " + getObjectbyIDResponse.getData().getHardDiskSize());
        Assert.assertEquals(getObjectbyIDResponse.getData().getCapacity() + " cpu", GlobalVar.addDataDetails.getCapacity(), "Expected capacity " + GlobalVar.addDataDetails.getCapacity() + " but got " + getObjectbyIDResponse.getData().getCapacity() + " cpu");
        Assert.assertEquals(getObjectbyIDResponse.getData().getScreenSize() + " Inch", GlobalVar.addDataDetails.getScreenSize(), "Expected screen size " + GlobalVar.addDataDetails.getScreenSize() + " but got " + getObjectbyIDResponse.getData().getScreenSize() + " Inch");
        Assert.assertEquals(getObjectbyIDResponse.getData().getColor(), GlobalVar.addDataDetails.getColor(), "Expected color " + GlobalVar.addDataDetails.getColor() + " but got " + getObjectbyIDResponse.getData().getColor());
    }
}