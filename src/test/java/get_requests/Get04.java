package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    I send a GET request to the Url
	    And
	        Accept type is “application/json”
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userId
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data

        //Send the request get the response
        Response response = given(spec).get("/{first}"); //Accept type is “application/json” /accept(ContentType.JSON) dogrulama islemini burda yapiyorduk(test case de boyle yani when
                                                              //kısmında(action kisminda) yapmamiz istendigi icin) artik RequestSpecification da yapiyoruz, code tekrarından kurtulmak adina
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200).contentType(ContentType.JSON).body("id", hasSize(200), "title", hasItem("quis eius est sint explicabo"),
                                                                                                 "userId", hasItems(2, 7, 9));
    }

    //hasSize() -> size() methodu gibi eleman sayısını assert eder.
    //hasItem() -> contains() methodu gibi objenin içerilip içerilmediğini assert eder.
    //hasItems() -> containsAll() methodu gibi birden fazla objenin içerilip içerilmediğini assert eder.

}
