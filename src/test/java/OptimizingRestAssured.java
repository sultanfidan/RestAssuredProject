import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OptimizingRestAssured {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").build();

    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpesification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void requestZipCode90210_checkPlaceNameInResponseBody_expectedBeverlyHills() {
        given().spec(requestSpec).
                when().
                get("/us/90210").
                then().
                spec(responseSpec).
                and().
                assertThat().body("places[0].'place name'", equalTo("Beverly Hills")).log().all();
    }

    @Test
    public void requestZipCode90210_extractPlaceNameFromResponseBody_assertBeverlyHills() {

        //extract method is useful if you want to use values from the response in sequent requests.
        String placeName =

                given().
                        spec(requestSpec).
                        when().
                        get("/us/90210").
                        then().
                        extract().
                        path("places[0].'place name'");

        Assert.assertEquals(placeName, "Beverly Hills");

    }

}
