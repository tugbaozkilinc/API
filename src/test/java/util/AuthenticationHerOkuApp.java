package util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

    public static String generateTokenHerOkuApp(){
        String url = "https://restful-booker.herokuapp.com/auth";
        Map<String, String> tokenBody = new HashMap<>();
        tokenBody.put("username", "admin");
        tokenBody.put("password", "password123");
        Response response = given().when().contentType(ContentType.JSON).body(tokenBody).post(url); //response: REST Assured’in Response objesi.

        return response.jsonPath().getString("token"); //jsonPath(): Bu method gelen yanıtı JSON formatında yorumlamanı sağlar.
                                                            //getString("token"): JSON’daki "token" anahtarının karşılığı olan (String)değeri verir.
    }

}
