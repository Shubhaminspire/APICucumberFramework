package resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.logging.Logger;

public class Utils {
    public static String Url = "https://rahulshettyacademy.com";
    RequestSpecification reqSpec;

    ResponseSpecification resSpec;
    File file = new File("./logs/Place.txt");
    PrintStream printStream;

    public RequestSpecification reqSpec() throws FileNotFoundException {
      printStream  = new PrintStream(file);
        reqSpec = new RequestSpecBuilder().setBaseUri(Url)
                .addQueryParam("key", "qaclick123")
                //Added Logs to see the Request and Response in External File
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .setContentType(ContentType.JSON).build();
        return reqSpec;
    }

    public ResponseSpecification resSpec() {
        resSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON).build();
        return resSpec;
    }

}

