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

public class UpdateObject {
    /*
    * Test Case - 003: Update Object
    * 1. Hit the endpoint update object with valid data
    * 1. Hit the endpoint partially update object with valid data
    * 2. Hit the endpoint get object with id of the previous updated data
    */

    @Test(dependsOnGroups = "newObject", groups = "updateObject", priority = 1)
    public void updateObject() throws JsonProcessingException{
        //Setting variables for request body
        GlobalVar.requestUpdateObject = new RequestUpdateObject();
        GlobalVar.requestUpdateDataDetails = new RequestUpdateObject.DataDetails();

        GlobalVar.requestUpdateObject.setObjectName("Apple MacBook Pro 18");
        GlobalVar.requestUpdateDataDetails.setYear("2028");
        GlobalVar.requestUpdateDataDetails.setPrice(1851.77);
        GlobalVar.requestUpdateDataDetails.setCpuModel("Intel Core i9");
        GlobalVar.requestUpdateDataDetails.setHardDiskSize("3 TB");
        GlobalVar.requestUpdateDataDetails.setCapacity("3 cpu");
        GlobalVar.requestUpdateDataDetails.setScreenSize("22 Inch");
        GlobalVar.requestUpdateDataDetails.setColor("white");

        GlobalVar.requestUpdateObject.setDataDetails(GlobalVar.requestUpdateDataDetails);

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyString = objectMapper.writeValueAsString(GlobalVar.requestUpdateObject);

        //Send PUT request to update object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .body(bodyString)
                            .when()
                            .put(GlobalVar.BASE_URL + "/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + GlobalVar.objectid);
        
        //Print response
        System.out.println(response.asPrettyString());

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("update_object_schema.json"));

        List<UpdateObjectResponse> updateObjectResponse = objectMapper.readValue(response.body().asString(),
                new TypeReference<List<UpdateObjectResponse>>() {
                });

        UpdateObjectResponse object = updateObjectResponse.get(0);
        Assert.assertEquals(object.getObjectID(), GlobalVar.objectid, "Expected id " + GlobalVar.objectid + " but got " + object.getObjectID());
        Assert.assertEquals(object.getObjectName(), GlobalVar.requestUpdateObject.getObjectName(), "Expected name " + GlobalVar.requestUpdateObject.getObjectName() + " but got " + object.getObjectName());
        Assert.assertEquals(object.getData().getYear(), GlobalVar.requestUpdateDataDetails.getYear(), "Expected year " + GlobalVar.requestUpdateDataDetails.getYear() + " but got " + object.getData().getYear());
        Assert.assertEquals(object.getData().getPrice(), GlobalVar.requestUpdateDataDetails.getPrice(), "Expected price " + GlobalVar.requestUpdateDataDetails.getPrice() + object.getData().getPrice());
        Assert.assertEquals(object.getData().getCpuModel(), GlobalVar.requestUpdateDataDetails.getCpuModel(), "Expected cpu model " + GlobalVar.requestUpdateDataDetails.getCpuModel() + " but got " + object.getData().getCpuModel());
        Assert.assertEquals(object.getData().getHardDiskSize(), GlobalVar.requestUpdateDataDetails.getHardDiskSize(), "Expected hard disk size " + GlobalVar.requestUpdateDataDetails.getHardDiskSize() + " but got " + object.getData().getHardDiskSize());
        Assert.assertEquals(object.getData().getCapacity(), GlobalVar.requestUpdateDataDetails.getCapacity(), "Expected capacity " + GlobalVar.requestUpdateDataDetails.getCapacity() + " but got " + object.getData().getCapacity());
        Assert.assertEquals(object.getData().getScreenSize(), GlobalVar.requestUpdateDataDetails.getScreenSize(), "Expected screen size " + GlobalVar.requestUpdateDataDetails.getScreenSize() + " but got " + object.getData().getScreenSize());
        Assert.assertEquals(object.getData().getColor(), GlobalVar.requestUpdateDataDetails.getColor(), "Expected color " + GlobalVar.requestUpdateDataDetails.getColor() + " but got " + object.getData().getColor());

    }

