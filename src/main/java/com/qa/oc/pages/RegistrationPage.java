package com.qa.oc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
	
	private WebDriver driver;
	
	private By firstNameField=By.id("input-firstname");
	
	private By lastNameField=By.id("input-lastname");
	
	private By emailField=By.id("input-email");
	
	private By phoneNumberField=By.id("input-telephone");
	
	private By passwordField=By.id("input-password");
	
	private By confirmPasswordField=By.id("input-confirm");	
	
	private By policyCheck=By.xpath("//input[@type='checkbox' and @name='agree']");
	
	private By accountSuccessText=By.xpath("//h1[text()='Your Account Has Been Created!']");
	
	private By logoutLink=By.xpath("(//a[text()='Logout'])[2]");
	
	private By registerLink=By.xpath("(//a[text()='Register'])[2]");
	
	private By newsLetterYes=By.xpath("//input[@type='radio' and @value='1' and @name='newsletter']");
	
	private By newsLetterNo=By.xpath("//input[@type='radio' and @value='0' and @name='newsletter']");	
	
	private By continueButton=By.xpath("//input[@value='Continue' and @class='btn btn-primary']");

	public RegistrationPage(WebDriver driver) {
		this.driver=driver;		
	}
	
	public boolean userRegistration(String firstName,String lastName,String email,String phoneNumber,String password, String subscription)
	{
		driver.findElement(firstNameField).sendKeys(firstName);		
		driver.findElement(lastNameField).sendKeys(lastName);		
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(phoneNumberField).sendKeys(phoneNumber);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(confirmPasswordField).sendKeys(password);	
		if(subscription.toLowerCase().trim()=="yes")
		{
			driver.findElement(newsLetterYes).click();
		}
		if(subscription.toLowerCase().trim()=="no")
		{
			driver.findElement(newsLetterNo).click();
		}
		
	
		driver.findElement(policyCheck).click();
		
		driver.findElement(continueButton).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		if(driver.findElement(accountSuccessText).isDisplayed())
		{
			driver.findElement(logoutLink).click();
			driver.findElement(registerLink).click();
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	

}
