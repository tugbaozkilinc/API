package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import static util.AuthenticationHerOkuApp.generateTokenHerOkuApp;

public class RestfulBookerHerOkuAppBaseUrl {

    protected RequestSpecification spec;
    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().addHeader("Cookie", "token=" + generateTokenHerOkuApp()).setContentType(ContentType.JSON).
                                        setBaseUri("https://restful-booker.herokuapp.com").build();
    }

}
