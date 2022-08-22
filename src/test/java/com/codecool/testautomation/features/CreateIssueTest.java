package com.codecool.testautomation.features;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codecool.testautomation.utils.DriverSingleton.quitDriver;

public class CreateIssueTest {
    static CreateIssuePage createIssuePage;
    static LogInPage logInPage;
    static IssuePage issuePage;
    static SearchIssuePage searchIssuePage;

    @BeforeAll
    public static void setUp(){
        createIssuePage = new CreateIssuePage();
        issuePage = new IssuePage();
        logInPage = new LogInPage();
        searchIssuePage = new SearchIssuePage();
        logInPage.logInSuccessful();
    }

    @AfterAll
    public static void tearDown(){
        quitDriver();
    }

    @Test
    public void createIssueSuccessful(){
        String project = "mtp";
        String type = "bug";
        String summary = "new issue test";
        createIssuePage.navigateCreate();
        createIssuePage.fillIssue(project,type ,summary);
        createIssuePage.createIssue();
        createIssuePage.navigateToNewIssue();
        String actualResult = issuePage.getSummaryValue();
        Assertions.assertEquals(summary,actualResult);
        issuePage.deleteIssue();
    }

    @Test
    public void createIssueEmptySummaryUnsuccessful(){
        String project = "mtp";
        String type = "bug";
        String summary = "";
        createIssuePage.navigateCreate();
        createIssuePage.fillIssue(project,type ,summary);
        createIssuePage.createIssue();
        Assertions.assertTrue(createIssuePage.summaryErrorMessageIsPresent());
    }

    @Test
    public void cancelIssueSuccessful(){
        String project = "mtp";
        String type = "bug";
        String summary = "new issue test";
        createIssuePage.navigateCreate();
        createIssuePage.fillIssue(project,type ,summary);
        createIssuePage.cancelCreateIssue();
        createIssuePage.openUrl("/issues/?jql=project%20%3D%20MTP%20AND%20text%20~%20%27new%20issue%20test%27");
        Assertions.assertTrue(searchIssuePage.noIssueFoundMessageIsPresent());
    }
}
