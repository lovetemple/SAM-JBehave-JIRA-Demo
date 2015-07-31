/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.samjbehavedemo.utils.report;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.qait.automation.samjbehavedemo.getstory.Constants;
import com.qait.automation.samjbehavedemo.utils.FileHandler;
import com.qait.automation.samjbehavedemo.utils.HttpClient;
import com.qait.automation.samjbehavedemo.utils.StoryXMLParser;
import com.sun.jersey.api.client.UniformInterfaceException;

import static com.qait.automation.samjbehavedemo.utils.TestStates.*;

/**
 *
 * @author prashantshukla
 */
public class PublishJiraReport {

    private StoryXMLParser xml;

    private Map<String, String> storyStatus = new LinkedHashMap<String, String>();

    public PublishJiraReport() {
    }

    public String createJiraCommentJson(String jiraStoryId) {
        xml = new StoryXMLParser(jiraStoryId);
        int scenarioCount = xml.getScenarioCount();

        String jsonResultText = "{ \"body\": \"";
        jsonResultText = jsonResultText + "**Test Resuls For :- " + jiraStoryId
                + "**\\n\\n";

        for (int i = 0; i < scenarioCount; i++) {
            Element scenarioElement = xml.getScenario(i);
            String scenarioTitle = scenarioElement.getAttribute("title");

            Map<String, Integer> scenarioResult = xml
                    .getScenarioResult(scenarioElement);
            System.out.println("RESULT for " + jiraStoryId +":- "+scenarioResult);

            if (scenarioResult.get(PENDING) == 0) {
                
                if (scenarioResult.get(FAIL) == 0) {
                    jsonResultText = jsonResultText + "{color:green}"
                            + scenarioTitle + " - *PASSED*" + "{color}"
                            + "\\n\\n";
                    this.storyStatus.put(jiraStoryId + ":" + "Scenario " + i,
                            PASS);
                } else if (scenarioResult.get(FAIL) > 0) {
                    jsonResultText = jsonResultText + "{color:red}"
                            + scenarioTitle + " - *Failed*" + "{color}"
                            + "\\n\\n";

                    for (String step : xml.getStepResults(scenarioElement)
                            .values()) {

                        if (step.endsWith("- SUCCESSFUL")) {
                            jsonResultText = jsonResultText
                                    + "\\n{color:green}" + step + "{color}"
                                    + "\\n";
                        } else if (step.endsWith("- FAILED")) {
                            jsonResultText = jsonResultText + "\\t{color:red}"
                                    + step + "{color}" + "\\n";
                        } else if (step.endsWith("- NOTPERFORMED")) {
                            jsonResultText = jsonResultText + "\\n{color:blue}"
                                    + step + "{color}" + "\\n";
                        }
                    }
                    this.storyStatus.put(jiraStoryId + ":" + "Scenario " + i,
                            FAIL);
                }

            } else if (scenarioResult.get(PENDING) > 0){
                jsonResultText = jsonResultText + "{color:blue}" + scenarioTitle
                        + " - *PASSED*" + "{color}" + "\\n\\n";

                for (String step : xml.getStepResults(scenarioElement).values()) {

                    if (step.endsWith("- SUCCESSFUL")) {
                        jsonResultText = jsonResultText + "\\t{color:green}" + step
                                + "{color}" + "\\n";
                    } else if (step.endsWith("- FAILED")) {
                        jsonResultText = jsonResultText + "\\t{color:red}" + step
                                + "{color}" + "\\n";
                    } else if (step.endsWith("- NOTPERFORMED")) {
                        jsonResultText = jsonResultText + "\\t{color:blue}" + step
                                + "{color}" + "\\n";
                    } else if (step.endsWith("- PENDING")) {
                        jsonResultText = jsonResultText + "\\t{color:blue}" + step
                                + "{color}" + "\\n";
                    }
                }
                this.storyStatus.put(jiraStoryId + ":" + "Scenario " + i, PENDING);
            }
        }
        jsonResultText = jsonResultText + "\"}";
        System.out.println(jsonResultText);
        return jsonResultText;
    }

