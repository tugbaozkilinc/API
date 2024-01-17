package get_requests;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Get01 extends RestfulBookerHerOkuAppBaseUrl {

    //Given https://restful-booker.herokuapp.com/booking/55
    //When User sends a GET Request to the url
    //Then HTTP Status Code should be 200
    //And Content Type should be application/json
    //And Status Line should be HTTP/1.1 200 OK

    @Test
    public void name() {
        //Note: Endpoint Swagger documentation dan gelir, postman de endpoint in manual olarak calisip calismadigina bakilir, sonra test otomasyona dokulur.
        //Set the URL
        spec.pathParams("first", "booking", "second", 55);

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
    }

}
