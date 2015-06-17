package com.qait.automation.samjbehavedemo.tests;

import com.qait.automation.samjbehavedemo.getstory.JiraStoryDownloader;
import com.qait.automation.samjbehavedemo.utils.FileHandler;

import org.junit.Test;

/**
 *
 * @author prashantshukla
 */
public class JUnitTestRunner {

    @Test
    public void testNoJbehavStory() {

        FileHandler.cleanStoryLocation();

        JiraStoryDownloader jirastory = new JiraStoryDownloader("SLD-4");

        jirastory.storeJiraStoryLocally();
    }
}
