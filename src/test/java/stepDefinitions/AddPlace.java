package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.internal.http.HTTPBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import resource.PlaceData;
import resource.Resource;
import resource.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddPlace extends Utils {
    Response response;
    Response getResponse;

    RequestSpecification request;
    RequestSpecification getrequest;


    Resource resource;

    String responseKeyValue;
    public static String getResponseName = null;


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
            response = request.when().delete(resource.getResource());
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
    public void inResponseIs(String arg0, String arg1) {

        responseKeyValue = getResponseData(response, arg0);
        assertEquals(responseKeyValue, arg1);
        System.out.println(arg0 + ":" + responseKeyValue);
    }

    @And("Verify that palce_id created maps to {string} using {string}")
    public void verifyPlaceIdUsingGetHttpMethod(String personName, String reso) throws IOException {
        //ReqSpec Builder
        //  resource = Resource.valueOf(reso);

        String place_id = getResponseData(response, "place_id");
        request = given().spec(reqSpec()).queryParam("place_id", place_id);

        user_call_the_api_with_post_http_method(reso, "Get");

        getResponseName = getResponseData(response, "name");
        Assert.assertEquals(getResponseName, personName);


    }
}
