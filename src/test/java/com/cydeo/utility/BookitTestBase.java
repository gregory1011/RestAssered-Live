package com.cydeo.utility;

import io.restassured.RestAssured;
import lombok.Data;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
public abstract class BookitTestBase {

   protected String token;

    @BeforeAll
    public static void init (){
        RestAssured.baseURI = "https://api.qa.bookit.cydeo.com/";
    }

    @BeforeEach
    public void getToken() {

        String email = "";
        String password = "";

        token = RestAssured.given()
                .queryParam("email", email)
                .queryParam("password", password)
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

    }


    @AfterAll
    public static void destroy() {
        RestAssured.reset();
    }


}
