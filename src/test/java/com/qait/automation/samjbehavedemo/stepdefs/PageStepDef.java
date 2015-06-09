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

	@Given("I am on {the} Login page")
	public void givenIAmOnTheLoginPage() {
		verifyUserIsOnLoginPage();
	}
	
	@Given("I am on Home Page")
	public void givenIAmOnHomePage() {
	  // PENDING
	}
	
	@When("I am on {the|} Home page")
	public void whenIAmOnHomePage() {
	  verifyPageTitleExact("SAM - Home");
	}
	
	@When("I click Student View button")
	public void whenIClickStudentViewButton() {
		element("btn_studentView").click();
	}
	
	@Then("I land on Activity Calendar page")
	public void thenILandOnActivityCalendarPage() {

	}

	@Then("I see the Student View button")
	public void thenISeeTheStudentViewButton() {
		verifyElementText("btn_studentView", "Student View");
	}
	
	@When("I am on Activity Calendar page")
	public void whenIAmOnActivityCalendarPage() {
	}

	@Then("Student View button is not visible")
	public void thenStudentViewButtonIsNotVisible() {
		
	}

	@When("I Navigate to Activities page")
	public void whenINavigateToActivitiesPage() {
	}


	@Given("I am on Activity Calendar page")
	public void givenIAmOnActivityCalendarPage() {
	}

	@When("I navigate to Dropbox Page")
	public void whenINavigateToDropboxPage() {
	  element("lnk_dropbox").click();
	}

	@Then("I land on Sections Page")
	public void thenILandOnSectionsPage() {
		verifyPageTitleExact("SAM - Section");
	}


	

}
