package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.Attach.*;
import static helpers.Attach.addVideo;

public class TestBase {
    @BeforeAll
    static void setup() {
        browser = System.getProperty("browser", "chrome");
        browserSize = System.getProperty("browserSize", "1920x1080");
        browserVersion = System.getProperty("browserVersion", "120.0");
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        RestAssured.basePath = "";
        pageLoadStrategy = "eager";
        remote = "https://user1:1234@" + System.getProperty("wdHost", "selenoid.autotests.cloud") + "/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void shutDown() {
        screenshotAs("Last screenshot");
        pageSource();
        browserConsoleLogs();
        addVideo();
        closeWebDriver();
    }
}
