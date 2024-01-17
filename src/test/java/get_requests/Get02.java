package get_requests;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class Get02 extends RestfulBookerHerOkuAppBaseUrl {

    /*
        Given https://restful-booker.herokuapp.com/booking/0
        When User send a GET Request to the url
        Then HTTP Status code should be 404
        And Status Line should be HTTP/1.1 404 Not Found
        And Response body contains "Not Found"
        And Response body does not contain "TechProEd"
        And Server is "Cowboy"
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "booking", "second", 0);

        //Set the expected data

        //Send the request get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("TechProEd"));
        assertEquals("Cowboy", response.getHeader("Server"));
    }

    //Exception alirsan bunu kullan.
    /*
      String endPoint = "https://restful-booker.herokuapp.com/booking/0";
      try {
             Response response = given(spec).get("/{first}/{second}");
      } catch (Exception e) {
             assert e.getMessage().contains("404");
             assert e.getMessage().contains("Not Found");
             assertFalse(e.getMessage().contains("TechProEd"));
      }
    */

}
