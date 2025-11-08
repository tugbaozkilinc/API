package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyRestApiDeletePojo {

    //@JsonIgnoreProperties(ignoreUnknown = true) anotasyonu, genellikle Java'nın Jackson kütüphanesiyle kullanılır. Bu anotasyon, JSON'dan Java nesnelerine
    //dönüşüm sırasında bilinmeyen özelliklerin varlığını görmezden gelmek için kullanılır. Yani, bir JSON nesnesinde Java sınıfınızda tanımlanmamış ekstra alanlar varsa,
    //bu anotasyon sayesinde bu bilinmeyen alanlar dönüşüm sırasında hata üretmez ve sadece tanımlı alanlar dönüştürülür.

    private String status;
    private String data;
    private String message;

    public DummyRestApiDeletePojo() {
    }

    public DummyRestApiDeletePojo(String status, String data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", data='" + data + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
