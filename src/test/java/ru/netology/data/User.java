package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class User {
    private User() {
    }

    @SneakyThrows
    public static DataHelper.VerificationCodePage getValidVerificationCode() {
        var authCode = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();

        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scr_mysql", "scriper", "123")) {
            var verificationCode = runner.query(conn, authCode, new ScalarHandler<>());
            return new DataHelper.VerificationCodePage((String) verificationCode);
        }
    }

    @SneakyThrows
    public static void cleanData() {
        var authCodesClean = "DELETE FROM auth_codes";
        var cardsClean = "DELETE FROM cards";
        var usersClean = "DELETE FROM users";
        var runner = new QueryRunner();

        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scr_mysql", "scriper", "123")) {
            runner.update(conn, authCodesClean);
            runner.update(conn, cardsClean);
            runner.update(conn, usersClean);
        }
    }
}
