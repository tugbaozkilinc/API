package get_requests;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ClassWork04 extends RestfulBookerHerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=John&lastname=Smith
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "John" and lastname is "Smith"
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "booking").queryParams("firstname", "John", "lastname", "Smith");

        //Set the expected data

        //Send the request get the response
        Response response = given(spec).get("/{first}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200);
        Assert.assertTrue(response.asString().contains("bookingid"));
    }

}
