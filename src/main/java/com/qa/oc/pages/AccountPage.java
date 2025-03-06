package com.qa.oc.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {

	private WebDriver driver;

	By logoutLink = By.xpath("(//a[text()='Logout'])[2]");

	By headerText = By.xpath("//div[@id='content']/h2");

	By searchText = By.xpath("//input[@placeholder='Search']");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean logoutLinkExist() {
		return driver.findElement(logoutLink).isDisplayed();
	}

	public List<String> getHeadersText() {
		List<WebElement> elements = driver.findElements(headerText);
		List<String> headersText = new ArrayList<String>();
		for (WebElement e : elements) {
			String text = e.getText();

			headersText.add(text);
		}
		System.out.println(headersText);
		return headersText;
	}

	public boolean searchTextExist() {
		return driver.findElement(searchText).isDisplayed();
	}
	
	public SearchResultsPage doSearch(String productName)
	{
		driver.findElement(searchText).clear();
		driver.findElement(searchText).sendKeys(productName,Keys.ENTER);
		return new SearchResultsPage(driver);
	}

}
