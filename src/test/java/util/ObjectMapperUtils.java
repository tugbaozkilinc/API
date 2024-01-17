package util;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;

public class ObjectMapperUtils {

    //<T> T -> Herhangi bir data tipi (<A> T)
    //ObjectMapper().readValue(json, cls) methodu birinci parametrede aldığı String formatındaki Json datayı ikinci parametrede belitilen Java objesine çevirir.
    //Buraya Object yazamiyoruz cunku, class icin girecegimiz datanin da type i object olma durumunda kalacak. Bu durumda surekli type casting yapmam gerekecek.(Obje yavastir,
    //objenin method lari yoktur, type casting gerektirir.)

    public static <T> T convertJsonToJava(String json, Class<T> cls){ //Generic method. Class<T> herhangi bir class demek. Test imizde exception istemedigimiz icin burda olusturuyoruz.
        try {
            return new ObjectMapper().readValue(json, cls); //import org.codehaus.jackson.map.ObjectMapper;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
