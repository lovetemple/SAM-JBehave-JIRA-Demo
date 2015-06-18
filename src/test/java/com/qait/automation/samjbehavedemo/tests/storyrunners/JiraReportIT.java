package com.qait.automation.samjbehavedemo.tests.storyrunners;

import org.junit.Test;

import com.qait.automation.samjbehavedemo.utils.report.PublishJiraReport;

public class JiraReportIT {
    @Test
    public void test() {
	PublishJiraReport jirareport = new PublishJiraReport();
	jirareport.pushJiraComment();
    }
}
