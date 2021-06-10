package ru.netolody.web.test;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netolody.web.sql.SqlHelper;

import java.sql.DriverManager;

public class AppDeadlineTest {
    @BeforeEach
    @SneakyThrows
    void setUp() {
        SqlHelper dbUser = new SqlHelper();
        dbUser.createUser();
    }

    @Test
    @SneakyThrows
    void stubTest() {
        val countSQL = "SELECT COUNT(*) FROM users;";
        val cardsSQL = "SELECT id, number, balance_in_kopecks FROM cards WHERE user_id = ?;";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                val countStmt = conn.createStatement();
                val cardsStmt = conn.prepareStatement(cardsSQL);
        ) {
            try (val rs = countStmt.executeQuery(countSQL)) {
                if (rs.next()) {
                    // выборка значения по индексу столбца (нумерация с 1)
                    val count = rs.getInt(1);
                    // TODO: использовать
                    System.out.println(count);
                }
            }

            cardsStmt.setInt(1, 1);
            try (val rs = cardsStmt.executeQuery()) {
                while (rs.next()) {
                    val id = rs.getInt("id");
                    val number = rs.getString("number");
                    val balanceInKopecks = rs.getInt("balance_in_kopecks");
                    // TODO: сложить всё в список
                    System.out.println(id + " " + number + " " + balanceInKopecks);
                }
            }
        }
    }
}