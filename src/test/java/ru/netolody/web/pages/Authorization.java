package ru.netolody.web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Authorization {

    private SelenideElement loginField = $("[data-test-id=\"login\"] input");
    private SelenideElement passwordField = $("[data-test-id=\"password\"] input");
    private SelenideElement sigIn = $("[data-test-id=\"action-login\"]");

    public Verification sigIn(String login, String password) throws InterruptedException {
        loginField.setValue(login);
        passwordField.setValue(password);
        sigIn.click();
        return new Verification();
    }
}
