package ru.netolody.web.sql;

import lombok.val;
import ru.netolody.web.data.UserDto;

import java.sql.DriverManager;
import java.sql.SQLException;


public final class SqlHelper2 {

    public static void createUser(UserDto user) throws SQLException {

        val dataSQL = "INSERT INTO users(id, login, password) VALUES (?, ?, ?);";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                val dataStmt = conn.prepareStatement(dataSQL);
        ) {
            dataStmt.setString(1, user.getId());
            dataStmt.setString(2, user.getLogin());
            dataStmt.setString(3, user.getPassword());
            dataStmt.executeUpdate();
        }
    }
}
