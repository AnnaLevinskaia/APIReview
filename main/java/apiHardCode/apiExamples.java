package apiHardCode;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class apiExamples {
    //    initialise the Base URI
    String baseURI =  RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQ2ODM4ODUsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDcyNzA4NSwidXNlcklkIjoiNDgzNyJ9.EyslI8StZQe6Om8nBjv-0eVkII55VV8Z8Bkyt4CJWGM";

    @Test
    public void createEmployee(){
        //prepare the request
        RequestSpecification request = given().headers("Content-Type", "application/json").headers("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"Anna\",\n" +
                        "  \"emp_lastname\": \"Deleted\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1990-09-10\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");
//         send the request and get response
        Response resp = request.when().post("/createEmployee.php");
//         print the response
        resp.prettyPrint();
//extract the first name from the response

        String empFname = resp.jsonPath().getString("Employee.emp_firstname");
        System.out.println(empFname);

//         assert that the first name is Anna
        Assert.assertEquals(empFname,"Anna");
    }

//write the request to get all the employees and print the data in console

    @Test
    public void getAllEmployees(){
        RequestSpecification request=given().headers("Content-Type", "application/json").headers("Authorization", token);

        Response resp=request.when().get("/getAllEmployees.php");

        resp.prettyPrint();
    }

    @Test
    public void gitJobTitle(){
        RequestSpecification request=given().headers("Content-Type", "application/json").headers("Authorization", token);

        Response resp=request.when().get("/jobTitle.php");

        resp.prettyPrint();
        String res=resp.jsonPath().getString("Jobs[0].id");
        //System.out.println(res);

        //print only the job titles
        //check the size of array
        //String array=resp.jsonPath().getString("Jobs");
        //find the size of array
        List<String> array=resp.jsonPath().getJsonObject("Jobs");
        System.out.println(array.size());

        for (int i = 0; i < array.size(); i++) {
            String x=resp.jsonPath().getString("Jobs["+i+"].job");
            System.out.println(x);
            Assert.assertEquals("doctor","doctor");
        }


    }
}
