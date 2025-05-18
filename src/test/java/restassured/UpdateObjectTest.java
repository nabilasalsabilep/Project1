package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UpdateObjectTest {
    @Test(priority = 8)
    public void testUpdateObject(){
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Setting variables for request body
        String name = "Apple MacBook Pro 15";
        String year = "2020";
        double price = 1850.99;
        String cpu_model = "Intel Core i5";
        String hard_disk_size = "2 TB";
        String capacity = "3 cpu";
        String screen_size = "15 Inch";
        String color = "grey";

        //Create update object request
        String requestBody = "{\n" + //
                        "\"name\": \"" + name + "\", \n" + //
                        "\"data\": {\n" + //
                        "\"year\": \"" + year + "\", \n" + //
                        "\"price\": " + price + ", \n" + //
                        "\"cpu_model\": \"" + cpu_model + "\", \n" + //
                        "\"hard_disk_size\": \"" + hard_disk_size + "\", \n" + //
                        "\"capacity\": \"" + capacity + "\", \n" + //
                        "\"screen_size\": \"" + screen_size + "\", \n" + //
                        "\"color\": \"" + color + "\" \n" + //
                        "}" + 
                        "}";
        
        //Send PUT request to update object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header(new Header("Authorization", "Bearer " + LoginTest.token))
                            .log()
                            .all()
                            .body(requestBody)
                            .when()
                            .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + ListOfObjectsByIdsTest.id);
        
        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getInt("[0].id"), ListOfObjectsByIdsTest.id, "Expected id " + ListOfObjectsByIdsTest.id + " but got " + response.jsonPath().getInt("[0].id"));
        Assert.assertEquals(response.jsonPath().getString("[0].name"), name, "Expected name " + name + " but got " + response.jsonPath().getString("[0].name"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.year"), year, "Expected year " + year + " but got " + response.jsonPath().getString("[0].data.year"));
        Assert.assertEquals(response.jsonPath().getDouble("[0].data.price"), price, "Expected price " + price + response.jsonPath().getDouble("[0].data.price"));
        Assert.assertEquals(response.jsonPath().getString("[0].data['CPU model']"), cpu_model, "Expected cpu model " + cpu_model + " but got " + response.jsonPath().getString("[0].data['CPU model']"));
        Assert.assertEquals(response.jsonPath().getString("[0].data['Hard disk size']"), hard_disk_size, "Expected hard disk size " + hard_disk_size + " but got " + response.jsonPath().getString("[0].data['Hard disk size']"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.capacity"), capacity, response.jsonPath().getString("[0].data.capacity"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.screen_size"), screen_size, "Expected screen size " + screen_size + " but got " + response.jsonPath().getString("[0].data.screen_size"));
        Assert.assertEquals(response.jsonPath().getString("[0].data.color"), color, "Expected color " + color + " but got " + response.jsonPath().getString("[0].data.color"));

        //Print response
        System.out.println(response.asPrettyString());
    }
}
