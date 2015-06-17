/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.samjbehavedemo.utils.report.email;

/**
 *
 * @author prashant.shukla
 */
public class Emailer {
    
    public void send_email(String jiraStoryId, String emailId){
        System.out.println("[INFO: ]" + jiraStoryId + " does not have JBEHAVE story written yet. Mail sent to " + emailId);
    }
}
