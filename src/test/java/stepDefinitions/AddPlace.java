package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import resource.PlaceData;
import resource.Resource;
import resource.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddPlace extends Utils {
    public static Response response;

    public static RequestSpecification request;


    public static Resource resource;

    public static String responseKeyValue;
    public static String getResponseName;
    public static String place_id;


    @Given("Add Place Payload {string} {string} {string}")
    public void add_place_payload(String name, String address, String language) throws IOException {

        request = given().log().all().spec(reqSpec()).body(PlaceData.addPlace(name, address, language));
        System.out.println("Hi");
    }

    @When("User call the {string} API with {string} Http Method")
    public void user_call_the_api_with_post_http_method(String resou, String httpMethod) {
        //create object for Resource Enum Class of resource package using valueOf Method of Enum
        resource = Resource.valueOf(resou);

        if (httpMethod.equalsIgnoreCase("Post")) {
            response = request.when().post(resource.getResource());
        } else if (httpMethod.equalsIgnoreCase("Delete")) {
            response = request.when().post(resource.getResource());
        } else if (httpMethod.equalsIgnoreCase("get")) {
            response = request.when().get(resource.getResource());
        }
        response = response.then().log().all()
                .spec(resSpec()).extract().response();
    }

    @Then("User should see the status as success with code {int}")
    public void userShouldSeeTheStatusAsSuccessWithCode(int arg0) {
        assertEquals(response.getStatusCode(), arg0);
    }

    @And("{string} in response is {string}")
    public void inResponseIs(String key, String value) {

        responseKeyValue = getResponseData(response, key);
        assertEquals(responseKeyValue, value);
        System.out.println(key + ":" + responseKeyValue);
    }

    @And("Verify that palce_id created maps to {string} using {string}")
    public void verifyPlaceIdUsingGetHttpMethod(String personName, String reso) throws IOException {
        //ReqSpec Builder
        //  resource = Resource.valueOf(reso);

        place_id = getResponseData(response, "place_id");
        request = given().spec(reqSpec()).queryParam("place_id", place_id);

        user_call_the_api_with_post_http_method(reso, "Get");

        getResponseName = getResponseData(response, "name");
        Assert.assertEquals(getResponseName, personName);


    }


}
