package get_requests;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get09 extends RestfulBookerHerOkuAppBaseUrl {

    /*
      Given
          https://restful-booker.herokuapp.com/booking/794
      When
          I send GET Request to the url
      Then
          Response body should be like that;
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
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "booking", "second", 794);

        //Set the expected data
        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "John");
        expectedData.put("lastname", "Smith");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDatesMap);
        expectedData.put("additionalneeds", "Breakfast");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion (Note: Map ile degiskenlerimizi onceden hazirlamamizin sebebi assertion da degisken kullanilmamasi, boyle bir kultur var)
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin")); //assertEquals() methodu String ve Objeyi karşılaştırabiliyor.Bu gecer mesela;
                                                                                                            //Object obj = "abc"; String str = "abc"; assertEquals(str, obj );
        assertEquals(bookingDatesMap.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
    }

    @Test
    public void testWithMethod() {
        //Set the url
        spec.pathParams("first", "booking", "second", 794);

        //Set the expected data
        HerOkuAppTestData herOkuAppTestData = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = herOkuAppTestData.bookingdatesMapMethod("2018-01-01", "2019-01-01");
        Map<String, Object> expectedData = herOkuAppTestData.expectedDataMethod("John", "Smith", 111, true, bookingDatesMap, "Breakfast");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
    }

}
