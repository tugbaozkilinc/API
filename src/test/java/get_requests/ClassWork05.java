package get_requests;

import base_urls.RegresInApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class ClassWork05 extends RegresInApiBaseUrl {

    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json; charset=utf-8”
        And
            Response body should be like; (Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
        }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "unknown", "second", 3);

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200).contentType(ContentType.JSON);
        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(3, jsonPath.getInt("data.id"));
        softAssert.assertEquals("true red", jsonPath.getString("data.name"));
        softAssert.assertEquals(2002, jsonPath.getInt("data.year"));
        softAssert.assertEquals("#BF1932", jsonPath.getString("data.color"));
        softAssert.assertEquals("19-1664", jsonPath.getString("data.pantone_value"));
        softAssert.assertEquals("https://reqres.in/#support-heading", jsonPath.getString("support.url"));
        softAssert.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", jsonPath.getString("support.text"));
        softAssert.assertAll();
    }

}
