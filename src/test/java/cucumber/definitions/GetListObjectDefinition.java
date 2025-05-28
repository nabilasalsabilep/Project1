package cucumber.definitions;

import java.util.List;

import com.demo.testng.program.model.ResponseModel.GetListObjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class GetListObjectDefinition {
    public static String token;
    public static Response response;
    public static Integer id;

    @And("Get first id from the list of objects")
    public void get_first_id() throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        List<GetListObjectResponse> getListObjectResponses = objectMapper.readValue(response.body().asString(), new TypeReference<List<GetListObjectResponse>>() {});
        
        GetListObjectDefinition.id = getListObjectResponses.get(0).getObjectID();
    }

}
