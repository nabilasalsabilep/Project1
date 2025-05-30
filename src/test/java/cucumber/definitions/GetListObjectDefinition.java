package cucumber.definitions;

import com.demo.testng.program.model.ResponseModel.GetListObjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import cucumber.context.TestContext;
import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class GetListObjectDefinition {
    public static String token;
    public static Response response;
    public static Integer id;
    private final TestContext context;

    public GetListObjectDefinition(TestContext context) {
        this.context = context;
    }

    @And("Get first id from the list of objects")
    public void get_first_id() throws JsonProcessingException{
        GetListObjectResponse[] getListObjectResponses = context.getResponse().as(GetListObjectResponse[].class);
        GetListObjectDefinition.id = getListObjectResponses[0].getObjectID();
    }

}