    public String pushJiraComment() {
        String response = "";
        HttpClient client = new HttpClient();
        for (String storyFilename : FileHandler.getFileNames(
                Constants.STORY_LOC, ".story")) {

            String jiraStoryId = storyFilename.split(".story")[0];
            String jiracommenturl = Constants.JIRA_URL + Constants.JIRA_ISSUE
                    + jiraStoryId + "/" + Constants.JIRA_COMMENT;

            // update JIRA only if execEnv is QA
            if (System.getProperty("execEnv", "dev").equalsIgnoreCase("qa")) {

                response = response
                        + client.postHttpResponse(jiracommenturl,
                                this.createJiraCommentJson(jiraStoryId))
                        .getEntity(String.class) + "\n";
                System.out.println("RESPONSE COMMENT:- " + response);
                moveJiraTicket(jiraStoryId, this.storyStatus);
            } else {
                System.out
                        .println("=================================================");
                System.out
                        .println("NOT UPDATING THE JIRA TICKET AS execEnv IS NOT QA");
                System.out
                        .println("=================================================");
            }
        }
        return response;
    }

    private String moveJiraTicket(String _jiraStoryId,
            Map<String, String> _storystatus) {
        String response = "";

        String jiratransitionurl = Constants.JIRA_URL + Constants.JIRA_ISSUE
                + _jiraStoryId + "/" + Constants.JIRA_TRANSITION;

        if (getstoryStatus(_storystatus.values()).equalsIgnoreCase(PENDING)) {
            System.out.println("NO JIRA ACTION");
            getChangeAssigneeJson("automation-script");
            return "";
        } else if (getstoryStatus(_storystatus.values()).equalsIgnoreCase(FAIL)) {

            try {
                response = new HttpClient().postHttpResponse(jiratransitionurl,
                        getReopenJiraTicketJson()).getEntity(String.class);

                getChangeAssigneeJson("rohitsingh");
            } catch (UniformInterfaceException e) {
            	//e.printStackTrace();
                getChangeAssigneeJson("rohitsingh");
            }
            System.out.println("\nREOPENING JIRA TICKET:- " + _jiraStoryId
                    + "\n");
            return response;
        } else if (getstoryStatus(_storystatus.values()).equalsIgnoreCase(PASS)) {
            try {
                response = new HttpClient().postHttpResponse(jiratransitionurl,
                        getCloseTicketJson()).getEntity(String.class);
                changeJiraAssignee(_jiraStoryId, "-1");
            } catch (UniformInterfaceException e) {
            	//e.printStackTrace();
            }
            System.out
                    .println("\nCLOSING JIRA TICKET:- " + _jiraStoryId + "\n");
            return response;
        }
        return response;
    }

    private String getstoryStatus(Collection<String> storyvalues) {
        String returnValue = PASS;
        for (String value : storyvalues) {
            if (value.equalsIgnoreCase(FAIL)) {
                return FAIL;
            } else if (value.equalsIgnoreCase(PENDING)) {
                returnValue = PENDING;
            }
        }
        return returnValue;
    }

    private String changeJiraAssignee(String _jiraStoryId, String jiraUserName) {
        String response = "";
        String jiraassgineeurl = Constants.JIRA_URL + Constants.JIRA_ISSUE
                + _jiraStoryId + "/" + Constants.JIRA_ASSIGNEE;

        try {
            response = new HttpClient().putHttpResponse(jiraassgineeurl,
                    getChangeAssigneeJson(jiraUserName))
                    .getEntity(String.class
                    );
        } catch (UniformInterfaceException e) {
        	//e.printStackTrace();
        }
        return response;
    }

    private String getChangeAssigneeJson(String jiraUsername) {
        return "{ \"name\":\"" + jiraUsername + "\"}";
    }

    private String getCloseTicketJson() {
        return "{ \"transition\": { \"id\": \"31\" }}";
    }

    private String getReopenJiraTicketJson() {
        return "{ \"transition\": { \"id\": \"11\" }}";

    }

}
