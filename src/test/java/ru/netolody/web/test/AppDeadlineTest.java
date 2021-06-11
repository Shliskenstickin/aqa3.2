package ru.netolody.web.test;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netolody.web.data.UserDto;
import ru.netolody.web.pages.Authorization;
import ru.netolody.web.sql.SqlHelper;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;

public class AppDeadlineTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    @SneakyThrows
    static void clean(){
        SqlHelper.cleanDefaultData();
    }

    @Test
    @SneakyThrows
    void shouldBeValidAuthorization() {
        UserDto user = new UserDto().getUserFirstPassword();
        SqlHelper.createUser(user);
        System.out.println(user.getId());

        val page = new Authorization().sigIn(user.getLogin(), user.getPassword());

        System.out.println(SqlHelper.getVerificationCode(user.getId()));

//        val page = new Authorization().sigIn(user.getLogin(), user.getPassword()).inputCode(user.getId());
    }
}