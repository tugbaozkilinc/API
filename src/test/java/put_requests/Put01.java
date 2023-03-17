package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Put01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 	   I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									    "id": 198
									   }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 21);
        expectedData.put("title", "Wash the dishes");
        expectedData.put("completed", false);
        System.out.println("Expected data: " + expectedData);

        //Send the request and get the response
        Response response = given().when().spec(spec).contentType(ContentType.JSON).body(expectedData).put("/{first}/{second}");
        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data: " + actualData);

        //Do assertion
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
    }

    @Test
    public void testWithMethod() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jsonPlaceHolderTestData.expectedDataMethod(21, "Wash the dishes", false);
        System.out.println("Expected data: " + expectedData);

        //Send the request and get the response
        Response response = given().when().spec(spec).contentType(ContentType.JSON).body(expectedData).put("/{first}/{second}");
        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data: " + actualData);

        //Do assertion
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
    }

}
