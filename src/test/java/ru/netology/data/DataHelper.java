package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getValidAuthInfo_1stUser() {
        return new AuthInfo("Anton", "123");
    }

    public static AuthInfo getValidAuthInfo_2ndUser() {
        return new AuthInfo("Tatiana", "456");
    }

    public static AuthInfo getValidLoginAndEmptyPassword_1stUser() {
        return new AuthInfo("Anton", "");
    }

    public static AuthInfo getValidLoginAndEmptyPassword_2ndUser() {
        return new AuthInfo("Tatiana", "");
    }

    public static AuthInfo getInvalidLoginAndValidPassword_1stUser() {
        Faker faker = new Faker();
        return new AuthInfo(faker.name().firstName(), "123");
    }

    public static AuthInfo getInvalidLoginAndValidPassword_2ndUser() {
        Faker faker = new Faker();
        return new AuthInfo(faker.name().firstName(), "456");
    }

    public static AuthInfo getValidLoginAndInvalidPassword_1stUser() {
        Faker faker = new Faker();
        return new AuthInfo("Anton", faker.internet().password());
    }

    public static AuthInfo getValidLoginAndInvalidPassword_2ndUser() {
        Faker faker = new Faker();
        return new AuthInfo("Tatiana", faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static String getInvalidVerificationCode() {
        Faker faker = new Faker();
        return String.valueOf(faker.number());
    }
}