package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class Get11 extends GoRestBaseUrl {

    /*
        Given https://gorest.co.in/public/v1/users
        When User send GET Request
        Then The value of "pagination limit" is 10
        And The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And The number of users should  be 10
        And We have at least one "active" status
        And "Kannan Ahluwalia", "The Hon. Tara Chaturvedi" and "Damayanti Dubashi" are among the users
        And The female users are less than or equals to male users
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "users");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("/{first}");
        response.prettyPrint();

        //Do assertion
        //1. yol:
        response.then().statusCode(200).body("meta.pagination.limit", equalTo(10),
                                          "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                                                  "data", hasSize(10),
                                                  "data.status", hasItem("active"),
                //"data" and "data.status" list return eder.
                                                  "data.name", hasItems("Chanda Guha", "Chandramauli Bandopadhyay Ret.", "Ganaka Marar"));
        //2. yol:
        JsonPath jsonPath = response.jsonPath();
        List<String> genders = jsonPath.getList("data.gender");
        int female = 0;
        for (String w : genders) {
            if(w.equals("female")){
                female++;
            }
        }
        assertTrue(female<=genders.size()-female);
        //3. yol:
        List<String> femaleList = jsonPath.getList("data.findAll{it.gender=='female'}.gender"); //data ya gelerek liste ulaşiyorum, bu yuzden direk findAll{} yazamayiz.
                                                                                                     //findAll{} u kullanabilmek icin elimizde bir json list i olmali, aksi taktirde kullanamazsin.
        List<String> maleList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        assertTrue(femaleList.size()<=maleList.size());
    }

}
