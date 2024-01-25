package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://3.86.111.97:1000/ords/hr";
    }

    @AfterAll
    public static void destroy(){

        RestAssured.reset();
    }
}
