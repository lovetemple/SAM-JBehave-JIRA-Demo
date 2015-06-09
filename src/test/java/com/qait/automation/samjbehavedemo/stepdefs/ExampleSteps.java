/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.samjbehavedemo.stepdefs;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

/**
 *
 * @author prashantshukla
 */
public class ExampleSteps {

    public ExampleSteps() {
    }

    @Given("I copy an existing section")
    public void givenICopyAnExistingSection() {
        // PENDING
    }

    @Given("I am logged into SAM application as a non-student user")
    public void givenIAmLoggedIntoSAMApplicationAsANonstudentUser() {
        // PENDING
    }

    @Given("I am on Sections tab")
    public void givenIAmOnSectionsTab() {
        Assert.assertTrue(false);
    }

    @When("I move to Section - Copy (Add New) page")
    public void whenIMoveToSectionCopyAddNewPage() {
        // PENDING
    }

    @Then("Copy (Add New) page should display term info from copied section")
    public void thenCopyAddNewPageShouldDisplayTermInfoFromCopiedSection() {
        // PENDING
    }
}
