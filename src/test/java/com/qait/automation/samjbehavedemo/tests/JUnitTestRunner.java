package com.qait.automation.samjbehavedemo.tests;

import com.qait.automation.samjbehavedemo.utils.StoryXMLParser;
import com.qait.automation.samjbehavedemo.utils.report.PublishJiraReport;

import java.util.Map;

import javax.xml.bind.JAXBException;

import org.junit.Test;

/**
 *
 * @author prashantshukla
 */
public class JUnitTestRunner {

	@Test
	public void testCommentCreation() {
		PublishJiraReport jirareport = new PublishJiraReport();

		System.out.println(jirareport.createJiraCommentJson("SLD-8"));

	}

	// @Test
	public void testStoryCount() throws JAXBException {

		StoryXMLParser xml = new StoryXMLParser("SLD-6");
		System.out.println("Scenario Count:- " + xml.getScenarioCount());
		for (int i = 0; i < xml.getScenarioCount(); i++) {

			Map<String, Integer> results = xml.getScenarioResult(xml
					.getScenario(i));
			System.out.println("Result: " + i + " - " + results);
			if (results.get("Pending") == 0) {
				if (results.get("Fail") > 0) {
					System.out.println("Scenario " + (i + 1) + ": "
							+ xml.getScenario(i).getAttributeNode("title")
							+ " = Failed");
					System.out.println(xml.getStepResults(xml.getScenario(i)));
				}
				if (results.get("Fail") == 0) {
					System.out.println("Scenario " + (i + 1) + ": "
							+ xml.getScenario(i).getAttributeNode("title")
							+ " = PASSED");

				}
				
			}
			if(results.get("Pending") > 0){
				System.out.println("There are steps that are still to be implemented by the automatino engineer");
				
			}
		}
	}
}
