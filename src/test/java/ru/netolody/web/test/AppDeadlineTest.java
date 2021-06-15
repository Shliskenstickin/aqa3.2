package ru.netolody.web.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netolody.web.data.DataGenerator;
import ru.netolody.web.pages.Authorization;
import ru.netolody.web.sql.SqlHelper;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class AppDeadlineTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clean() {
        SqlHelper.cleanDefaultData();
    }

    @Test
    void shouldBeValidAuthorization() {
        DataGenerator.UserDto user = new DataGenerator().getUserFirstPassword();
        SqlHelper.createUser(user);

        new Authorization().sigIn(user.getLogin(), user.getPassword()).inputCode(SqlHelper.getVerificationCode(user.getId()));
    }

    @Test
    void shouldBlockUserAfterInvalidPassword() {
        DataGenerator.UserDto user = new DataGenerator().getUserFirstPassword();
        SqlHelper.createUser(user);

        new Authorization().invalidSigIn(user.getLogin());
        closeWindow();
        setUp();
        new Authorization().invalidSigIn(user.getLogin());
        closeWindow();
        setUp();
        new Authorization().invalidSigIn(user.getLogin());

        String status = SqlHelper.getUserStatus(user.getId());

        Assertions.assertNotEquals("active", status);
    }
}