package post_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import util.ObjectMapperUtils;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {

    /*
      Given
         URL: https://dummy.restapiexample.com/api/v1/create
      And
      Request body:
         {
            "employee_name": "Tom Hanks",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image",
            "id": 4891
         }
      When
          HTTP Request Method: POST Request
      Then
          Status code is 200
      And
          Response body should be like the following
            {
                "status": "success",
                "data": {
                    "employee_name": "Tom Hanks",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image",
                    "id": 4891
                },
                "message": "Successfully! Record has been added."
            }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "create");

        //Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Tom Hanks", 111111, 23, "Perfect image");
        System.out.println("Expected Data: " + expectedData);
        DummyRestApiPojo expectedBody = new DummyRestApiPojo("success", expectedData, "Successfully! Record has been added.");

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do assertion
        DummyRestApiPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiPojo.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedBody.getStatus(), actualData.getStatus());
        assertEquals(expectedBody.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());
    }

}
