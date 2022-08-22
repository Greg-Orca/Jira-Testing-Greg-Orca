package com.codecool.testautomation.features;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codecool.testautomation.utils.DriverSingleton.quitDriver;

public class LogInTest {
    static LogInPage logInPage;
    private static final String FAIL_TEST_DATA_SOURCE = "/login_fail.csv";

    @BeforeAll
    public static void setUp(){
        logInPage = new LogInPage();
        logInPage.openUrl("/login.jsp");
    }

    @AfterAll
    public static void tearDown(){
        logInPage.logInSuccessful();
//        quitDriver();
    }

    @Test
    public void logInSuccessful(){
        logInPage.fillUsernameAndPassword(logInPage.USER_NAME, logInPage.PASSWORD);
        logInPage.logIn();
//        Assertions.assertEquals("Auto Tester "+ LogIn.keyCode, lp.profileName.getText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = FAIL_TEST_DATA_SOURCE, numLinesToSkip = 1)
    void loginFail(String username, String password, String expected){
//        if (username==null){
//            username = "";
//        }
//        else if (password==null){
//            password = "";
//        }
        System.out.println(username);
        logInPage.fillUsernameAndPassword(username,password);
        logInPage.logIn();
        String actual = logInPage.logInErrorMessage.getText();
        Assertions.assertEquals(expected, actual);

    }

}
