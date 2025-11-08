package herokuapp_smoketest;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RestfulBookerHerOkuAppBookingDatesPojo;
import pojos.RestfulBookerHerOkuAppPojo;
import util.ObjectMapperUtils;
import static herokuapp_smoketest.S01_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S02_Put extends RestfulBookerHerOkuAppBaseUrl {

    /*
    Given https://restful-booker.herokuapp.com/booking/{id}
               {
                "firstname" : "Ali",
                "lastname" : "Can",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
               }
    When send put request
    Then status code should be 200
    Then body should be like
               {
                "firstname": "Ali",
                "lastname": "Can",
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
    public void put01() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        RestfulBookerHerOkuAppBookingDatesPojo bookingDates = new RestfulBookerHerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        RestfulBookerHerOkuAppPojo expectedData = new RestfulBookerHerOkuAppPojo("Ali", "Can", 111, true, bookingDates, "Breakfast");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        RestfulBookerHerOkuAppPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), RestfulBookerHerOkuAppPojo.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
    }

}
