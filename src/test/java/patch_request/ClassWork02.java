package patch_request;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class ClassWork02 extends AutomationExerciseBaseUrl {

    /*
    Given https://automationexercise.com/api/productsList
	When User sends Get request
	Then Assert that number of "Women" usertype is 12
	And  Status code is 200
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "productsList");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("/{first}");
        JsonPath jsonPath = response.jsonPath(); //Response html formatında geldigi icin json a cevirip yazdırıyoruz.
        jsonPath.prettyPrint();

        //Do assertion
        assertEquals(200, response.statusCode());
        List<String> userTypeList = jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}.category.usertype.usertype");
        System.out.println("User Type List: " + userTypeList);
        assertEquals(12, userTypeList.size());
    }

}
