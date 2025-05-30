package cucumber.definitions;

import org.testng.Assert;

import cucumber.context.TestContext;
import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class LoginDefinition {
    public static String token;
    public static Response response;
    private final TestContext context;

    public LoginDefinition(TestContext context) {
        this.context = context;
    }

    @And("Save the token from the response to local storage")
    public void save_the_token() {
        String token = context.getResponse().jsonPath().getString("token");
        LoginDefinition.token = token;
        context.set("token", token);
        Assert.assertNotNull(token, "Token should not be null");

        System.out.println("Saved token: " + token);
    }
}
