package resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification reqSpec;

    public static ResponseSpecification resSpec;
    public static File file = new File("./logs/Place.txt");
    public static PrintStream printStream;
    public static FileInputStream fileInputStream;
    public static Properties properties;

    public static RequestSpecification reqSpec() throws IOException {
        //System.out.println(getGlobalValue("value"));
        if (reqSpec == null) {
            printStream = new PrintStream(file);
            reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", getGlobalValue("key"))
                    //Added Logs to see the Request and Response in External File
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .setContentType(ContentType.JSON).build();
            return reqSpec;
        }

        return reqSpec;
    }

    public ResponseSpecification resSpec() {
        if (resSpec == null) {
            resSpec = new ResponseSpecBuilder()
                    .expectContentType(ContentType.JSON).build();
            return resSpec;
        }
        return resSpec;
    }

    public static String getGlobalValue(String key) throws IOException {
        properties = new Properties();
        fileInputStream = new FileInputStream("src/main/java/resource/global.properties");

        properties.load(fileInputStream);

        return properties.getProperty(key);


    }

    public String getResponseData(Response response, String key) {
        String resp = response.asString();
        JsonPath path = new JsonPath(resp);
        return path.getString(key);


    }

}

