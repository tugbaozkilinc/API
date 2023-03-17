package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get07 extends JsonPlaceHolderBaseUrl {

    /*
      Given
          https://jsonplaceholder.typicode.com/todos
      When
          I send GET Request to the URL
      Then
          1)Status code is 200
          2)Print all ids greater than 190 on the console
            Assert that there are 10 ids greater than 190
          3)Print all userIds whose ids are less than 5 on the console
            Assert that the number of userIds whose ids are less than 5 is 4
          4)Print all titles whose ids are less than 5
            Assert that "delectus aut autem" is one of the titles whose id is less than 5
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).get("/{first}");
        response.prettyPrint();

        //Do assertion
        //1.Status code is 200
        assertEquals(200, response.statusCode());
        //2.Print all ids greater than 190 on the console. Assert that there are 10 ids greater than 190
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idList = jsonPath.getList("findAll{it.id>190}.id"); //Groovy Language, java temelli programlama dilidir.
        System.out.println("Id List: " + idList);
        assertEquals(10, idList.size());
        //3.Print all userIds whose ids are less than 5 on the console. Assert that the number of userIds whose ids are less than 5 is 4
        List<Integer> userIdList = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("UserId List: " + userIdList);
        Assert.assertEquals(4, userIdList.size());
        //4.Print all titles whose ids are less than 5. Assert that "delectus aut autem" is one of the titles whose id is less than 5
        List<String> titleList = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("Title List: " + titleList);
        assert titleList.contains("delectus aut autem");
    }

}
