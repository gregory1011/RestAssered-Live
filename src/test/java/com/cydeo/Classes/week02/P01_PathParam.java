package com.cydeo.Classes.week02;

import com.cydeo.utility.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_PathParam extends FruitTestBase {

    /**
     *1- Given accept type is Json
     *2- Path Parameters value is

     id —> 4
     - When user sends GET request to /products/{id}4
     - Verify followings
     Status code should be 200
     Content Type is application/json
     Print response
     id is 4
     Name is "Coconut"
     Vendor name is "True Fruits Inc."
     **/


    @Test @DisplayName("GET single product with Response")
    public void test1() {

        Response response = given().log().uri().accept(ContentType.JSON)
                .pathParam("id", 4)
                .when().get("products/{id}");

        //response.prettyPeek();

        //  Status code should be 200
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        // Content Type is application/json
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());
        Assertions.assertEquals("application/json", response.contentType());

        // Print response
       // --> response.print();

        // id is 4
        int id = response.path("id");
        Assertions.assertEquals(4, id);
        Assertions.assertEquals(4, (Integer) response.path("id"));

        // Name is "Coconut"
        String name = response.path("name");
        Assertions.assertEquals("Coconut", name);
        Assertions.assertEquals("Coconut", response.path("name"));


        // Vendor name is "True Fruits Inc."
        String vendorName = response.path("vendors[0].name");
        Assertions.assertEquals("True Fruits Inc.", vendorName);

    }

    @Test @DisplayName("GET single product with JsonPath")
    public void test2() {

        Response response = given().log().uri().accept(ContentType.JSON)
                .pathParam("id", 4)
                .when().get("products/{id}").prettyPeek();

        JsonPath jsonPath = response.jsonPath();

        //  Status code should be 200
        Assertions.assertEquals(200, response.getStatusCode());

        // Content Type is application/json
        Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());

        // Print response
       // jsonPath.prettyPeek();

        // id is 4
        int id = jsonPath.getInt("id");
        Assertions.assertEquals(4, id);

        // Name is "Coconut"
        String name = jsonPath.getString("name");
        Assertions.assertEquals("Coconut", name);

        // Vendor name is "True Fruits Inc."
        String vendorName = jsonPath.getString("vendors[0].name");
        Assertions.assertEquals("True Fruits Inc.", vendorName);


    }

    @Test @DisplayName("GET single product with Hamcrest")
    public void test3() {

        given().log().uri().accept(ContentType.JSON)
                .pathParam("id", 4)
                .when().get("products/{id}").prettyPeek()
                // Assertions with Junit using Hamcrest
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", Matchers.is(4))
                .body("name", Matchers.is("Coconut"))
                .body("vendors[0].name", Matchers.is("True Fruits Inc."));






    }


    @Test @DisplayName("GET single Product with Hamcrest plus JsonPath")
    public void test4() {

        JsonPath jsonPath = getResponse("/products/{id}", 4);

        Assertions.assertEquals(4, jsonPath.getInt("id"));

        Assertions.assertEquals("Coconut", jsonPath.getString("name"));

        Assertions.assertEquals("True Fruits Inc.", jsonPath.getString("vendors[0].name"));
    }


    // Method to return response object
    public static JsonPath getResponse(String endpoint, int pathParam){

      return  given().log().uri().accept(ContentType.JSON)
                .pathParam("id", pathParam)
                .when().get(endpoint).prettyPeek()
                // Assertions with Junit using Hamcrest
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();
    }


    /**     HOMEWORK
     *1- Given accept type is Json
     *2- Path Parameters value is

     id —> 2
     *3- Query Parameter start  value  is 1*4- Query Parameter limit  value  is 100*5- When user sends GET request to /vendors/{id}/products*6- Verify followings
     Status code should be 200
     Content Type is application/json
     verify limit is 100
     verify start is 1
     print all product names
     **/

}
