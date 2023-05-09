package get_requests;

import base_urls.RegresInApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ClassWork01 extends RegresInApiBaseUrl {

    /*
        Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "users", "second", 3);

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
    }

}
