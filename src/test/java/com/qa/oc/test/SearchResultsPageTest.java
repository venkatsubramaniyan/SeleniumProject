package com.qa.oc.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.oc.base.BaseTest;

public class SearchResultsPageTest extends BaseTest {

	@BeforeClass
	public void searchResultsSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
	}

	@DataProvider(name = "productImageData")
	public Object[][] getData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "imac", "iMac", 3 },
				{ "samsung", "Samsung SyncMaster 941BW", 1 } };
	}

	@Test(dataProvider = "productImageData")
	public void verifyProductImage(String product, String productName, int imageCount) {

		int actualImageCount = accountPage.doSearch(product).getProductInfo(productName).getProductImageCount();
		Assert.assertEquals(actualImageCount, imageCount);
	}

}
