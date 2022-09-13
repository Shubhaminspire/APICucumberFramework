package resource;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class PlaceData {


    private static final List<String> types = new ArrayList<>(List.of("shoe park", "shoe"));

    // Request Spec Builder that can be used anywhere where we are sending common chaining across Test


    public static AddPlace addPlace(String name, String address, String language) {

        AddPlace place = new AddPlace();
        Location loc = new Location();
        loc.setLat(48.71231);
        loc.setLng(35.89813842);

        place.setAccuracy(145);
        place.setName(name);
        place.setPhone_number("(+91) 8700195273");
        place.setAddress(address);
        place.setWebsite("http://google.com");
        place.setLanguage(language);
        place.setTypes(types);
        place.setLocation(loc);
        return place;
    }

    public static String getPlaceId(String placeId){
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}";
    }
}
