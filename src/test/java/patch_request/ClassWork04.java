package patch_request;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.RestfulBookerHerOkuAppBookingDatesPojo;
import pojos.RestfulBookerHerOkuAppPojo;
import util.ObjectMapperUtils;
import static io.restassured.RestAssured.given;

public class ClassWork04 extends RestfulBookerHerOkuAppBaseUrl {

    /*
        Given https://restful-booker.herokuapp.com/booking/22
        When I send GET Request to the URL
		Then Status code is 200
                  {
                    "firstname": "John",
                    "lastname": "Smith",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                  }
                (TestNG Soft assertion yapınız)
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "booking", "second", 22);

        //Set the expected data
        RestfulBookerHerOkuAppBookingDatesPojo object = new RestfulBookerHerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        RestfulBookerHerOkuAppPojo expectedData = new RestfulBookerHerOkuAppPojo("John", "Smith", 111, true, object, "Breakfast");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        RestfulBookerHerOkuAppPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), RestfulBookerHerOkuAppPojo.class);
        System.out.println("Actual Data: " + actualData);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        softAssert.assertEquals(actualData.getLastname(), expectedData.getLastname());
        softAssert.assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        softAssert.assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(), object.getCheckin());
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(), object.getCheckout());
        softAssert.assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());
        softAssert.assertAll();
    }

}
