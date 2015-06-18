package com.qait.automation.samjbehavedemo.getstory;

import com.qait.automation.samjbehavedemo.utils.HttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author prashantshukla
 */
public class JiraSprintStoryFinder {

    private final String rapidViewPath;

    public JiraSprintStoryFinder(String rapidViewId) {
        this.rapidViewPath = Constants.JIRA_URL + Constants.JIRA_PATH_RAPIDVIEW + rapidViewId;
    }

    public List<String> getJiraSprintStories() {
        List<String> testStories = new ArrayList<>();
        String sprintJson;
        try {
            HttpClient client = new HttpClient();
            sprintJson = client.getHttpResponse(this.rapidViewPath).getEntity(String.class);
            JSONObject obj = new JSONObject(sprintJson);

            JSONArray issuesArray = obj.getJSONObject("issuesData").getJSONArray("issues");

            for (int i = 0; i < issuesArray.length(); i++) {

                JSONObject issueJson = new JSONObject(issuesArray.get(i).toString());

                if (Constants.ALLOWED_TYPE.contains(issueJson.getString("typeName").trim())) {
                    if (Constants.ALLOWED_STATUS.contains(issueJson.getString("statusName").trim())) {
                        //TODO: System.out.println("The Story: \'" + issueJson.getString("key") + "\' will be tested");
                        testStories.add(issueJson.getString("key"));
                    }
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(JiraSprintStoryFinder.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return testStories;
    }
}
