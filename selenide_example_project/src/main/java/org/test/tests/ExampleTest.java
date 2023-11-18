package org.test.tests;

import com.codeborne.selenide.Selenide;
import org.test.page_objects.MainPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ExampleTest {
    final String AUT_URL = "https://trello.com/home";
    MainPage mainPage = new MainPage();

    @Test
    public void openMainPageAndLogin() {
        open(AUT_URL);
        mainPage.logIn();
        mainPage.inputEmail("lijija3622@dabeixin.com");
        Selenide.sleep(5000);
        mainPage.clickContinueButton();
        element("h5")
                .shouldBe(visible)
                .shouldHave(text("Check your inbox to log in"));
    }
}
