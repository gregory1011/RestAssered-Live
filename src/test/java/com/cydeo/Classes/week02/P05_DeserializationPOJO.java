package com.cydeo.Classes.week02;

import com.cydeo.POJO.MRData;
import com.cydeo.POJO.Status;
import com.cydeo.POJO.StatusTable;
import com.cydeo.utility.FormulaTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P05_DeserializationPOJO extends FormulaTestBase {

     /*
        - ERGAST API
        - Given accept type is json
        - When user send request /status.json
        - Then verify status code is 200
        - And content type is application/json; charset=utf-8
        - And total is 137
        - And limit is 30
        - And each status has statusId
     */


    @Test @DisplayName("GET statusPOJO")
    public void test1() {

        JsonPath jsonPath = given().log().uri()
                .when().get("/status.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        // DO DESERIALIZATION
        MRData mrData = jsonPath.getObject("MRData", MRData.class);
        System.out.println("mrData = " + mrData);

        System.out.println("-------------GET STATUS TABLE----------------");
        StatusTable statusTable = mrData.getStatusTable();
        System.out.println("statusTable = " + statusTable);
        
        System.out.println("-------------GET STATUS LIST----------------");
        List<Status> statusList = statusTable.getStatusList();
        System.out.println("statusList = " + statusList);
        
        System.out.println("-------------GET FIRST STATUS----------------");
        String firstStatus = statusList.get(0).getStatus();
        System.out.println("firstStatus = " + firstStatus);

        System.out.println("-------------GET FIRST ID----------------");
        String firstID = statusList.get(0).getStatusId();
        System.out.println("firstID = " + firstID);

    }






}
