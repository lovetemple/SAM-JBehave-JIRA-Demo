package com.qait.automation.samjbehavedemo.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author prashantshukla
 */
public class JsonParser {

    public String getJsonValue(String jsonString, String jsonKey) {
        String jsonValue = null;
        try {
            JSONObject obj = new JSONObject(jsonString);
            jsonValue = obj.getString(jsonKey.split(":")[0]);
            if (jsonKey.contains(":")) {
                String[] splitString = jsonKey.split(":", 2);
                jsonValue = getJsonValue(jsonValue, splitString[1]);
            }
        } catch (JSONException ex) {
            Logger.getLogger(JsonParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonValue;
    }
}
