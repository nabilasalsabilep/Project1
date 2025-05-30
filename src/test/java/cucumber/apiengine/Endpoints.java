package cucumber.apiengine;

import java.util.UUID;

import cucumber.definitions.AddObjectDefinition;
import cucumber.definitions.DeleteObjectDefinition;
import cucumber.definitions.GetListObjectDefinition;
import cucumber.definitions.LoginDefinition;
import cucumber.definitions.PartiallyUpdateObjectDefinition;
import cucumber.definitions.RegisterDefinition;
import cucumber.definitions.UpdateObjectDefinition;
import cucumber.helper.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Endpoints {
    public static String email;

    public Endpoints() {
        // Set the base URI for the API
        String baseUrl = ConfigManager.getBaseUrl();
        RestAssured.baseURI = baseUrl;
    }

    public Response sendRequest(String method, String url, String body) {
        url = resolveDynamicUrl(url);
        body = prepareRequestBody(url, body);

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", getAuthorizationHeader())
                .body(body)
                .when()
                .request(method, RestAssured.baseURI + url);

        handleResponse(url, method, response);
        return response;
    }

    private String resolveDynamicUrl(String url) {
        switch (url) {
            case "/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/{id}":
                return "/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/" + GetListObjectDefinition.id;
            case "/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/{id}":
                return "/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/" + GetListObjectDefinition.id;
            case "/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/{id}":
                return "/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/" + GetListObjectDefinition.id;
            default:
                return url;
        }
    }

    private String prepareRequestBody(String url, String body) {
        if (url.equals("/webhook/api/register")) {
            String randomString = "user_" + UUID.randomUUID().toString().substring(0, 8);
            RegisterDefinition.email = randomString + "@test.com";
            return body.replace("{email}", RegisterDefinition.email);
        }
        return body;
    }

    private String getAuthorizationHeader() {
        return LoginDefinition.token != null ? "Bearer " + LoginDefinition.token : "";
    }

    private void handleResponse(String url, String method, Response response) {
        if (url.equals("/webhook/login")) {
            LoginDefinition.response = response;
        } else if (url.equals("/webhook/api/objects")) {
            if (method.equals("POST")) {
                AddObjectDefinition.response = response;
            } else if (method.equals("GET")) {
                GetListObjectDefinition.response = response;
            }
        } else if (url.contains("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/")) {
            DeleteObjectDefinition.response = response;
        } else if (url.contains("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/")) {
            PartiallyUpdateObjectDefinition.response = response;
        } else if (url.contains("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/")) {
            UpdateObjectDefinition.response = response;
        }
    }
}
