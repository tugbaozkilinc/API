package patch_request;

import base_urls.PetstoreSwaggerBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ClassWork01 extends PetstoreSwaggerBaseUrl {

    //1) https://petstore.swagger.io/ dokumanını kullanarak statüsü "available" olan "pet" sayısını bulup 100'den fazla olduğunu assert eden bir otomasyon testi yazınız.
    /*
    Given https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When User sends get request
    Then User asserts that status code is 200
    And User asserts that pet number whose status is available is more than 100
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "pet", "second", "findByStatus").queryParam("status", "available");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        JsonPath jsonPath = response.jsonPath();
        assertEquals(200, response.getStatusCode());
        List<String> idList = jsonPath.getList("id");
        assertTrue(idList.size()>100);
    }

}
