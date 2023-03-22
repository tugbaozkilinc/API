package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestfulBookerHerOkuAppResponsePojo {

    private Integer bookingid;
    private RestfulBookerHerOkuAppPojo booking;

    public RestfulBookerHerOkuAppResponsePojo() {
    }

    public RestfulBookerHerOkuAppResponsePojo(Integer bookingid, RestfulBookerHerOkuAppPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public RestfulBookerHerOkuAppPojo getBooking() {
        return booking;
    }

    public void setBooking(RestfulBookerHerOkuAppPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }

}
