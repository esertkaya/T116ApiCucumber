package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class HeroBookingPojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private HeroBookingDatesPojo bookingdates;
    private String additionalneeds;

}
