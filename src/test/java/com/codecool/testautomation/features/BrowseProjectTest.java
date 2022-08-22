package com.codecool.testautomation.features;

import com.codecool.testautomation.utils.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codecool.testautomation.utils.DriverSingleton.quitDriver;

public class BrowseProjectTest {
    static final String PROJECTS_SOURCES = "/project_sources.csv";
    static BrowseProjectPage browseProjectPage;
    static LogInPage logInPage;

    @BeforeAll
    public static void setUp(){
        browseProjectPage = new BrowseProjectPage();
        logInPage = new LogInPage();
        logInPage.logInSuccessful();
    }

    @AfterAll
    public static void tearDown(){
        quitDriver();
    }

    @Test
    public void browseProject(){
        browseProjectPage.openUrl("/secure/BrowseProjects.jspa");
        browseProjectPage.waitUntilElementLoaded(browseProjectPage.mtpLink);
        Assertions.assertTrue(browseProjectPage.mtpLink.isDisplayed());
    }

    @ParameterizedTest
    @CsvFileSource(resources = PROJECTS_SOURCES, numLinesToSkip = 1)
    public void findProjects(String projectName, String exceptedResult){
        browseProjectPage.openUrl("/secure/BrowseProjects.jspa");
        browseProjectPage.findProject(projectName);
        boolean isPresent = browseProjectPage.projectIsPresent(exceptedResult);
        Assertions.assertTrue(isPresent);


    }
}
