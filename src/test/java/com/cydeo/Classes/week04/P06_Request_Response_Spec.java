package com.cydeo.Classes.week04;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class P06_Request_Response_Spec extends SpartanTestBase {

    RequestSpecification requestSpecification;
    @BeforeEach
    void setUp() {

        requestSpecification = RestAssured
                .given().log().uri()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }


    @Test
    void getSingleSpartan() {

        Response response = requestSpecification
                .when().get("api/spartans/10");

        response.prettyPrint();
    }
}
