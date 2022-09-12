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
import resource.PlaceData;
import resource.Resource;
import resource.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddPlace extends Utils {
    Response response;
    JsonPath path;
    RequestSpecification request;

    Resource resource;


    @Given("Add Place Payload {string} {string} {string}")
    public void add_place_payload(String name, String address, String language) throws IOException {

        request = given().log().all().spec(reqSpec()).body(PlaceData.addPlace(name, address, language));
        System.out.println("Hi");
    }

    @When("User call the {string} API with POST Http Method")
    public void user_call_the_api_with_post_http_method(String resou) {
      resource = Resource.valueOf(resou);
        response = request.when().post(resource.getResource()).then().log().all()
                .spec(resSpec()).extract().response();
    }

    @Then("User should see the status as success with code {int}")
    public void userShouldSeeTheStatusAsSuccessWithCode(int arg0) {
        assertEquals(response.getStatusCode(), arg0);
    }

    @And("{string} in response is {string}")
    public void inResponseIs(String arg0, String arg1) {
        path = new JsonPath(response.asString());
        assertEquals(path.getString(arg0), arg1);
        System.out.println(arg0 + ":" + path.getString(arg0));
    }
}