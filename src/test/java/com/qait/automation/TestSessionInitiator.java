package com.qait.automation;

import static com.qait.automation.samjbehavedemo.utils.ConfigPropertyReader.getProperty;

import com.qait.automation.sam.keywords.LoginPageActionKeyWords;
import com.qait.automation.samjbehavedemo.utils.TakeScreenshot;

import static com.qait.automation.samjbehavedemo.utils.YamlReader.getYamlValue;
import static com.qait.automation.samjbehavedemo.utils.YamlReader.setYamlFilePath;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestSessionInitiator {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	/**
	 * Initiating the page objects
	 */
	public LoginPageActionKeyWords loginpage;

	public TakeScreenshot takescreenshot;

	public WebDriver getDriver() {
		return this.driver;
	}

//	private void _initPage() {
//		loginpage = new LoginPageActions(driver);
//	}

	/**
	 * Page object Initiation done
	 * 
	 * @param testname
	 */
	public TestSessionInitiator() {
		wdfactory = new WebDriverFactory();
		testInitiator();
	}

	public void testInitiator() {
		setYamlFilePath();
		_configureBrowser();
		//_initPage();
	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		// driver.manage().window().maximize();
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(getProperty("timeout")),
						TimeUnit.SECONDS);
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver",
				"seleniumserverhost", "timeout", "driverpath" };
		Map<String, String> config = new HashMap<>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("base_url"));
	}

	public void launchApplication(String base_url) {
		System.out.println("\nThe application url is :- " + base_url);
		System.out.println("The test browser is :- "
				+ _getSessionConfig().get("browser") + "\n");
		driver.manage().deleteAllCookies();
		driver.get(base_url);
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void closeBrowserSession() {
		System.out.println("\n");
		//driver.quit();
		driver.close();
	}

	public void closeTestSession() {
		closeBrowserSession();
	}
}
