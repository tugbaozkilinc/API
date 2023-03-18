package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Patch01 extends JsonPlaceHolderBaseUrl {

    /*
       Given
	       1) https://jsonplaceholder.typicode.com/todos/198
	       2) {
                 "title": "Wash the dishes"
               }
        When
	 	   I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									    }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jsonPlaceHolderTestData.expectedDataMethod(null, "Wash the dishes", null);
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).patch("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(10, actualData.get("userId"));
        assertEquals(true, actualData.get("completed")); //obje oldugu icin simply assertion onermiyor
        assertTrue((boolean) actualData.get("completed"));
    }

}
