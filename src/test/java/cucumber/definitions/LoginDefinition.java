package cucumber.definitions;

import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class LoginDefinition {
    public static String token;
    public static Response response;

    @And("Save the token from the response to local storage")
    public void save_the_token() {
        LoginDefinition.token = RegisterDefinition.response.jsonPath().getString("token");
        System.out.println(LoginDefinition.token);
    }
}
