package post_requests;

import base_urls.PetstoreSwaggerBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PetstoreSwaggerPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class ClassWork03 extends PetstoreSwaggerBaseUrl {

    /*
       "https://petstore.swagger.io/" dökümanını kullanarak 'user' oluşturacak bir otomasyon testi yazınız
        Not: Test Case'i gherkin language ile yazınız.
        Given https://petstore.swagger.io/v2/user
            {
              "id": 1,
              "username": "ece_can",
              "firstName": "Ece",
              "lastName": "Can",
              "email": "ece_can@gmail.com",
              "password": "1234",
              "phone": "12345678",
              "userStatus": 0
            }
         When I send POST Request to the Url
         Then Status code is 200
         And response body is like
            {
               "code": 200,
               "type": "unknown",
               "message": "1"
            }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "user");

        //Set the expected data
        PetstoreSwaggerPojo expectedData = new PetstoreSwaggerPojo(1, "ece_can", "Ece", "Can", "ece_can@gmail.com", "1234", "12345678", 0);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do assertion
        response.then().assertThat().statusCode(200).body("code", equalTo(200), "type", equalTo("unknown"), "message", equalTo("1"));
    }

}
