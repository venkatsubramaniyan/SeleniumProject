package com.qa.oc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductInfoPage {

	private WebDriver driver;
	
	By productImages=By.xpath("//ul[@class='thumbnails']//a[@class='thumbnail']");
	

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public int getProductImageCount()
	{
		return driver.findElements(productImages).size();
	}

}
