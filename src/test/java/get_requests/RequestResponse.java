package get_requests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RequestResponse {

    /*
    1)Postman manuel API test i için kullanilir.
    2)API otomasyonu için Rest-Assured Library kullanacagiz.
    3)Otomasyon kodlarının yazımı için şu adımları izliyoruz:
      a) Gereksinimleri anlama
      b) Test case i yazma:
           Test case i yazmak için "Gherkin Language" kullaniyoruz.
           x) Given: Preconditions  --> Endpoint, body
           y) When : Functions --> Get, Put, Delete...
           z) Then : Verification --> Assert
           t) And --> Çoklu işlemlerin art arda yazilacağı yerlerde kullanilir.
      c) Test kodunu yazarken şu adımları izleriz:
           i)   Set the URL
           ii)  Set the expected data
           iii) Send the request and get the response
           iv)  Do assertion
    */

    public static void main(String[] args) {

        //Get request nasil yapilir? (Once postman de manual test yapilir)
        String endPoint = "https://restful-booker.herokuapp.com/booking/55";
        Response response = given().when().get(endPoint);
        response.prettyPrint(); //prettyPrint() response datayi yazdirir.

        //Note: Headers --> Metadata
        //Status code nasil yazdirilir?
        System.out.println("Status code: " + response.statusCode());

        //Content type nasil yazdirilir?
        System.out.println("Content type: " + response.contentType());

        //Status line nasil yazdirilir?
        System.out.println("Status line: " + response.statusLine());

        //Header nasil yazdirilir?
        System.out.println("Connection: " + response.header("Connection"));
        System.out.println("Server: " + response.header("Server"));
        System.out.println("Content-Type: " + response.header("Content-Type"));

        //Headers nasil yazdirilir?
        System.out.println(response.headers());

        //Time nasil yazdirilir?
        System.out.println("Time: " + response.getTime());
    }

}
