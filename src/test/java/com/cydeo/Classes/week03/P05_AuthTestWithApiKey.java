package com.cydeo.Classes.week03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class P05_AuthTestWithApiKey {

    String apiKey = "a6v39P9aam1Q7YaB-TS5u34p_AdSejb5RJoke0lA8YQIV6Ac";


    @Test
    public void apiKeyAsWithHeader() {

        RestAssured
                .given()
                //.accept(ContentType.JSON)
                .header("Authorization", apiKey)
                .get("https://api.currentsapi.services/v1/latest-news")
                .prettyPrint();

    }

    @Test
    void apiKeyAsQueryParam() {

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .queryParam("apiKey",apiKey)
                .get("https://api.currentsapi.services/v1/latest-news")
                .prettyPrint();

    }


}


