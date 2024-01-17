package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import util.ObjectMapperUtils;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get14ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {

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
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(10, "quis eius est sint explicabo", true);
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());
    }

}
