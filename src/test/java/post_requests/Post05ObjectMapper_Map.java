package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post05ObjectMapper_Map extends JsonPlaceHolderBaseUrl {

    /*
        Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
    */

    @Test
    public void test() throws IOException {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jsonPlaceHolderTestData.expectedDataMethod(55, "Tidy your room", false);
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }

}
