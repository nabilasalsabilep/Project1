package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetSingleObjectTest {

    @Test(priority = 6)
    public void testGetSingleObject(){
        /*
         * Define the base URL for the API
         * String base_url = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Send GET request to get single objects
        Response response = RestAssured
                            .given()
                            .header(new Header("Authorization", "Bearer " + LoginTest.token))
                            .log()
                            .all()
                            .when()
                            .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/" + ListOfObjectsByIdsTest.id);

        
        //Validate response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getInt("id"), ListOfObjectsByIdsTest.id, "Expected object id " + ListOfObjectsByIdsTest.id + "but got " + response.jsonPath().getInt("id"));

        //Print response
        System.out.println(response.asPrettyString());
    }
}