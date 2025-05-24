package e2e;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteObject {
    /*
    * Test Case - 004: Delete Object
    * 1. Hit the endpoint delete object with valid data two times (positive + negative case)
    * 2. Hit the endpoint get single object with id of the previous deleted data
    */

    @Test(dependsOnGroups = "updateObject", groups = "deleteObject", invocationCount = 2, priority = 10)
    public void deleteObject(){
        //Send DELETE request to delete object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .when()
                            .delete(GlobalVar.BASE_URL + "/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + GlobalVar.objectid);

        //Validate response
        if (response.jsonPath().getString("status").equals("deleted")) {
            Assert.assertEquals(response.jsonPath().getString("message"), "Object with id = " + GlobalVar.objectid + ", has been deleted.", "Expected id " + GlobalVar.objectid +" has been deleted but got "+ response.jsonPath().getString("message"));
        } else if (response.jsonPath().getString("status").equals("failed")) {
            Assert.assertEquals(response.jsonPath().getString("message"), "object with id = " + GlobalVar.objectid + " has been deleted or doesn't exist", "Expected failure message for already deleted or non-existent object.");
        } 
        
        //Print response
        System.out.println(response.asPrettyString());
    }

    @Test(dependsOnMethods = "deleteObject", groups = "deleteObject")
    public void validateGetDeletedObject(){
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
        String normalizedBody = response.asPrettyString().replaceAll("\\s", "");
        Assert.assertEquals(normalizedBody, "{}", "Expected empty JSON object but got: " + response.asPrettyString());
    }
}
