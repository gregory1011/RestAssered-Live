package com.cydeo.Classes.week04;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P07_Request_Response_Spec_Cucumber extends SpartanTestBase {

   static RequestSpecification requestSpecification;

    @Test
    @Order(1)
    public void acceptHeaderJson(String acceptHeader){

        requestSpecification = RestAssured.given().log().uri()
                .accept(acceptHeader);
    }

    @Test
    @Order(2)
    public void contentTypeJson(String contentType){

        requestSpecification
                .contentType(contentType);
    }
    @Test
    @Order(3)
    public void sendGetRequest(String endpoint){

        // "api/spartans/11"  --> endPoint
        requestSpecification.when().get(endpoint).prettyPrint();
    }

}
