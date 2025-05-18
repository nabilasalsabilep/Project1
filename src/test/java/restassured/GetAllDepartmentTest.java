package restassured;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetAllDepartmentTest {
    
    @Test
    public void testGetAllDepartment(){
        /*
         * Define the base URL for the API
         * String base_url = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        //Send GET request to get all department
        Response response = RestAssured
                            .given()
                            .header(new Header("Authorization", "Bearer " + LoginTest.token))
                            .log()
                            .all()
                            .when()
                            .get("/webhook/api/department");

        //Validate response
        List<Integer> expectedIds = Arrays.asList(
            1, 2, 3, 4);

        List<String> expectedDepartments = Arrays.asList(
            "Technology", "Human Resource", "Finance", "Executive");
            
        Assert.assertEquals(response.jsonPath().getList("id", Integer.class), expectedIds, "Expected id " + expectedIds + "but got " + response.jsonPath().getList("id", Integer.class));
        Assert.assertEquals(response.jsonPath().getList("department", String.class), expectedDepartments, "Expected department " + expectedDepartments + "but got " + response.jsonPath().getString("deparment"));

        //Print response
        System.out.println(response.asPrettyString());
    }
}
