package ru.netolody.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import ru.netolody.web.sql.SqlHelper;

import static com.codeborne.selenide.Selenide.$;

public class Verification {
    private SelenideElement heading = $(".heading");
    private SelenideElement codeField = $("[data-test-id=\"code\"] input");
    private SelenideElement verify = $("[data-test-id=\"action-verify\"]");

    public Verification() {
        heading.shouldBe(Condition.visible).shouldHave(Condition.text("Интернет Банк"));
    }

    @SneakyThrows
    public Dashboard inputCode(String userId) {
        String code = SqlHelper.getVerificationCode(userId);
        codeField.setValue(code);
        verify.click();

        return new Dashboard();
    }
}
