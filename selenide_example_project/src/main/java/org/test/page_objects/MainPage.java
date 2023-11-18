package org.test.page_objects;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.element;

public class MainPage {

    private SelenideElement logInButton = element(Selectors.byXpath("//a[text()='Log in']"));
    private SelenideElement emailInput = element(Selectors.byXpath("//input[@id='username' and @type='email' and @name='username']"));
    private SelenideElement continueButton = element(Selectors.byText("Continue"));

    public void logIn() {
        logInButton.click();
    }

    public void inputEmail(String email) {
        emailInput.setValue(email);
    }

    public void clickContinueButton() {
        continueButton.click();
    }
}
