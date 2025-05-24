package e2e;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddObject {

    /*
    * Test Case - 002: Add Object
    * 1. Hit the endpoint add object with valid data
    * 2. Hit the endpoint get single object with id of the previous added data
    */

    @Test(dependsOnMethods = "loginUser", groups = "newObject")
    public void addObject(){
        //Setting variables for request body
        GlobalVar.objectname = "Apple MacBook Pro 16";
        GlobalVar.year = "2020";
        GlobalVar.price = 1845.99;
        GlobalVar.cpu_model = "Intel Core i8";
        GlobalVar.hard_disk_size = "1 TB";
        GlobalVar.capacity = "2 cpu";
        GlobalVar.screen_size = "16 Inch";
        GlobalVar.color = "red";

        //Create request
        String requestBody = "{\n" + //
                        "\"name\": \"" + GlobalVar.objectname + "\", \n" + //
                        "\"data\": {\n" + //
                        "\"year\": \"" + GlobalVar.year + "\", \n" + //
                        "\"price\": " + GlobalVar.price + ",\n" + //
                        "\"cpu_model\": \"" + GlobalVar.cpu_model + "\", \n" + //
                        "\"hard_disk_size\": \"" + GlobalVar.hard_disk_size + "\", \n" + //
                        "\"capacity\": \"" + GlobalVar.capacity + "\", \n" + //
                        "\"screen_size\": \"" + GlobalVar.screen_size + "\", \n" + //
                        "\"color\": \"" + GlobalVar.color + "\" \n" + //
                        "} \n" +
                        "}";

        //Send POST request to add new object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + GlobalVar.token)
                            .log()
                            .all()
                            .body(requestBody)
                            .when()
                            .post(GlobalVar.BASE_URL + "/webhook/api/objects");
        
        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertNotNull(response.jsonPath().getInt("[0].id"));
        Assert.assertEquals(response.jsonPath().getString("[0].name"), GlobalVar.objectname, "Expected name " + GlobalVar.objectname + " but got " + response.jsonPath().getString("[0].name"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.year"), GlobalVar.year, "Expected year " + GlobalVar.year + " but got " + response.jsonPath().getString("[0].data.year"));
        Assert.assertEquals(response.jsonPath().getDouble("[0].data.price"), GlobalVar.price, "Expected price " + GlobalVar.price + response.jsonPath().getDouble("[0].data.price"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.cpu_model"), GlobalVar.cpu_model, "Expected cpu model " + GlobalVar.cpu_model + " but got " + response.jsonPath().getString("[0].data.cpu_model"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.hard_disk_size"), GlobalVar.hard_disk_size, "Expected hard disk size " + GlobalVar.hard_disk_size + " but got " + response.jsonPath().getString("[0].data.hard_disk_size"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.capacity"), GlobalVar.capacity, response.jsonPath().getString("[0].data.capacity"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.screen_size"), GlobalVar.screen_size, "Expected screen size " + GlobalVar.screen_size + " but got " + response.jsonPath().getString("[0].data.screen_size"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.color"), GlobalVar.color, "Expected color " + GlobalVar.color + " but got " + response.jsonPath().getString("[0].data.color"));

        //Get object id
        Integer createdid = response.jsonPath().getInt("[0].id");
        GlobalVar.objectid = createdid;

        //Print response
        System.out.println(response.asPrettyString());
    }

    @Test(dependsOnMethods = "addObject", groups = "newObject")
    public void getObjectbyID(){
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
