package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {

    /*
        Given  1)  https://jsonplaceholder.typicode.com/todos
               2)  {
                   "userId": 55,
                   "title": "Tidy your room",
                   "completed": false
                   }
        When I send POST Request to the Url
        Then Status code is 201
        And response body is like   {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
    */

    @Test //JUnit ten import et yoksa calismaz, cunku @Before method u Junit ten import ettik
    public void test() {
        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data --> Payload
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        System.out.println("ExpectedData: " + expectedData);

        //Send the request get the response
        Response response = given(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}"); //Bu body gonderecegimiz datayi iceriyor, contentType(ContentType.JSON)
                                                                                                             //ile content type i belirtiyoruz. Bu sekilde bu datayi java dilinde
                                                                                                             //gonderemeyiz, onu ceviren bir library e ihtiyacimiz var. Bunun icin
                                                                                                             //pom.xml e json library i ekledik. Daha sonra gson library i ekledik.
                                                                                                             //Gson i java objesini json a cevirmek icin serializer olarak kullandik.
                                                                                                             //Note: Sonra jackson i ekledik.
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class); //DE-Serialization --> Json to Java
        System.out.println("ActualData: " + actualData);
        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }

    //Note: Put isleminde tum datalari guncellemezsen eger guncellemedigin kisimlarin null oldugunu gorursun, bunun icin patch ya da put isleminden hangisini yapacagina karar vermelisin.
    //Ama bu Api in calisma sistemine gore degisiklik gosterebilir.(Her bir api in kendine ozgu sistemi vardir.)
    /*
    De-Serialization: Json datanın Java objesine çevrilmesidir.
    Serialization: Java objesinin, Json dataya çevrilmesidir.
    2 türlü De-Serialization yapacağız:
        i) Gson: Google tarafından üretilmiş bir library.(as() method u ile yapılan çeviriler arka planda Gson'ı ve Jackson'ı kullanıyor. Gson objesi kullanarak da de-serialization yapacağız.)
        ii) Object Mapper: En popüleri(Object Mapper + Pojo Class(Plain Old Java Object))
    */

}
