import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class XMLSamples {

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {
        given().
                when().
                get("http://localhost:9876/us/90210").
                then().
                assertThat().
                body("response.places.place.placeName", equalTo("Beverly Hills"));
    }
}
