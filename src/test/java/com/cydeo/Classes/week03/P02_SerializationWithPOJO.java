package com.cydeo.Classes.week03;

import com.cydeo.POJO.Fruit;
import com.cydeo.utility.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class P02_SerializationWithPOJO extends FruitTestBase {

    @Test
    public void postFruit() {

        // POJO Fruit class
        Fruit requestBody = new Fruit();
        requestBody.setPrice(2.99);
        requestBody.setName("Peach");


        Response response = RestAssured.given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                //           .body("{\n" + "  \"name\": \"Kiwi,\",\n" + "  \"price\": 3.49\n" + "}") --> This Post Body is not practical
                // Most of the time we are using POJO or Map
                .body(requestBody)
                .post("products").then()
                .statusCode(201)
                .extract().response();

        response.prettyPrint();

    }
}
