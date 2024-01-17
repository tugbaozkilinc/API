package herokuapp_smoketest;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static herokuapp_smoketest.S01_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S04_Get extends RestfulBookerHerOkuAppBaseUrl {

    /*
    Given https://restful-booker.herokuapp.com/booking/{id}
    When user sends a get request
    Then status code should be 404
    And body should be like "Not Found"
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());
    }

}
