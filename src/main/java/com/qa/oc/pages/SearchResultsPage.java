package com.qa.oc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
	
	private WebDriver driver;
	
	private By productText=By.xpath("//div[@class='caption']//a");

	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public int getProductCount()
	{
		return driver.findElements(productText).size();
	}
	
	public ProductInfoPage getProductInfo(String productName)
	{
		driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
		return new ProductInfoPage(driver);
	}
	
	

}
