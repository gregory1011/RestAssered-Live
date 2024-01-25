package com.cydeo.Classes.week04;

import com.cydeo.utility.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class P04_DDT_withExcel {


    public static List<Map<String , String>> readExcel(){

        ExcelUtil excelUtil = new ExcelUtil(
                "src/test/resources/Library.xlsx", "short");

        List<Map<String, String>> dataList = excelUtil.getDataList();

        for (Map<String, String> eachDataMap : dataList) {

          //  System.out.println("eachDataMap = " + eachDataMap);
        }


        return dataList;
    }






    @ParameterizedTest
    @MethodSource ("readExcel")
    void test1(Map<String, String> eachMap) {

        System.out.println("eachMap = " + eachMap);
    }


    public static List<Integer> data(){

        return new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
    }

    @ParameterizedTest
    @MethodSource ("data")
    public void useData(int num){

        System.out.println("num = " + num);
    }



    // DIFFERENT EXAMPLE
    public static List<String> getFruits(){

        List<String> fruits = new ArrayList<>();

        fruits.add("Peach");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Orange");

        return fruits;
    }

    @ParameterizedTest
    @MethodSource ("getFruits")
    public void useFruits(String fruit){

        System.out.println("fruit = " + fruit);
    }



    // USING ARRAY  example

    public static String[][] numbers () {

        String [][] num ={
                {"one ","two", "nine"},
                {"three","four","zero"},
                {"five","six", "none"},
                {"seven","eight", "OK"}
        };
        return num;
    }

    @ParameterizedTest
    @MethodSource ("numbers")
    public void useNumbers(String firstElement, String secondElement, String thirdElement){

        System.out.println(firstElement+", "+ secondElement+", "+thirdElement);
    }

}

