package com.cydeo.Classes.week02;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P03_HR_Hamcrest extends HrTestBase {


    /**
     *        Given accept type is Json
     *        And parameters: q={"region_id":2}
     *        When users sends a GET request to "/countries"
     *        Then status code is 200
     *        And Content type is application/json
     *        And Date header is not null
     *        Verify
     *            - count is 5
     *            - hasMore is false
     *            - first country id is AR
     *            - country names have Canada
     *            - country names have Canada,Mexico
     *            - total country size is 5
     *            - each country has country_id
     *            - each country region_id is 2
     *       - Print country names
     */


    @Test @DisplayName("GET HR /Countries")
    public void test1() {


        Response response = given().log().uri().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries").prettyPeek()  // to keep continue chaining
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Date", notNullValue())
                .body("count", is(5))
                .body("hasMore", is(false))
                .body("items[0].country_id", is("AR"))
                .body("items.country_name", hasItem("Canada"))
                .body("items.country_name", hasItems("Canada", "Mexico"))
                .body("items", hasSize(5))
                .body("items.country_id", everyItem(notNullValue()))
                .body("items.region_id", everyItem(is(2)))
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        //     *        Verify
        //     *            - count is 5

        //     *            - hasMore is false
        //     *            - first country id is AR
        //     *            - country names have Canada
        //     *            - country names have Canada,Mexico
        //     *            - total country size is 5
        //     *            - each country has country_id
        //     *            - each country region_id is 2

        System.out.println("-------------------- JSONPATH METHOD ----------------------");
        //     *       - Print country names
        List<String> allCountriesName = jsonPath.getList("items.country_name");
        System.out.print("allCountriesName = " + allCountriesName);


        System.out.println("-------------------- RESPONSE METHOD ----------------------");
        // Response method
        List<String> listCountriesName = response.path("items.country_name");
        System.out.println("listCountriesName = " + listCountriesName);


        Assertions.assertEquals(5, allCountriesName.size());
    }




}
