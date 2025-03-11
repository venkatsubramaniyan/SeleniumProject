package com.qa.oc.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.oc.base.BaseTest;
import com.qa.oc.constant.AppConstant;


public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accountSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyLogoutLink() {
		boolean logoutLinkExist = accountPage.logoutLinkExist();
		Assert.assertTrue(logoutLinkExist);
	}

	@Test(priority = 2)
	public void verifyAccountPageHeader() {
		List<String> actualHeadersList = accountPage.getHeadersText();
		Assert.assertEquals(actualHeadersList, AppConstant.ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@DataProvider(name = "searchResultsData")
	public Object[][] getData() {
		return new Object[][] { { "macbook", 3}, { "imac", 1 }, { "samsung", 2 } };
	}

	@Test(priority = 3,dataProvider = "searchResultsData")
	public void verifyProductCount(String productName, int productCount) {
		 int actualProductCount = accountPage.doSearch(productName).getProductCount();
		 Assert.assertEquals(actualProductCount, productCount);
		 
	}
		 

}
