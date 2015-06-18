package com.qait.automation.samjbehavedemo.tests.storyrunners;

import org.junit.Test;

import com.qait.automation.samjbehavedemo.utils.report.PublishJiraReport;

public class JiraReportTest {

    public JiraReportTest() {

    }

    @Test
    public void test() {
	System.setProperty("execEnv", "QA");
	PublishJiraReport jirareport = new PublishJiraReport();
	jirareport.pushJiraComment();
    }
}
