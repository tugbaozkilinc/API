package patch_request;

import base_urls.GmiBankBaseUrl;
import pojos.Country;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.States;
import util.ObjectMapperUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static util.AuthenticationGmiBank.generateTokenGmi;

public class ClassWork03 extends GmiBankBaseUrl {

    //https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1 dokümanını kullanarak en az 3 "state" içeren bir "country" oluşturan bir otomasyon testi yazınız.
    /*
      Given https://www.gmibank.com/api/tp-countries
      And   {
              "id": 0,
              "name": "Happy country",
              "states": [
                {
                  "id": 12,
                  "name": "Alabama",
                  "tpcountry": null
                },
                {
                  "id": 13,
                  "name": "New Jersey",
                  "tpcountry": null
                },
                {
                  "id": 14,
                  "name": "Colorado",
                  "tpcountry": null
                }
              ]
            }
      When user sends post request
      Then status code should be 201
      And the body should be like
            {
                "id": 175623,
                "name": "Happy country",
                "states": [
                    {
                        "id": 12,
                        "name": "Alabama",
                        "tpcountry": null
                    },
                    {
                        "id": 13,
                        "name": "New Jersey",
                        "tpcountry": null
                    },
                    {
                        "id": 14,
                        "name": "Colorado",
                        "tpcountry": null
                    }
                ]
            }
    */

    @Test
    public void test() {
        //Set the url
        spec.pathParams("first", "api", "second", "tp-countries");

        //Set the expected data
        States states1 = new States(12, "Alabama", null);
        States states2 = new States(13, "New Jersey", null);
        States states3 = new States(14, "Colorado", null);
        ArrayList<States> states = new ArrayList<>();
        states.add(states1);
        states.add(states2);
        states.add(states3);
        Country expectedData = new Country("Happy country", states);
        System.out.println("ExpectedData: " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().body(expectedData).headers("Authorization", "Bearer " + generateTokenGmi()).post("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Country actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), Country.class);
        System.out.println("Actual Data: " + actualData);
        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(states1.getId(), actualData.getStates().get(0).getId());
        assertEquals(states1.getName(), actualData.getStates().get(0).getName());
        assertEquals(states1.getTpcountry(), actualData.getStates().get(0).getTpcountry());
        assertEquals(states2.getId(), actualData.getStates().get(1).getId());
        assertEquals(states2.getName(), actualData.getStates().get(1).getName());
        assertEquals(states2.getTpcountry(), actualData.getStates().get(1).getTpcountry());
        assertEquals(states3.getId(), actualData.getStates().get(2).getId());
        assertEquals(states3.getName(), actualData.getStates().get(2).getName());
        assertEquals(states3.getTpcountry(), actualData.getStates().get(2).getTpcountry());
    }

}
