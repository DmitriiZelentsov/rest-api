package helpers;

import org.openqa.selenium.Cookie;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static api.AuthApi.authorizeRequest;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        LoginResponseModel authResponse = authorizeRequest();

        open("/favicon.png");
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("userName", authResponse.getUsername()));
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
    }
}
