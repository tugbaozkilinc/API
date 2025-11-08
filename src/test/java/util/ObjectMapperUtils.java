package util;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;

public class ObjectMapperUtils {

    //ObjectMapper().readValue(json, cls) methodu birinci parametrede aldığı String formatındaki Json datayı ikinci parametrede belitilen Java objesine çevirir.
    //Test imizde exception istemedigimiz icin burda olusturuyoruz.

    public static <T> T convertJsonToJava(String json, Class<T> cls){ //Class<T> herhangi bir class demek.
        try {                                                         //<T> T -> Herhangi bir data tipi (<A> T)
            return new ObjectMapper().readValue(json, cls); //import org.codehaus.jackson.map.ObjectMapper;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
