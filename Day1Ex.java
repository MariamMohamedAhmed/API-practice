import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Day1Ex {

    @Test
    public void request_checkResponseCode_expect200(){
        given().
                baseUri("https://reqres.in/").
                when().
                get("api/user").
                then().
                log().all().
                and().
                assertThat().statusCode(200);
    }

    @Test
    public void request_checkContentType_expectjson(){
        given().
                baseUri("https://reqres.in/").
                when().
                get("api/user").
                then().
                assertThat().contentType("application/json; charset=utf-8");
    }

    @Test
    public void request_queryParams(){
        given().
                baseUri("https://reqres.in/").
                and().
                queryParam("page",2).//by default 1 = /api/user?page=2
                and().
                queryParam("per_page",3).// by default 6 = /api/user?per_page=3
                when().
                get("api/user").
                then().log().body();
    }

    @Test
    public void request_pathParam(){
        given().
                baseUri("https://reqres.in/").
                and().
                pathParam("userID",4). // el path parameters de mandatory mdam 3yza user 3 mlhas default -> /users/3
                when().
                get("api/users/{userID}").
                then().log().all();
    }

    @Test
    public void request_postReq(){
        given().
                baseUri("https://reqres.in/").
                and().header("Content-Type", "application/json; charset=utf-8").
                and().body("{\n" +
                "\"name\": \"morpheus\",\n" +
                "\"job\": \"leader\"\n" +
                "}").
                when().post("api/users").
                then().log().all();
    }

    @Test
    public void request_putReq(){
        given().
                baseUri("https://reqres.in/").
                and().contentType(ContentType.JSON).
                and().body("{\n" +
                "\"name\": \"morpheus\",\n" +
                "\"job\": \"zion resident\"\n" +
                "}").
                when().put("api/users").
                then().log().all();
    }

}

