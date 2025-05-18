package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetListAllObjectTest {

    @Test(priority = 4)
    public void testGetAllObjects(){
        /*
         * Define the base URL for the API
         * String base_url = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Send GET request to get all objects
        Response response = RestAssured
                            .given()
                            .header(new Header("Authorization", "Bearer " + LoginTest.token))
                            .log()
                            .all()
                            .when()
                            .get("/webhook/api/objects");
        
        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        //Print response
        System.out.println(response.asPrettyString());
    }
}