package post_requests;

import base_urls.RegresInApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ClassWork01 extends RegresInApiBaseUrl {

    /*
       Given
            https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then
            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "unknown");

        //Set the expected data

        //Send the request and get the expected data
        Response response = given().spec(spec).get("/{first}");
        response.prettyPrint();

        //Do assertion
        //1)Status code is 200
        assertEquals(200, response.statusCode());
        //2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        List<String> pantoneList = jsonPath.getList("data.pantone_value");
        System.out.println("Pantone List: " + pantoneList);
        //3)Print all ids greater than 3 on the console. Assert that ids which are greater than 3 equals to 3
        List<String> idList = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("Id List: " + idList);
        assertEquals(3, idList.size());
        //4)Print all names whose ids are less than 3 on the console. Assert that the number of names whose ids are less than 3 is 2
        List<String> nameList = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("Name List: " + nameList);
        assertEquals(2, nameList.size());
    }

}
