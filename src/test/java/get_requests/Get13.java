package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get13 extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/247158
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
            }
          }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "users", "second", 247152);

        //Set the expected data
        GoRestDataPojo object = new GoRestDataPojo("Ujjwal Bandopadhyay", "ujjwal_bandopadhyay@mueller-schoen.net", "male", "active");
        GoRestPojo expectedData = new GoRestPojo(null, object);
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(object.getName(), actualData.getData().getName());
        assertEquals(object.getEmail(), actualData.getData().getEmail());
        assertEquals(object.getGender(), actualData.getData().getGender());
        assertEquals(object.getStatus(), actualData.getData().getStatus());
        assertEquals(expectedData.getMeta(), actualData.getMeta());
    }

}
