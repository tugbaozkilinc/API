package post_requests;

import base_urls.RegresInApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresInApiPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class ClassWork02 extends RegresInApiBaseUrl {

    /*
       Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "users");

        //Set the expected data
        RegresInApiPojo expectedData = new RegresInApiPojo("morpheus", "leader");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do assertion
        RegresInApiPojo actualData = response.as(RegresInApiPojo.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getJob(), actualData.getJob());
        assertEquals(expectedData.getName(), actualData.getName());
    }

}
