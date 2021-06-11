package ru.netolody.web.sql;

import lombok.val;
import ru.netolody.web.data.UserDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlHelper {

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
    }

    public static void createUser(UserDto user) throws SQLException {

        val dataSQL = "INSERT INTO users(id, login, password) VALUES (?, ?, ?);";

        try (
                val conn = connect();
                val dataStmt = conn.prepareStatement(dataSQL);
        ) {
            dataStmt.setString(1, user.getId());
            dataStmt.setString(2, user.getLogin());
            dataStmt.setString(3, user.getEncryptedPassword());
            dataStmt.executeUpdate();
        }
    }

    public static void cleanDefaultData() throws SQLException {
        val deleteCards = "DELETE FROM cards WHERE number = '5559 0000 0000 0002' OR number = '5559 0000 0000 0001';";
        val deleteUsers = "DELETE FROM users WHERE login = 'petya' OR login = 'vasya';";
        try (
                val conn = connect();
                val dataStmt = conn.createStatement();
        ) {
            dataStmt.executeUpdate(deleteCards);
            dataStmt.executeUpdate(deleteUsers);
        }
    }

    public static String getVerificationCode(String id) throws SQLException {
        val selectCode = "SELECT code FROM auth_codes WHERE user_id = '" + id + "';";
        try (
                val conn = connect();
                val dataStmt = conn.createStatement()
        ) {
            try (val rs = dataStmt.executeQuery(selectCode)) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        return "FUCK!";
    }
}
