package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResponse {

    /*
    1)Postman manuel API testi için kullanilir.
    2)API otomasyonu için Rest-Assured Library kullanacagiz.
    3)Otomasyon kodlarının yazımı için şu adımları izliyoruz:
       a) Gereksinimleri anlama
       b) Test case i yazma:
          -Test case i yazmak için "Gherkin Language" kullaniyoruz.
           x) Given: Ön koşullar  --> Endpoint, body
           y) When : İşlemler --> Get, Put, Delete...
           z) Then : Dönütler --> Assert
           t) And --> Çoklu işlemlerin art arda yazilacağı yerlerde kullanilir.
        c) Test kodunu yazarken şu adımları izleriz:
           i)   Set the URL
           ii)  Set the expected data
           iii) Send the request and get the response
           iv)  Do assertion
    */

    public static void main(String[] args) {

        //Get request nasil yapilir?
        String url = "https://restful-booker.herokuapp.com/booking/55";
        Response response = given().when().get(url);
        response.prettyPrint(); //prettyPrint() response datayi yazdirir.

        //Note: Headers --> Metadata
        //Status code nasil yazdirilir?
        System.out.println("Status code: " + response.statusCode());

        //Content type nasil yazdirilir?
        System.out.println("Content type: " + response.contentType()); //Response in icinde headers da var

        //Status line nasil yazdirilir?
        System.out.println("Status line: " + response.statusLine());

        //Header nasil yazdirilir?
        System.out.println("Connection: " + response.header("Connection"));
        System.out.println("Server: " + response.header("Server"));

        //Headers nasil yazdirilir?
        System.out.println(response.headers());

        //Time nasil yazdirilir?
        System.out.println("Time: " +response.getTime());
    }

}
