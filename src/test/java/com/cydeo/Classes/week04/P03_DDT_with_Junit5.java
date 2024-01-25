package com.cydeo.Classes.week04;

import com.cydeo.utility.SpartanTestAuth;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;

public class P03_DDT_with_Junit5 extends SpartanTestBase {


   // @Test
    @ParameterizedTest
    @CsvFileSource (resources = "/names.csv", numLinesToSkip = 1)
    void getTheNamesFromCSV_File(String name, String gender, long phone) {

//        System.out.println("name = " + name);
//        System.out.println("gender = " + gender);
//        System.out.println("phone = " + phone);

        Map<String, Object> spartan = new HashMap<>();

        spartan.put("name", name);
        spartan.put("gender", gender);
        spartan.put("phone", phone);

        System.out.println("spartan = " + spartan);

        RestAssured
                .given().log().uri()
                .contentType(ContentType.JSON)
                .body(spartan)
                .post("/api/spartans")
                .then()
                .statusCode(201);

    }



}
