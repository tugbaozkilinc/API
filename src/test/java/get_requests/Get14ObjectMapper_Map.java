package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import util.ObjectMapperUtils;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get14ObjectMapper_Map extends JsonPlaceHolderBaseUrl {

    /*
        Given https://jsonplaceholder.typicode.com/todos/198
        When I send GET Request to the URL
	 	Then Status code is 200
	 	And response body is like    {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        String json = JsonPlaceHolderTestData.expectedDataInString(10, "quis eius est sint explicabo", true);
        Map<String, Object> expectedData = ObjectMapperUtils.convertJsonToJava(json, HashMap.class);
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }

}
