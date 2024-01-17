package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get10 extends GoRestBaseUrl {

    /*
        Given https://gorest.co.in/public/v1/users/149679
        When User send GET Request to the URL
        Then Status Code should be 200
        And Response body should be like
        {
         "meta": null,
         "data": {
            "id": 149679,
            "name": "Kali Dhawan",
            "email": "dhawan_kali@rohan-marquardt.info",
            "gender": "female",
            "status": "active"
         }
        }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "users", "second", 149679);

        //Set the expected data
        GoRestTestData goRestTestData = new GoRestTestData();
        Map<String, String> dataMap = goRestTestData.dataMap("Kali Dhawan", "dhawan_kali@rohan-marquardt.info", "female", "active");
        Map<String, Object> expectedData = goRestTestData.expectedDataMethod(null, dataMap);
        System.out.println("Expected Data: " + expectedData);

        //Send the data and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(dataMap.get("name"), ((Map)actualData.get("data")).get("name"));
        assertEquals(dataMap.get("email"), ((Map)actualData.get("data")).get("email"));
        assertEquals(dataMap.get("gender"), ((Map)actualData.get("data")).get("gender"));
        assertEquals(dataMap.get("status"), ((Map)actualData.get("data")).get("status"));
    }

}
