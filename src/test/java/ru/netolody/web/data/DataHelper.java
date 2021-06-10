package ru.netolody.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    @Value
    public static class UserDto {
        String id;
        String login;
        String password;
    }

    public static UserDto getUserDtoFirstPassword() {
        Faker user = new Faker(new Locale("en"));
        String id = user.idNumber().valid();
        String name = user.name().firstName();
        String password = "$2a$10$bdjJukJt6RkUWbKzedh0MOg/bl8aRjxUcHFiiUD5kBqfoF3xcDn7q";
        return new UserDto(id, name, password);
    }
}
