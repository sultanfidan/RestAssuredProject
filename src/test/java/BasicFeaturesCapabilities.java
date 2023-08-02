import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class BasicFeaturesCapabilities {

    @Test
    public void requestUsZipCode90210_checkStatusCode_expectHttp200() {

        // RestAssured rest=new RestAssured(); --yukarıda static olarak tanımlandı
        given().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                assertThat().
                statusCode(200).log().all();
    }

    @Test
    public void requestUsZipCode90210_checkContentType_expectApplicationJson() {

        given().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON);
        //contentType(application/json);

    }

    @Test
    public void requestUsZipCode90210_checkRequestAndResponseDetails() {

        given().
                log().all().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                log().body();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkStateNameInResponseBody_expectCalifornia() {

        given().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].state", equalTo("California"));
    }

    @Test
    public void requestUsZipCode90210_checkListOfPlaceNameResponseBody_expectContainsBeverlyHills() {

        given().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasItem("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNameInResponseBody_expectOne() {

        given().
                when().
                get("https://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }


}
