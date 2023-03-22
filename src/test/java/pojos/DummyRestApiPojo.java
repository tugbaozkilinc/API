package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //codehaus tan import et.
public class DummyRestApiPojo {

    private String status;
    private DummyRestApiDataPojo data;
    private String message;

    public DummyRestApiPojo() {
    }

    public DummyRestApiPojo(String status, DummyRestApiDataPojo data, String message) {
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

    public DummyRestApiDataPojo getData() {
        return data;
    }

    public void setData(DummyRestApiDataPojo data) {
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
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

}
