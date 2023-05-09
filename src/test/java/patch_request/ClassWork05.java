package patch_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.AssertJUnit.assertEquals;

public class ClassWork05 extends DummyRestApiBaseUrl {

     /*
       Given URL: https://dummy.restapiexample.com/api/v1/employees
       When User sends GET Request
       Then User asserts i) Status code is 200
       And User asserts ii) There are 24 employees (hamcrest.Matchers kullanarak 24 employees olduğunu doğrulayınız)
       And User asserts iii) "Tiger Nixon" and "Garrett Winters" are among the employees (hamcrest.Matchers kullanarak "Tiger Nixon" ve "Garrett Winters"'ın employees arasında olduğunu doğrulayınız')
       And User asserts iv) The greatest age is 66
       And User asserts v) The name of the lowest age is "Tatyana Fitzpatrick"
       And User asserts vi) Total salary of all employees is 6,644,770
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "employees");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("/{first}");
        response.jsonPath().prettyPrint();

        //Do assertion
        response.then().statusCode(200).body("data.id", hasSize(24), "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ageList = jsonPath.getList("data.employee_age");
        Collections.sort(ageList);
        assertEquals(66, (int)(ageList.get(((int)(ageList.size()-1)))));
        List<String> isTatyana = jsonPath.getList("data.findAll{it.employee_age==19}.employee_name");
        assertEquals("Tatyana Fitzpatrick", isTatyana.get(0));
        List<Integer> salaryList = jsonPath.getList("data.employee_salary");
        int sumOfSalary = salaryList.stream().reduce(0, Integer::sum);
        assertEquals(6644770, sumOfSalary);
    }

}
