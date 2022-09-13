package stepDefinitions;

import io.cucumber.java.en.Given;
import resource.PlaceData;
import resource.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DeletePlace extends Utils {


    @Given("Delete API Payload")
    public void deleteAPIPayload() throws IOException {

        AddPlace.request = given().spec(reqSpec()).body(PlaceData.getPlaceId(AddPlace.place_id));
    }
}
