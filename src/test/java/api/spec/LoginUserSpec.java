package api.spec;

import helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class LoginUserSpec {
    public static RequestSpecification loginRequestSpec = with()
            .filters(CustomAllureListener.withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();
}
