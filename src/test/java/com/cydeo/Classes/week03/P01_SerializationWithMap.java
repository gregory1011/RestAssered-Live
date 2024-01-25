package com.cydeo.Classes.week03;

import com.cydeo.utility.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class P01_SerializationWithMap extends FruitTestBase {

    private int createdId;
    @Test
    public void postFruit() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        Map<String, Object> bodyRequest = new HashMap<>();
        requestBody.put("name", "Banana");
        requestBody.put("price", "0.79");

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
     //           .body("{\n" + "  \"name\": \"Kiwi,\",\n" + "  \"price\": 3.49\n" + "}") --> This Post Body is not practical
                // Most of the time we are using POJO or Map
                .body(requestBody)
                .post("products").then()
                .statusCode(201)
                .extract().response();

        response.prettyPrint();

        // Let's assume that API is not returning ID, how to get created fruit in next test
        String selfLink = response.path("self_link");
        String substring = selfLink.substring(selfLink.lastIndexOf("/")+1);

        int id = Integer.parseInt( substring ); // converting string to int
        System.out.println("createdID = " + id);

        createdId = id; // make it global variable
    }


    @Test
    public void getFruit() {
     // get method use as ----> homework
// solution 1 - order your tests
// solution 2 - ...
        System.out.println("createdId with GET method = " + createdId);



    }



}
