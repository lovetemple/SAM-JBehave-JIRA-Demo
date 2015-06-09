package com.qait.automation.samjbehavedemo.tests;

import org.junit.Test;

import com.qait.automation.samjbehavedemo.utils.report.PublishJiraReport;

public class JiraReportIT {

	public JiraReportIT() {

	}

	@Test
	public void test() {
		PublishJiraReport jirareport = new PublishJiraReport();
		System.out.println(jirareport.pushJiraComment());
	}
}
