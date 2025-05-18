package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class PartiallyUpdateObjectTest {
    @Test(priority = 9)
    public void testPartiallyUpdateObject(){
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Setting variables for request body
        String name = "Apple MacBook Air M1";
        String year = "2030";

        //Create partially update object request body
        String requestBody = "{\n" + //
                        "\"name\": \"" + name + "\", \n" + //
                        "\"year\": \"" + year + "\" \n" + //
                        "}";

        //Send PATCH request to partially update object
        Response response = RestAssured
                            .given()
                            .header("Content-Type", "application/json")
                            .header(new Header("Authorization", "Bearer " + LoginTest.token))
                            .log()
                            .all()
                            .body(requestBody)
                            .when()
                            .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/" + ListOfObjectsByIdsTest.id);

        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("name"), name, "Expected name " + name + " but got " + response.jsonPath().getString("name"));
        Assert.assertEquals(response.jsonPath().getString("data.year"), year, "Expected year " + year + " but got " + response.jsonPath().getString("data.year"));
        
        //Print response
        System.out.println(response.asPrettyString());
    }
}
