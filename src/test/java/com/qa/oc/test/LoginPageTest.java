package com.qa.oc.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.oc.base.BaseTest;
import com.qa.oc.constant.AppConstant;
import com.qa.oc.error.AppError;
import com.qa.oc.pages.AccountPage;

public class LoginPageTest extends BaseTest {

	@Test(priority = 4)
	public void loginTest() {
		boolean searchTextExist = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"))
				.searchTextExist();
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
		Assert.assertTrue(searchTextExist);
	}

	@Test(priority = 3)
	public void verifyLoginTitle() {
		String loginTitle = loginPage.getLoginTitle();
		Assert.assertEquals(loginTitle, AppConstant.LOGIN_PAGE_TITLE, AppError.LOGIN_PAGE_NOT_FOUND);
	}

	@Test(priority = 2)
	public void verifyForgotPwdLinkExist() {
		boolean forgotPwdLinkExist = loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(forgotPwdLinkExist);
	}

	@Test(priority = 1)
	public void verifyLoginPageUrl() {
		String url = loginPage.getUrl();
		Assert.assertTrue(url.contains(AppConstant.LOGIN_URL_FRACTION_VALUE));
	}

}
