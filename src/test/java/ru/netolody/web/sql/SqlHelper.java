package ru.netolody.web.sql;

import lombok.Data;
import lombok.val;
import ru.netolody.web.data.DataHelper;

import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class SqlHelper {

    public void createUser() throws SQLException {
        val dataSQL = "INSERT INTO users(id, login, password) VALUES (?, ?, ?);";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                val dataStmt = conn.prepareStatement(dataSQL);
        ) {
            dataStmt.setString(1, DataHelper.getUserDtoFirstPassword().getId());
            dataStmt.setString(2, DataHelper.getUserDtoFirstPassword().getLogin());
            dataStmt.setString(3, DataHelper.getUserDtoFirstPassword().getPassword());
            dataStmt.executeUpdate();
        }
    }
}
