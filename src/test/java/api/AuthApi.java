package api;

import models.LoginRequestModel;
import models.LoginResponseModel;
import tests.TestBase;

import static api.spec.SpecAllTests.requestSpecification;
import static io.restassured.RestAssured.given;
import static testdata.TestData.password;
import static testdata.TestData.username;

public class AuthApi extends TestBase {

    public static LoginResponseModel authorizeRequest() {
        LoginRequestModel authRequest = new LoginRequestModel();
        authRequest.setUserName(username);
        authRequest.setPassword(password);

        return given(requestSpecification)
                .body(authRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .extract().as(LoginResponseModel.class);
    }
}
