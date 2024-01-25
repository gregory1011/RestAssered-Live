package com.cydeo.Classes.week04;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class P02_SchemaValidator extends SpartanTestBase {


    @Test
    void test1() {

        RestAssured
                .given().log().uri()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath
                        ("SingleSpartanSchema.json"))
                .body("id", Matchers.is(2));



    }



}
