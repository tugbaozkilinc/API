package get_requests;

import base_urls.RestfulBookerHerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Get05 extends RestfulBookerHerOkuAppBaseUrl {

    /*
        Given https://restful-booker.herokuapp.com/booking
        When User sends a GET request to the URL
        Then Status code is 200
	  	And Among the data there should be someone whose firstname is "Sally" and last name is "Brown"
    */
    //https://restful-booker.herokuapp.com/booking?firstname=Sally&lastname=Brown ? isareti query params larin basladigi yeri ifade eder. / ile de path params lar baslatilir.
    //2 query params & ile ayrilir.

    @Test
    public void test() {
        //Set the url
        spec.pathParam("first", "booking").queryParams("firstname", "Sally","lastname", "Brown");

        //Set the expected data

        //Send the request get the response
        Response response = given(spec).get("/{first}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));
        /*
        response.asString() metodu, response body'nin tamamını bir String (düz metin) olarak döner.
        Yani JSON, Java tarafında aşağıdaki tek satırlık string gibi görünür:
        "[{\"bookingid\":2811},{\"bookingid\":3094},{\"bookingid\":2810}]"
        */
    }

}
