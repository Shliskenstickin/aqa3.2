package ru.netolody.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import ru.netolody.web.sql.SqlHelper;

import static com.codeborne.selenide.Selenide.$;

public class Verification {
    private SelenideElement heading = $(".heading");
    private SelenideElement codeField = $("[data-test-id=\"code\"] input");
    private SelenideElement verify = $("[data-test-id=\"action-verify\"]");

    public Verification() throws InterruptedException {
        heading.shouldBe(Condition.visible).shouldHave(Condition.text("Интернет Банк"));
        Thread.sleep(500);
    }

    @SneakyThrows
    public Dashboard inputCode(String userId) {
        String code = SqlHelper.getVerificationCode(userId);
        System.out.println("code is " + code);
        codeField.setValue(code);
        verify.click();

        return new Dashboard();
    }
}
