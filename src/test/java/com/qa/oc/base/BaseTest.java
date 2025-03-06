package com.qa.oc.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.oc.factory.DriverFactory;
import com.qa.oc.pages.AccountPage;
import com.qa.oc.pages.LoginPage;
import com.qa.oc.pages.RegistrationPage;
import com.qa.oc.pages.SearchResultsPage;

public class BaseTest {

	WebDriver driver;

	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountPage accountPage;
	protected SearchResultsPage searchResultsPage;
	protected RegistrationPage registrationPage;

	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {

		df = new DriverFactory();
		prop=df.initProp();
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);

	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
