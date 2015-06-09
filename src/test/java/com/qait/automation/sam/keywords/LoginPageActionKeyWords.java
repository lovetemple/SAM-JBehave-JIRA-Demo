package com.qait.automation.sam.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

/**
 * 
 * @author prashantshukla
 */
public class LoginPageActionKeyWords extends GetPage {

	public LoginPageActionKeyWords(WebDriver driver) {
		super(driver, "LoginPage");
	}

	public void verifyUserIsOnLoginPage() {
		verifyPageTitleExact("SAM - Log In");
	}

	public void verifyUserIsOnHomePage() {
		verifyPageTitleExact("Home");
	}
	
	public void loginToTheApplicationUsingCredentials(String username,
			String password) {
		element("inp_username").sendKeys(username);
		element("inp_password").sendKeys(password);
		element("btn_login").click();
		handleAlert();
	}
}
