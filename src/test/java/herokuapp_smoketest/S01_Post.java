package herokuapp_smoketest;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RestfulBookerHerOkuAppBookingDatesPojo;
import pojos.RestfulBookerHerOkuAppPojo;
import pojos.RestfulBookerHerOkuAppResponsePojo;
import util.ObjectMapperUtils;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S01_Post extends RestfulBookerHerOkuAppBaseUrl {

    //"https://restful-booker.herokuapp.com/apidoc/index.html" dökümanını kullanarak;
    //Bir booking oluşturan, o booking'i güncelleyen ve sonra silen ve bu adımları doğrulayan pozitif ve negatif testler içeren bir otomasyon testi yazınız.

    /*
    Given https://restful-booker.herokuapp.com/booking
               {
                "firstname" : "Jim",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
               }
    When Send post request
    Then status code is 200
    And body should be like
               {
                "bookingid": 4527,
                "booking": {
                    "firstname": "Jim",
                    "lastname": "Brown",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }
               }
    */

    static int bookingId;
    @Test
    public void post01() {
        //Set the url
        spec.pathParam("first", "booking");

        //Set the expected data
        RestfulBookerHerOkuAppBookingDatesPojo bookingDates = new RestfulBookerHerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        RestfulBookerHerOkuAppPojo expectedData = new RestfulBookerHerOkuAppPojo("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do assertion
        RestfulBookerHerOkuAppResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), RestfulBookerHerOkuAppResponsePojo.class);
        System.out.println("Actual Data: " + actualData);
        bookingId = actualData.getBookingid();
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
    }

}
