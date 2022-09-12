package resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceData {


    private static List<String> types = new ArrayList<>(List.of("shoe park", "shoe"));

    // Request Spec Builder that can be used anywhere where we are sending common chaining across Test


    public static AddPlace addPlace() {

        AddPlace place = new AddPlace();
        Location loc = new Location();
        loc.setLat(48.74131);
        loc.setLng(34.89813842);

        place.setAccuracy(100);
        place.setName("Shubham");
        place.setPhone_number("(+91) 8700195272");
        place.setAddress("149A, Main Street, Jaipur 302012");
        place.setWebsite("http://google.com");
        place.setLanguage("Indian");
        place.setTypes(types);
        place.setLocation(loc);
        return place;
    }
}
