package com.qait.automation.samjbehavedemo.utils;

import java.util.Arrays;
import java.util.List;

public final class Constants {

    public static final String JIRA_URL = System.getProperty("jirahost", "http://localhost:8080/");
    public static final String JIRA_USERNAME = "automation-script";
    public static final String JIRA_PASSWORD = "Qait@123";

    public static final String JIRA_PROJECT_ID = "SLD";

    public static final String JIRA_PATH_RAPIDVIEW = "rest/greenhopper/1.0/xboard/work/allData.json?rapidViewId=";
    public static final String JIRA_PATH_JBEHAVE_STORY = "rest/jbehave-for-jira/1.0/find/for-issue/";
    public static final String JIRA_ISSUE = "rest/api/2/issue/";
    
    public static final String JIRA_COMMENT = "comment/";
    public static final String JIRA_ASSIGNEE = "assignee/";
    public static final String JIRA_TRANSITION = "transitions/";

    public static final List<String> ALLOWED_STATUS = Arrays.asList("Open", "In Progress", "Reopened");
    public static final List<String> ALLOWED_TYPE = Arrays.asList("Story", "Bug", "Epic");

    public static final String STORY_LOC = "./src/test/resources/stories/"; //DO NOT CHANGE THIS. MIGHT BREAK JBEHAVE
    public static final String STORY_XML_LOC = "./target/jbehave/"; 
}
