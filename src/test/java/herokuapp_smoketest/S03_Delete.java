package herokuapp_smoketest;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S01_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static util.AuthenticationHerOkuApp.generateTokenHerOkuApp;

public class S03_Delete extends RestfulBookerHerOkuAppBaseUrl {

    /*
    Given https://restful-booker.herokuapp.com/booking/{id}
    When send delete request
    Then status code should be 201
    And body should be like 'Created'
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given(spec).header("Cookie", "token=" + generateTokenHerOkuApp()).delete("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedData, response.asString());
    }

}
