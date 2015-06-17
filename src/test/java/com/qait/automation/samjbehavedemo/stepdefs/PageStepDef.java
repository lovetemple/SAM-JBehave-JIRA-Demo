package com.qait.automation.samjbehavedemo.stepdefs;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.sam.keywords.LoginPageActionKeyWords;

import static com.qait.automation.samjbehavedemo.utils.YamlReader.getData;

public class PageStepDef extends LoginPageActionKeyWords {

    TestSessionInitiator test;

    public PageStepDef(TestSessionInitiator test) {
        super(test.getDriver());
        this.test = test;

    }

    @Given("I login to the application as {a|an} $user")
    public void givenILoginToTheApplicationAsAnUser(String user) {
        System.out.println("USER IS " + user + ":- "
                + getData("credentials." + user + ".username"));
        loginToTheApplicationUsingCredentials(
                getData("credentials." + user + ".username"),
                getData("credentials." + user + ".password"));
    }

    @Given("I am on {the|} Login page")
    public void givenIAmOnTheLoginPage() {
        verifyUserIsOnLoginPage();
    }

    @Given("I am on {the|} $pagename page")
    public void givenIAmOnHomePage(String pagename) {
        verifyPageTitleExact("SAM - " + pagename);
    }

    @When("I am on {the|} $pagename page")
    public void whenIAmOnHomePage(String pagename) {
        verifyPageTitleExact("SAM - " + pagename);
    }

    @When("I click Student View button")
    public void whenIClickStudentViewButton() {
        element("btn_studentView").click();
    }

    @Then("I land on student Activity Calendar page")
    public void thenILandOnActivityCalendarPage() {

    }

    @Then("I see the Student View button")
    public void thenISeeTheStudentViewButton() {
        verifyElementText("btn_studentView", "Student View");
    }

    @Then("Student View button is not visible")
    public void thenStudentViewButtonIsNotVisible() {

    }

    @When("I Navigate to Activities page")
    public void whenINavigateToActivitiesPage() {
    }

    @When("I navigate to Dropbox page")
    public void whenINavigateToDropboxPage() {
        element("lnk_dropbox").click();
    }

    @When("I click on dropbox navigation tab")
    public void whenIClickOnDropboxNavigationTab() {
        element("lnk_dropbox").click();
    }

    @Then("I see the dropbox navigation tab")
    public void thenISeeTheDropboxNavigationTab() {
        isElementDisplayed("lnk_dropbox");
    }

    @Then("I see the dropbox label")
    public void thenISeeTheDropboxLabel() {
    }

    @Then("I land on ${pagename} page")
    public void thenILandOnTargetPage(String pagename) {
        verifyPageTitleExact("SAM - " + pagename);
    }
}
