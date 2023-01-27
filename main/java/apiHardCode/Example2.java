package apiHardCode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Example2 {
    //baseURI
    String baseURI=RestAssured.baseURI="https://reqres.in/api";

    @Test
    public void updateInfo(){

        //prepare the request
        RequestSpecification request = given().headers("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}");

        //send the request
        Response response=request.when().patch("/api/users/2");
        response.prettyPrint();

        //extract the data
        String name=response.jsonPath().getString("name");
        Assert.assertEquals(name, "morpheus");

    }
}
