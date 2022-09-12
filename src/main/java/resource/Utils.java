package resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.logging.Logger;

public class Utils {
    public static String Url = "https://rahulshettyacademy.com";
    RequestSpecification reqSpec;

    ResponseSpecification resSpec;

    public RequestSpecification reqSpec() {
        reqSpec = new RequestSpecBuilder().setBaseUri(Url)
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();
        return reqSpec;
    }

    public ResponseSpecification resSpec() {
        resSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON).build();
        return resSpec;
    }

}

