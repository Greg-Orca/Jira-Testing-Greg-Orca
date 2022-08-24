package com.codecool.testautomation.features;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codecool.testautomation.utils.DriverSingleton.quitDriver;

public class EditableIssueTest {
    final String EDIT_ISSUES_RESOURCE = "/edit_issues_resource.csv";
    static EditIssuePage editIssuePage;
    static LogInPage logInPage;

    @BeforeAll
    public static void setUp() {
        editIssuePage = new EditIssuePage();
        logInPage = new LogInPage();
        logInPage.logInSuccessful();
    }

    @AfterAll
    public static void tearDown() {
        quitDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = EDIT_ISSUES_RESOURCE, numLinesToSkip = 1)
    public void editableIssue(String issueUrl, String expected){
        editIssuePage.openUrl(issueUrl);
        boolean editPresent = editIssuePage.elementIsPresent(editIssuePage.editButton);
        Assertions.assertTrue(editPresent&&expected.equals("true"));
    }
}
