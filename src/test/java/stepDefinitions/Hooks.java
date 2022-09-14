package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        AddPlace addPlace = new AddPlace();
        if(AddPlace.place_id == null){
            addPlace.add_place_payload("Shubham","Austrailia","Hindi");
            addPlace.user_call_the_api_with_post_http_method("addPlaceApi","post");
            addPlace.verifyPlaceIdUsingGetHttpMethod("Shubham","getPlaceApi");
        }
    }
}
