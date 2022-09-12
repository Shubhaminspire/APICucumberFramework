package resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    RequestSpecification reqSpec;

    ResponseSpecification resSpec;
    File file = new File("./logs/Place.txt");
    PrintStream printStream;
    FileInputStream fileInputStream;
    Properties properties;

    public RequestSpecification reqSpec() throws IOException {
        //System.out.println(getGlobalValue("value"));
      printStream  = new PrintStream(file);
        reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
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

    public String getGlobalValue(String key) throws IOException {
        properties = new Properties();
        fileInputStream = new FileInputStream("src/main/java/resource/global.properties");

        properties.load(fileInputStream);

        return properties.getProperty(key);





    }

}