    @Test(dependsOnGroups = "newObject", groups = "updateObject", priority = 2)
    public void partiallyUpdateObject() throws JsonProcessingException{
        //Setting variables for request body
        GlobalVar.requestPartiallyUpdateObject = new RequestPartiallyUpdateObject();

        GlobalVar.requestPartiallyUpdateObject.setObjectName("Apple MacBook Air M4");
        GlobalVar.requestPartiallyUpdateObject.setYear("2030");


        ObjectMapper objectMapper = new ObjectMapper();
        String bodyString = objectMapper.writeValueAsString(GlobalVar.requestPartiallyUpdateObject);

        //Send PATCH request to partially update object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .body(bodyString)
                            .when()
                            .patch(GlobalVar.BASE_URL + "/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/" + GlobalVar.objectid);

        //Print response
        System.out.println(response.asPrettyString());

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("partially_update_object_schema.json"));
        
        PartiallyUpdateObjectResponse partiallyUpdateObjectResponse = objectMapper.readValue(response.body().asString(), PartiallyUpdateObjectResponse.class);

        Assert.assertEquals(partiallyUpdateObjectResponse.getObjectName(), GlobalVar.requestPartiallyUpdateObject.getObjectName(), "Expected name " + GlobalVar.requestPartiallyUpdateObject.getObjectName() + " but got " + partiallyUpdateObjectResponse.getObjectName());
        Assert.assertEquals(partiallyUpdateObjectResponse.getData().getYear(), GlobalVar.requestPartiallyUpdateObject.getYear(), "Expected year " + GlobalVar.requestPartiallyUpdateObject.getYear() + " but got " + partiallyUpdateObjectResponse.getData().getYear());
    }

    @Test(dependsOnGroups = "updateObject")
    public void validateGetUpdatedObject() throws JsonProcessingException{
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
        Assert.assertEquals(getObjectbyIDResponse.getObjectName(), GlobalVar.requestPartiallyUpdateObject.getObjectName(), "Expected name " + GlobalVar.requestPartiallyUpdateObject.getObjectName() + " but got " + getObjectbyIDResponse.getObjectName());
        Assert.assertEquals(getObjectbyIDResponse.getData().getYear(), GlobalVar.requestPartiallyUpdateObject.getYear(), "Expected year " + GlobalVar.requestPartiallyUpdateObject.getYear() + " but got " + getObjectbyIDResponse.getData().getYear());
        Assert.assertEquals(getObjectbyIDResponse.getData().getPrice(), GlobalVar.requestUpdateDataDetails.getPrice(), "Expected price " + GlobalVar.requestUpdateDataDetails.getPrice() + getObjectbyIDResponse.getData().getPrice());
        Assert.assertEquals(getObjectbyIDResponse.getData().getCpuModel(), GlobalVar.requestUpdateDataDetails.getCpuModel(), "Expected cpu model " + GlobalVar.requestUpdateDataDetails.getCpuModel() + " but got " + getObjectbyIDResponse.getData().getCpuModel());
        Assert.assertEquals(getObjectbyIDResponse.getData().getHardDiskSize(), GlobalVar.requestUpdateDataDetails.getHardDiskSize(), "Expected hard disk size " + GlobalVar.requestUpdateDataDetails.getHardDiskSize() + " but got " + getObjectbyIDResponse.getData().getHardDiskSize());
        Assert.assertEquals(getObjectbyIDResponse.getData().getCapacity() + " cpu", GlobalVar.requestUpdateDataDetails.getCapacity(), "Expected capacity " + GlobalVar.requestUpdateDataDetails.getCapacity() + " but got " + getObjectbyIDResponse.getData().getCapacity() + " cpu");
        Assert.assertEquals(getObjectbyIDResponse.getData().getScreenSize() + " Inch", GlobalVar.requestUpdateDataDetails.getScreenSize(), "Expected screen size " + GlobalVar.requestUpdateDataDetails.getScreenSize() + " but got " + getObjectbyIDResponse.getData().getScreenSize() + " Inch");
        Assert.assertEquals(getObjectbyIDResponse.getData().getColor(), GlobalVar.requestUpdateDataDetails.getColor(), "Expected color " + GlobalVar.requestUpdateDataDetails.getColor() + " but got " + getObjectbyIDResponse.getData().getColor());
    }
}