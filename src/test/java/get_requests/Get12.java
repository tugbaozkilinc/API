package get_requests;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RestfulBookerHerOkuAppBookingDatesPojo;
import pojos.RestfulBookerHerOkuAppPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get12 extends RestfulBookerHerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/392
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like:
 		               {
                        "firstname": "Guoqiang",
                        "lastname": "Combs",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "Breakfast"
                      }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "booking", "second", 535);

        //Set the expected data
        RestfulBookerHerOkuAppBookingDatesPojo object = new RestfulBookerHerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        RestfulBookerHerOkuAppPojo expectedData = new RestfulBookerHerOkuAppPojo("John", "Smith", 111, true, object, "Breakfast");

        //Send the request and get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        RestfulBookerHerOkuAppPojo actualData = response.as(RestfulBookerHerOkuAppPojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(object.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(object.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
    }

}
