package ru.netolody.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Dashboard {
    private SelenideElement heading = $("[data-test-id=\"dashboard\"]");

    public Dashboard() {
        heading.shouldBe(Condition.visible);
    }
}
