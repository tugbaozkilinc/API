package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
        Given https://jsonplaceholder.typicode.com/todos/2
        When I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "todos", "second", 2);

        //Set the expected data
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jsonPlaceHolderTestData.expectedDataMethod(1, "quis ut nam facilis et officia qui", false);
        expectedData.put("id", 2);
        expectedData.put("Via", "2.0 heroku-router");
        expectedData.put("Server", "cloudflare");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class); //actualData, JSON objesinin Java karşılığı olan bir Map<String, Object> haline geliyor.
        //Map<String, Object> actualData = new ObjectMapper().readValue(response.asString(), HashMap.class); //Bu yöntem de aynı işi yapar.
        //Once response.asString() ile response metni alınıyor. Sonra ObjectMapper ile JSON -> Java objesine dönüştürülüyor.
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        //And header "Via" is "1.1 vegur"
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        //And header "Server" is "cloudflare"
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));
    }

}
