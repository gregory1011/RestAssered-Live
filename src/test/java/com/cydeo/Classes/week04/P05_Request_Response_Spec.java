package com.cydeo.Classes.week04;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

public class P05_Request_Response_Spec extends SpartanTestBase {


    public static RequestSpecification getRequestSpec(){

        RequestSpecification requestSpecification = RestAssured
                .given().log().uri()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);

        return requestSpecification;
    }


    @Test
    void getTheSingleSpartan() {

               getRequestSpec().when().get("/api/spartans/2")
                       .then()
                       .statusCode(200);
    }

    @Test
    void getAllSpartan() {

//        RestAssured
//                .given().log().uri()
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
        getRequestSpec().when().get("/api/spartans");;
    }


    @Test
    void getTheAllSpartan() {

        ResponseSpecification responseSpecification = RestAssured
                .expect()
                .contentType(ContentType.JSON)
                .statusCode(200);

        getRequestSpec()
                .when().get("/api/spartans")
                        .then()
                        .spec(responseSpecification).extract().response().prettyPrint();
    }
}

