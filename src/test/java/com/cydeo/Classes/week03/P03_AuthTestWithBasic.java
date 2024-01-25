package com.cydeo.Classes.week03;

import com.cydeo.utility.SpartanTestAuth;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class P03_AuthTestWithBasic extends SpartanTestAuth {


    @Test
    public void positiveTest() {
        RestAssured.given().auth().basic("user", "user")
                .when().get("/api/spartans")
                .then()
                .statusCode(200);

    }

    @Test
    public void negativeTest() {
        RestAssured.given().auth().basic("user", "user")
                .when().get("/api/spartans")
                .then()
                .statusCode(401);

    }

    @Test
    public void Auth_with_Header() {
        RestAssured.given()
         //        .auth().basic("user", "user")
                .header("Authorization", "Basic ZWRpdG9yOmVkaXRvcg==")
                .when().get("/api/spartans")
                .then()
                .statusCode(200);
    }
}
