package ru.netolody.web.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import ru.netolody.web.sql.SqlHelper;

import static com.codeborne.selenide.Selenide.$;

public class Verification {
    private SelenideElement codeField = $("[data-test-id=\"code\"] input");
    private SelenideElement verify = $("[data-test-id=\"action-verify\"]");

    @SneakyThrows
    public Dashboard inputCode(String userId){
        String code = SqlHelper.getVerificationCode(userId);
        codeField.setValue(code);
        verify.click();

        return new Dashboard();
    }
}
