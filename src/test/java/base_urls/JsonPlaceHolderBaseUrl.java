package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification spec; //request i specific hale getiriyoruz.
    @Before
    public void setUp(){ //spec in atamasini burda yapiyoruz
        spec = new RequestSpecBuilder().setAccept(ContentType.JSON).setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

    //Base url ler icin olusturdugumuz bir class, tekrar tekrar isleme girmemek icin bunu yapiyoruz. Calisacagimiz api lar public olmayacak bu yuzden request gonderirken
    //authorization(yani token alma ve token ile kimligini belirtip yetkiyi alma) a ihtiyacimiz olacak. Bizim uzerinde islem yaptigimiz api lar public oldugundan dolayi request icin
    //authorization yapmaya gerek yok.

}
