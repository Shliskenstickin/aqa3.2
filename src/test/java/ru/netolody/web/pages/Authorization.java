package ru.netolody.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Authorization {

    private SelenideElement loginField = $("[data-test-id=\"login\"] input");
    private SelenideElement passwordField = $("[data-test-id=\"password\"] input");
    private SelenideElement sigIn = $("[data-test-id=\"action-login\"]");
    private SelenideElement errorNotification = $("[data-test-id=\"error-notification\"]");

    public Verification sigIn(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        sigIn.click();
        return new Verification();
    }

    public void invalidSigIn(String login){
        loginField.setValue(login);
        passwordField.setValue(login);
        sigIn.click();
        errorNotification.shouldBe(Condition.visible);
    }
}
