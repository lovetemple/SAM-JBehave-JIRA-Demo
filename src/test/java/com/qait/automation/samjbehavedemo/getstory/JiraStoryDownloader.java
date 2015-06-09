/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.samjbehavedemo.getstory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.qait.automation.samjbehavedemo.utils.Constants;

/**
 *
 * @author prashantshukla
 */
public class JiraStoryDownloader {

    private static final String storyLoc = Constants.STORY_LOC;
    private final JiraJbehaveStoryFinder jiraStory;
    private final String jiraStoryId;

    public JiraStoryDownloader(String jiraStoryId) {
        this.jiraStoryId = jiraStoryId;
        this.jiraStory = new JiraJbehaveStoryFinder(this.jiraStoryId);
    }

    public void storeJiraStoryLocally() {
        storeJiraStoryLocally(getJiraStory());
    }

    public void storeJiraStoryLocally(String jiraStory) {
        File storiesDir = new File(storyLoc);

        if (!storiesDir.exists()) {
            storiesDir.mkdir();
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(storyLoc + this.jiraStoryId + ".story"), "utf-8"))) {
            writer.write(jiraStory);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            Logger.getLogger(JiraStoryDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(JiraStoryDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(JiraStoryDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String getJiraStory() {
        return this.jiraStory.getStory();
    }

}
