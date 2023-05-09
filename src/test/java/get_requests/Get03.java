package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "todos", "second", 23);

        //Set the expected data

        //Send the request get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion (Oncelikle status code dogrulanir)
        response.then().statusCode(200).contentType(ContentType.JSON).body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                "completed", equalTo(false), "userId", equalTo(2));
        //Tek body() method'u icerisinde coklu assertion yaparak soft assertion olusturabilirsin. Bu sekilde fail durumunda body() icinde Java calismayi durdurmaz.
        //Coklu body() method'u ile assertion yapildiginda fail durumunda Java bir sonraki body() method'u oncesinde calismayi durdurur.
    }

}
