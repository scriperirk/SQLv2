package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import ru.netology.data.DataHelper;
import ru.netology.data.User;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest {

    @AfterAll
    static void cleanData() {
        User.cleanData();
    }

    @Test
    void shouldAuthorizeSuccessfully_User1() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo_User1();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verifyInfo = User.getValidVerificationCode();
        var dashboardPage = verificationPage.validCode(verifyInfo);
        dashboardPage.login();
    }

    @Test
    void shouldAuthorizeSuccessfully_User2() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo_User2();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verifyInfo = User.getValidVerificationCode();
        var dashboardPage = verificationPage.validCode(verifyInfo);
        dashboardPage.login();
    }

    @Test
    void shouldNotValidateWithBothEmptyFields() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.emptyAuthInfo();
    }

    @Test
    void shouldNotValidateWithEmptyPassword_User1() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndEmptyPassword_User1();
        loginPage.validLoginAndEmptyPassword(authInfo);
    }

    @Test
    void shouldNotValidateWithEmptyPassword_User2() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndEmptyPassword_User2();
        loginPage.validLoginAndEmptyPassword(authInfo);
    }

    @Test
    void shouldNotAuthorizeWithInvalidPassword_User1() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndInvalidPassword_User1();
        loginPage.invalidAuthInfo(authInfo);
    }

    @Test
    void shouldNotAuthorizeWithInvalidPassword_User2() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndInvalidPassword_User2();
        loginPage.invalidAuthInfo(authInfo);
    }

    @Test
    void shouldNotAuthorizeWithInvalidLogin_User1() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getInvalidLoginAndValidPassword_User1();
        loginPage.invalidAuthInfo(authInfo);
    }

    @Test
    void shouldNotAuthorizeWithInvalidLogin_User2() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getInvalidLoginAndValidPassword_User2();
        loginPage.invalidAuthInfo(authInfo);
    }

    @Test
    void shouldNotLoginWithEmptyCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo_User1();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        verificationPage.emptyCode();
    }

    @Test
    void shouldNotLoginWithInvalidCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo_User2();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verifyInfo = DataHelper.getInvalidVerificationCode();
        verificationPage.invalidCode(verifyInfo);
    }

    @Test
    void shouldBlockAfterThreeAttemptsWithInvalidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndInvalidPassword_User1();
        loginPage.blockSystem(authInfo);
    }
}
