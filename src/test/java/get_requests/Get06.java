package get_requests;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends RestfulBookerHerOkuAppBaseUrl {

    /*
        Given https://restful-booker.herokuapp.com/booking/21
        When User send a GET request to the URL
        Then HTTP Status Code should be 200
        And Response content type is "application/json"
        And Response body should be like;
        {
        "firstname": "Josh",
        "lastname": "Allen",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "midnight snack"
        }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "booking", "second", 21);

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1. yol:
        response.then().statusCode(200).contentType("application/json").body("firstname", equalTo("Josh"),
                                                                            "lastname", equalTo("Allen"),
                                                                                     "totalprice", equalTo(111),
                                                                                     "depositpaid", equalTo(true),
                                                                                     "bookingdates.checkin", equalTo("2018-01-01"),
                                                                                     "bookingdates.checkout", equalTo("2019-01-01"),
                                                                                     "additionalneeds", equalTo("super bowls"));
        //2.yol:
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Josh", jsonPath.getString("firstname"));
        assertEquals("Allen", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("super bowls", jsonPath.getString("additionalneeds"));

        //3. yol: TestNG Soft Assert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("firstname"), "Josh", "firstname doesn't match");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Allen", "lastname doesn't match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "totalprice doesn't match");
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"), "depositpaid doesn't match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "checkin doesn't match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "checkout doesn't match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "super bowls", "additionalneeds doesn't match");
        softAssert.assertAll();
    }

}
