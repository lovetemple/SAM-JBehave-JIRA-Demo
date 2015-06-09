package com.qait.automation.samjbehavedemo.getstory;

import com.qait.automation.samjbehavedemo.utils.HttpClient;
import com.qait.automation.samjbehavedemo.utils.JsonParser;
import com.sun.jersey.api.client.ClientResponse;

/**
 *
 * @author prashantshukla
 */
public class JiraJbehaveStoryFinder {

    private final String jiraStoryResource;
    private static final String STORY_JSON_KEY = "payload:asString";

    public JiraJbehaveStoryFinder(String jiraStoryId) {
        this.jiraStoryResource = Constants.JIRA_URL + Constants.JIRA_PATH_JBEHAVE_STORY + Constants.JIRA_PROJECT_ID + "/" + jiraStoryId;
    }

    public String getStoryUrl() {
        return this.jiraStoryResource;
    }

    public String getStory() {
        HttpClient httpclient = new HttpClient();

        ClientResponse response = httpclient.getHttpResponse(this.jiraStoryResource);

        return new JsonParser().getJsonValue(response.getEntity(String.class), STORY_JSON_KEY);
    }
}
