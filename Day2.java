import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class Day2 {
    RequestSpecBuilder requestBuilder;
    ResponseSpecBuilder responseBuilder;
    RequestSpecification request;
    ResponseSpecification response;


    @DataProvider(name = "dataReader")
    public static Object[][] dataReader() {
        return new Object[][]{
                {2, "janet.weaver@reqres.in", "Janet", "Weaver"},
                {3, "emma.wong@reqres.in", "Emma", "Wong"},
                {4, "ve.holt@reqres.in", "Eve", "Holt"}
        };
    }


@Test (dataProvider = "dataReader")
        public void jsonValid(int id, String email, String firstName, String lastName){
        Response response= given().
                baseUri("https://reqres.in/").
                and().pathParam("userID", id).
                when().
                get("api/users/{userID}").
                then().
                extract().response();
        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals(jsonPath.get("data.email"), email);
        Assert.assertEquals(jsonPath.get("data.first_name"), firstName);
        Assert.assertEquals(jsonPath.get("data.last_name"), lastName);
    }


    @BeforeClass
    public void builder() {
        requestBuilder = new RequestSpecBuilder();
        responseBuilder = new ResponseSpecBuilder();
        request= requestBuilder.setBaseUri("https://reqres.in/").build();
        response = responseBuilder.expectContentType("application/json; charset=utf-8").build();
    }

    @Test()
    public void requestAndResponse(){
        given().spec(request).
                when().
                get("/api/users/4").
                then().spec(response).log().all();
    }

}
