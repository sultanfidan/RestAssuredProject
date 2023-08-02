import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostAPIs {

    @Test
    public void testGet() {

        baseURI = "https://reqres.in/api";
        given().get("/users?page=2").then().statusCode(200).
                body("data[4].first_name", equalTo("George")).
                body("data.first_name", hasItems("George", "Rachel"));
    }

    @Test
    public void testPost() {
        // Map<K,V> map= new HashMap<K,V>
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", "Sultan");
        map.put("job", "engineer");
        System.out.println(map);

        JSONObject request = new JSONObject(map); //json format post method body
        System.out.println(request);
        System.out.println(request.toJSONString()); //same the above json format
    }

    @Test
    public void testPost2() {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject request = new JSONObject();
        request.put("name", "Test23");
        request.put("job", "teacher");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/users").
                then().
                statusCode(201).log().all();


    }
}
