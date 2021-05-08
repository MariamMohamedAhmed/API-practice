import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Practice {


        @Test
        public void request_Header(){
            given().
                    baseUri("https://reqres.in/").
                    and().header("Content-Type", "application/json; charset=utf-8").
                    and().header("Connection", "keep-alive").
                    and().header("accept","*/*").
                    when().get("api/users").
                    then().log().all();
        }

        @Test
        public void request_putReq(){
            given().
                    baseUri("https://reqres.in/").
                    and().contentType(ContentType.JSON).
                    and().body("""
                    {
                    "name": "morpheus",
                    "job": "zion resident"
                    }""").
                    when().put("api/users").
                    then().log().all();
        }

    }


