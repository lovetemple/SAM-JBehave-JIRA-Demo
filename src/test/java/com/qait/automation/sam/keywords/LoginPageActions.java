package com.qait.automation.sam.keywords;

import com.qait.automation.getpageobjects.GetPage;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author prashantshukla
 */
public class LoginPageActions extends GetPage {

    public LoginPageActions(WebDriver driver) {
        super(driver, "LoginPage");
    }

    public void verifyUserIsOnLoginPage() {
        verifyPageTitleExact();
    }

    public void loginToTheApplicationUsingCredentials(String username, String password) {
        element("inp_username").clear();
        element("inp_username").sendKeys(username);

        element("inp_password").clear();
        element("inp_password").sendKeys(password);

        element("btn_singin").click();
    }
}
