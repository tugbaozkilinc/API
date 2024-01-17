package get_requests;

import base_urls.RegresInApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ClassWork02 extends RegresInApiBaseUrl {

    /*
        Given https://reqres.in/api/users/23
        When User send a GET Request to the url
        Then HTTP Status code should be 404
        And Status Line should be HTTP/1.1 404 Not Found
        And Server is "cloudflare"
        And Response body should be empty
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "users", "second", 23);

        //Set the expected data

        //Send the request get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(2, response.asString().replaceAll("\\s", "").length());
    }

}
