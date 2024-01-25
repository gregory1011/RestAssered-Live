package com.cydeo.Classes.week02;

import com.cydeo.utility.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P04_Deserialization extends FruitTestBase {


    /**
     * Send request to FruitAPI url and save the response
     * Accept application/json
     * GET /customers
     * Store the response in Response Object that comes from get Request
     * Print out followings
     *     - Print response
     *     - Content-Type is application/json
     *     - Status Code is 200
     *     - Retrieve data as JAVA Collections and print out following informations
     *
     System.out.println("====== GET META ======");
     System.out.println("====== GET LIMIT ======");
     System.out.println("====== GET CUSTOMERS ======");
     System.out.println("====== GET FIRST CUSTOMER ======");
     System.out.println("====== PRINT CUSTOMERS IDs ======");
     System.out.println("====== PRINT CUSTOMERS Names ======");

     *
     */

    @Test @DisplayName("GET Costumers")
    public void test1() {


        JsonPath jsonPath = given().log().uri().accept(ContentType.JSON)
                .when().get("/customers").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        System.out.println("====== GET All DATA ======");

        Map<String, Object> allDataIntoMap = jsonPath.getMap("");
        System.out.println("allDataIntoMap = " + allDataIntoMap);

        System.out.println("====== GET META ======");
        Map<String, Integer> metaData = (Map<String, Integer>) allDataIntoMap.get("meta");
        System.out.println("metaData = " + metaData);

        System.out.println("====== GET LIMIT ======");
        Integer limitData = metaData.get("limit");
        System.out.println("limitData = " + limitData);

        System.out.println("====== GET CUSTOMERS ======");
        List<Map<String, Object>> allCustomers = (List<Map<String, Object>>) allDataIntoMap.get("customers");
        System.out.println("customers = " + allCustomers);

        System.out.println("====== GET FIRST CUSTOMER ======");
        Map<String, Object> firstCustomer = allCustomers.get(0);
        System.out.println("firstCustomer = " + firstCustomer);

        System.out.println("====== PRINT FIRST CUSTOMER IDs ======");
        int id = (int) firstCustomer.get("id");
        System.out.println("id = " + id);

        Assertions.assertEquals(6, id);

        System.out.println("====== PRINT CUSTOMERS IDs ======");
        List<Object> allIDS = allCustomers.stream().map(eachCustomer -> (Integer) eachCustomer.get("id")).collect(Collectors.toList());
        System.out.println("allIDS = " + allIDS);

        // WE can use for loop to get each customer IDs
        List<Integer> allIds = new ArrayList<>();
        for (Map<String, Object> eachCostumer : allCustomers) {
            allIds.add( (Integer) eachCostumer.get("id"));
        }
        System.out.println("All ID's with for eachLoop = " + allIds);

        System.out.println("====== PRINT CUSTOMERS Names ======");
        List<String> allNames = new ArrayList<>();
        for (Map<String, Object> eachName : allCustomers) {
            String names = (String) eachName.get("name");
            allNames.add(names);
        }
        System.out.println("allNames = " + allNames);

    }




}
