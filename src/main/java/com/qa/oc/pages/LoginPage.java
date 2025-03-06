package com.qa.oc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	private By emailField = By.id("input-email");

	private By passwordField = By.id("input-password");

	private By loginButton = By.xpath("//input[@class='btn btn-primary']");

	private By forgotPwdLink = By.xpath("(//a[text()='Forgotten Password'])[1]");
	
	private By registerLink=By.xpath("(//a[text()='Register'])[2]");

	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}

	public AccountPage doLogin(String userName, String password) {

		driver.findElement(emailField).sendKeys(userName);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
		return new AccountPage(driver);

	}

	public String getLoginTitle() {

		return driver.getTitle();
	}

	public String getUrl() {

		return driver.getCurrentUrl();
	}

	public boolean isForgotPwdLinkExist() {
		return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	public RegistrationPage navigateToRegistration()
	{
		driver.findElement(registerLink).click();
		return new RegistrationPage(driver);
	}

}
