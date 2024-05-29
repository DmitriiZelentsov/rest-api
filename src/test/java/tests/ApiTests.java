package tests;

import models.RegisterBodyModel;
import models.RegisterResponseModel;
import models.UsersBodyModel;
import models.UsersResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static specs.Specs.*;

public class ApiTests extends TestBase{

    @Test
    @DisplayName("POST REGISTER - SUCCESSFUL")
    void successfulRegisterTest() {
        RegisterBodyModel authData = new RegisterBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        RegisterResponseModel response = step("Make request", () ->
                given(requestSpec)
                        .body(authData)
                .when()
                        .post("/register")
                .then()
                        .spec(responseStatusCodeSpec(200))
                        .extract().as(RegisterResponseModel.class)
        );
        step("Check response", ()-> {
            assertAll(
                    () -> assertThat(4).isEqualTo(response.getId()),
                    () -> assertThat(response.getToken()).isNotNull()
            );
        });
    }

    @Test
    @DisplayName("POST REGISTER - UNSUCCESSFUL")
    void unsuccessfulRegisterTest() {
        RegisterBodyModel authData = new RegisterBodyModel();
        authData.setEmail("sydney@fife");

        RegisterResponseModel response = step("Make request", () ->
                given(requestSpec)
                        .body(authData)
                .when()
                        .post("/register")

                .then()
                        .spec(responseStatusCodeSpec(400))
                        .extract().as(RegisterResponseModel.class)
        );
        step("Check response", ()->
                assertThat("Missing password").isEqualTo(response.getError())
        );
    }

    @Test
    @DisplayName("GET SINGLE <RESOURCE> NOT FOUND")
    void resourceNotFoundTest() {
        step("Make request", () ->
                given(requestSpec)
                .when()
                        .get("/unknown/23")
                .then()
                        .spec(responseStatusCodeSpec(404))
        );
    }

    @Test
    @DisplayName("POST CREATE")
    void createUsersTest() {
        UsersBodyModel authData = new UsersBodyModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        UsersResponseModel response = step("Make request", () ->
                given(requestSpec)
                        .body(authData)
                .when()
                        .post("/users")
                .then()
                        .spec(responseStatusCodeSpec(201))
                        .extract().as(UsersResponseModel.class)
        );
        step("Check response", () -> {
            assertAll(
                    () -> assertThat(authData.getName()).isEqualTo(response.getName()),
                    () -> assertThat(authData.getJob()).isEqualTo(response.getJob())
            );
        });
    }

    @Test
    @DisplayName("GET SINGLE USER")
    void singleUserTest() {
        UsersResponseModel response = step("Make request", () ->
                given(requestSpec)
                .when()
                        .get("/users/2")
                .then()
                        .spec(responseStatusCodeSpec(200))
                        .extract().as(UsersResponseModel.class)
        );
        step("Check response", ()-> {
            assertAll(
                    () -> assertThat(2).isEqualTo(response.getData().getId()),
                    () -> assertThat("janet.weaver@reqres.in").isEqualTo(response.getData().getEmail()),
                    () -> assertThat("Weaver").isEqualTo(response.getData().getLast_name()),
                    () -> assertThat("To keep ReqRes free, contributions towards server costs are appreciated!")
                            .isEqualTo(response.getSupport().getText())
            );
        });
    }

    @Test
    @DisplayName("PUT UPDATE")
    void updateUsersTest() {
        UsersBodyModel authData = new UsersBodyModel();
        authData.setName("morpheus");
        authData.setJob("zion resident");

        UsersResponseModel response = step("Make request", () ->
                given(requestSpec)
                        .body(authData)
                .when()
                        .put("/users/2")
                .then()
                        .spec(responseStatusCodeSpec(200))
                        .extract().as(UsersResponseModel.class)
        );
        step("Check response", () -> {
            assertAll(
                    () -> assertThat(authData.getName()).isEqualTo(response.getName()),
                    () -> assertThat(authData.getJob()).isEqualTo(response.getJob())
            );
        });
    }

    @Test
    @DisplayName("DELETE DELETE")
    void deleteTest() {
        step("Make request", () ->
                given(requestSpec)
                .when()
                        .delete("/users/2")
                .then()
                        .spec(responseStatusCodeSpec(204))
        );
    }
}
