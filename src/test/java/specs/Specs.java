package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class Specs {
    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification responseStatusCodeSpec(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(STATUS)
                .log(BODY)
                .build();
    }
}
