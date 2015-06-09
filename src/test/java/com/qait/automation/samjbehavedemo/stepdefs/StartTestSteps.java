package com.qait.automation.samjbehavedemo.stepdefs;

import static com.qait.automation.samjbehavedemo.utils.YamlReader.getData;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.sam.keywords.LoginPageActionKeyWords;

public class StartTestSteps{
	TestSessionInitiator test ;
	LoginPageActionKeyWords loginpage;
	public PageStepDef pagestep;
	
	
	public StartTestSteps() {
		this.test = new TestSessionInitiator();
		loginpage = new LoginPageActionKeyWords(test.getDriver());
		pagestep = new PageStepDef(test);
	}
	
	public StartTestSteps(TestSessionInitiator test) {
		this.test = test;
		loginpage = new LoginPageActionKeyWords(test.getDriver());
	}
	
	@BeforeStory
	public void beforeIHaveLaunchedTheApplication() {
		this.test.launchApplication(getData("app_url"));
	}
	
	@Given("I have launched the application")
	public void givenIHaveLaunchedTheApplication() {
		this.test.launchApplication(getData("app_url"));
	}

	@Given("I close the application")
	public void givenICloseTheApplication() {
		this.test.closeTestSession();
	}
	
	@AfterStory
	public void quitBrowserSession(){
		this.test.closeTestSession();
	}
}
