package com.cydeo.Classes.week04;

import com.cydeo.utility.FormulaTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.xml.mapping.XmlObjectDeserializer;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class P01_xmlPath extends FormulaTestBase {


    @Test
    void test1() {

        /*
 MRData xmlns="http://ergast.com/mrd/1.5" series="f1" url="http://ergast.com/api/f1/drivers" limit="30" offset="0" total="858">
    <DriverTable>
        <Driver driverId="abate" url="http://en.wikipedia.org/wiki/Carlo_Mario_Abate">
            <GivenName>Carlo</GivenName>
            <FamilyName>Abate</FamilyName>
            <DateOfBirth>1932-07-10</DateOfBirth>
            <Nationality>Italian</Nationality>
        </Driver>
        <Driver driverId="abecassis" url="http://en.wikipedia.org/wiki/George_Abecassis">
            <GivenName>George</GivenName>
            <FamilyName>Abecassis</FamilyName>
            <DateOfBirth>1913-03-21</DateOfBirth>
            <Nationality>British</Nationality>
        </Driver>
       </DriverTable>
</MRData>
* */

        Response response = RestAssured
                .given().log().uri()
                .accept(ContentType.XML)
                .when().get("/drivers");

        XmlPath xmlPath = response.xmlPath();

        String name = xmlPath.get("MRData.DriverTable.Driver[0].GivenName");
        System.out.println("name = " + name);

        // get first driver ID - get attribute -->"@"driverId

        String id = xmlPath.get("MRData.DriverTable.Driver[0].@driverId");
        System.out.println("id = " + id);


    }




}
