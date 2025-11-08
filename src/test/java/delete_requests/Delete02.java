package delete_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDeletePojo;
import util.ObjectMapperUtils;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {

    /*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
    HTTP Request Method: DELETE Request
    Test Case: Type by using Gherkin Language
    Assert: i) Status code is 200
            ii) "status" is "success"
            iii) "data" is "2"
            iv) "message" is "Successfully! Record has been deleted"
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "delete", "second", 2);

        //Set the expected data
        DummyRestApiDeletePojo expectedData = new DummyRestApiDeletePojo("success", "2", "Successfully! Record has been deleted");
        System.out.println("Expected Data: " + expectedData);

        //Send the request and get the response
        Response response = given(spec).delete("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        DummyRestApiDeletePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiDeletePojo.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData(), actualData.getData());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
    }

    //@JsonIgnoreProperties(ignoreUnknown = true) Bu annotation, belirtilen class'ta tanımlanmamış bir JSON özelliği ile karşılaşıldığında Jackson'ın bu özellikleri yoksaymasına
    //veya görmezden gelmesini saglar. Jackson'un bu belirsiz özellikleri görmezden gelmesine ve Java objesinin oluşturulmasını tamamlamasına izin verir.

}
