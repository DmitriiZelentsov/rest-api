package api.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.AuthApi.authorizeRequest;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class SpecAllTests {
    public static RequestSpecification requestSpecification = with() //для авторизации
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .log().all()
            .contentType(JSON);

    public static RequestSpecification sendRequestWithBodySpec = new RequestSpecBuilder() //для добавления книги
            .addFilter(withCustomTemplates())
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + authorizeRequest().getToken())
            .log(ALL)
            .build();

    public static RequestSpecification sendRequestWithoutBodySpec = new RequestSpecBuilder()   //для удаления
            .addFilter(withCustomTemplates())
            .addHeader("Authorization", "Bearer " + authorizeRequest().getToken())
            .log(ALL)
            .build();

    public static ResponseSpecification responseSpecStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification responseSpecStatusCode201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();

    public static ResponseSpecification responseSpecStatusCode204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(ALL)
            .build();
}
