package e2e;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateObject {

    /*
    * Test Case - 003: Update Object
    * 1. Hit the endpoint update object with valid data
    * 1. Hit the endpoint partially update object with valid data
    * 2. Hit the endpoint get object with id of the previous updated data
    */

    @Test(dependsOnGroups = "newObject", groups = "updateObject", priority = 1)
    public void updateObject(){
        //Setting variables for request body
        GlobalVar.objectname = "Apple MacBook Pro 14";
        GlobalVar.year = "2021";
        GlobalVar.price = 1851.77;
        GlobalVar.cpu_model = "Intel Core i6";
        GlobalVar.hard_disk_size = "2 TB";
        GlobalVar.capacity = "3 cpu";
        GlobalVar.screen_size = "15 Inch";
        GlobalVar.color = "grey";

        //Create update object request
        String requestBody = "{\n" + //
                        "\"name\": \"" + GlobalVar.objectname + "\", \n" + //
                        "\"data\": {\n" + //
                        "\"year\": \"" + GlobalVar.year + "\", \n" + //
                        "\"price\": " + GlobalVar.price + ", \n" + //
                        "\"cpu_model\": \"" + GlobalVar.cpu_model + "\", \n" + //
                        "\"hard_disk_size\": \"" + GlobalVar.hard_disk_size + "\", \n" + //
                        "\"capacity\": \"" + GlobalVar.capacity + "\", \n" + //
                        "\"screen_size\": \"" + GlobalVar.screen_size + "\", \n" + //
                        "\"color\": \"" + GlobalVar.color + "\" \n" + //
                        "}" + 
                        "}";
        
        //Send PUT request to update object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .body(requestBody)
                            .when()
                            .put(GlobalVar.BASE_URL + "/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + GlobalVar.objectid);
        
        //Print response
        System.out.println(response.asPrettyString());

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getInt("[0].id"), GlobalVar.objectid, "Expected id " + GlobalVar.objectid + " but got " + response.jsonPath().getInt("[0].id"));
        Assert.assertEquals(response.jsonPath().getString("[0].name"), GlobalVar.objectname, "Expected name " + GlobalVar.objectname + " but got " + response.jsonPath().getString("[0].name"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.year"), GlobalVar.year, "Expected year " + GlobalVar.year + " but got " + response.jsonPath().getString("[0].data.year"));
        Assert.assertEquals(response.jsonPath().getDouble("[0].data.price"), GlobalVar.price, "Expected price " + GlobalVar.price + response.jsonPath().getDouble("[0].data.price"));
        Assert.assertEquals(response.jsonPath().getString("[0].data['CPU model']"), GlobalVar.cpu_model, "Expected cpu model " + GlobalVar.cpu_model + " but got " + response.jsonPath().getString("[0].data['CPU model']"));
        Assert.assertEquals(response.jsonPath().getString("[0].data['Hard disk size']"), GlobalVar.hard_disk_size, "Expected hard disk size " + GlobalVar.hard_disk_size + " but got " + response.jsonPath().getString("[0].data['Hard disk size']"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.capacity"), GlobalVar.capacity, response.jsonPath().getString("[0].data.capacity"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.screen_size"), GlobalVar.screen_size, "Expected screen size " + GlobalVar.screen_size + " but got " + response.jsonPath().getString("[0].data.screen_size"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.color"), GlobalVar.color, "Expected color " + GlobalVar.color + " but got " + response.jsonPath().getString("[0].data.color"));
    }

    @Test(dependsOnGroups = "newObject", groups = "updateObject", priority = 2)
    public void partiallyUpdateObject(){
        //Setting variables for request body
        GlobalVar.objectname = "Apple MacBook Air M1";
        GlobalVar.year = "2030";

        //Create partially update object request body
        String requestBody = "{\n" + //
                        "\"name\": \"" + GlobalVar.objectname + "\", \n" + //
                        "\"year\": \"" + GlobalVar.year + "\" \n" + //
                        "}";

        //Send PATCH request to partially update object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .body(requestBody)
                            .when()
                            .patch(GlobalVar.BASE_URL + "/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/" + GlobalVar.objectid);

         //Print response
         System.out.println(response.asPrettyString());

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("name"), GlobalVar.objectname, "Expected name " + GlobalVar.objectname + " but got " + response.jsonPath().getString("name"));
        Assert.assertEquals(response.jsonPath().getString("data.year"), GlobalVar.year, "Expected year " + GlobalVar.year + " but got " + response.jsonPath().getString("data.year"));
    }

    @Test(dependsOnGroups = "updateObject")
    public void validateGetUpdatedObject(){
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
        Assert.assertEquals(response.jsonPath().getInt("id"), GlobalVar.objectid, "Expected object id " + GlobalVar.objectid + "but got " + response.jsonPath().getInt("id"));
        Assert.assertEquals(response.jsonPath().getString("name"), GlobalVar.objectname, "Expected name " + GlobalVar.objectname + " but got " + response.jsonPath().getString("name"));
        Assert.assertEquals(response.jsonPath().getString("data.year"), GlobalVar.year, "Expected year " + GlobalVar.year + " but got " + response.jsonPath().getString("data.year"));
        Assert.assertEquals(response.jsonPath().getDouble("data.price"), GlobalVar.price, "Expected price " + GlobalVar.price + response.jsonPath().getDouble("data.price"));
        Assert.assertEquals(response.jsonPath().getString("data.cpu_model"), GlobalVar.cpu_model, "Expected cpu model " + GlobalVar.cpu_model + " but got " + response.jsonPath().getString("data.cpu_model"));
        Assert.assertEquals(response.jsonPath().getString("data.hard_disk_size"), GlobalVar.hard_disk_size, "Expected hard disk size " + GlobalVar.hard_disk_size + " but got " + response.jsonPath().getString("data.hard_disk_size"));
        Assert.assertEquals(response.jsonPath().getString("data.capacity") + " cpu", GlobalVar.capacity, "Expected capacity " + GlobalVar.capacity + " but got " + response.jsonPath().getString("data.capacity") + " cpu");
        Assert.assertEquals(response.jsonPath().getString("data.screen_size") + " Inch", GlobalVar.screen_size, "Expected screen size " + GlobalVar.screen_size + " but got " + response.jsonPath().getString("data.screen_size") + " Inch");
        Assert.assertEquals(response.jsonPath().getString("data.color"), GlobalVar.color, "Expected color " + GlobalVar.color + " but got " + response.jsonPath().getString("data.color"));
    }
}