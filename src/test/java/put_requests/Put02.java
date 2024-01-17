package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import util.ObjectMapperUtils;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "update", "second", 21);

        //Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        System.out.println("Expected Data: " + expectedData);
        DummyRestApiPojo expectedBody = new DummyRestApiPojo("success", expectedData, "Successfully! Record has been updated.");

        //Send the request and get the response
        Response response = given(spec).contentType(ContentType.JSON).body(expectedData).put("/{first}/{second}");
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
