package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification spec;
    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

    //"Spec" objesi, Rest Assured kütüphanesi ile API testlerinde kullanılan ve API request lerinin özelliklerini tanımlamak için kullanılan bir objedir.
    //Accept-Type:
    //"Accept-Type" başlığı, istemcinin sunucudan istediği yanıt veri formatını belirtir. Örneğin, bir istemci "application/json" tipinde yanıt verisi bekliyorsa, "Accept-Type: application/json" başlığını isteğine ekler.
    //Content-Type:
    //"Content-Type" başlığı, istemcinin sunucuya gönderdiği verinin formatını belirtir. Örneğin, bir istemci JSON formatında veri göndermek istiyorsa, "Content-Type: application/json" başlığını isteğine ekler.

    //setContentType(ContentType.JSON) bunu buraya yazmamızın sebebi, her seferinde api ya body gonderirken test case i mize .contentType(ContentType.JSON) yazma tekrarından kurtulmak.
    //Base url ler icin olusturdugumuz bir class, tekrar tekrar isleme girmemek icin bunu yapiyoruz. Calisacagimiz api lar public olmayacak bu yuzden request gonderirken
    //authorization(yani token alma ve token ile kimligini belirtip yetkiyi alma) a ihtiyacimiz olacak. Bizim uzerinde islem yaptigimiz api lar public oldugundan dolayi request icin
    //authorization yapmaya gerek yok.

}
