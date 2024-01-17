package post_requests;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RestfulBookerHerOkuAppBookingDatesPojo;
import pojos.RestfulBookerHerOkuAppPojo;
import pojos.RestfulBookerHerOkuAppResponsePojo;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post04 extends RestfulBookerHerOkuAppBaseUrl {

    /*
        Given
          1)  https://restful-booker.herokuapp.com/booking
          2) {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        },
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
    */

    @Test
    public void test() {
        //Set the URL
        spec.pathParam("first", "booking");

        //Set the expected data
        RestfulBookerHerOkuAppBookingDatesPojo object = new RestfulBookerHerOkuAppBookingDatesPojo("2021-09-21", "2021-12-21");
        RestfulBookerHerOkuAppPojo expectedData = new RestfulBookerHerOkuAppPojo("Ali", "Can", 999, true, object, "Breakfast");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do assertion
        RestfulBookerHerOkuAppResponsePojo actualData = response.as(RestfulBookerHerOkuAppResponsePojo.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(object.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(object.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
    }

}
