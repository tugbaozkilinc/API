package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {

    /*
     Given
         https://jsonplaceholder.typicode.com/todos/198
     When
         I send DELETE Request to the Url
     Then
         Status code is 200
     And
         Response body is { }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        Map<String, String> expectedData = new HashMap<>();
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).delete("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, String> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        //1. yol:
        assertEquals(expectedData, actualData);
        //2. yol:
        assertTrue(actualData.isEmpty());
        //3. yol:
        assertEquals(0, actualData.size());
        //4. yol:
        String actual = response.asString();
        assertEquals(2, actual.length());
    }

}
