package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class DeleteObjectTest {
    @Test(invocationCount = 2, priority = 10)
    public void testDeleteObject(){
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Send DELETE request to delete object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header(new Header("Authorization", "Bearer " + LoginTest.token))
                            .log()
                            .all()
                            .when()
                            .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + ListOfObjectsByIdsTest.id);

        //Validate response
        if (response.jsonPath().getString("status").equals("deleted")) {
            Assert.assertEquals(response.jsonPath().getString("message"), "Object with id = " + ListOfObjectsByIdsTest.id + ", has been deleted.", "Expected id " + ListOfObjectsByIdsTest.id +" has been deleted but got "+ response.jsonPath().getString("message"));
        } else if (response.jsonPath().getString("status").equals("failed")) {
            Assert.assertEquals(response.jsonPath().getString("message"), "object with id = " + ListOfObjectsByIdsTest.id + " has been deleted or doesn't exist", "Expected failure message for already deleted or non-existent object.");
        } 
        
        //Print response
        System.out.println(response.asPrettyString());
    }
}
