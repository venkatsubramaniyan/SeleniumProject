package com.qa.oc.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.oc.base.BaseTest;
import com.qa.oc.util.CSVUtil;
import com.qa.oc.util.ExcelUtil;
import com.qa.oc.util.StringUtil;

public class RegistrationPageTest extends BaseTest {

	@DataProvider(name = "userRegistrationData")
	public Object[][] getData() {
		return new Object[][] { { "vieene", "vieen",  "1234", "abcde", "yes" },
				{ "vinee", "vine",  "12345", "abcdee", "no" },
				{ "vinete", "vinet",  "123456", "abcdeee", "yes" } };
				
	}
	
	
	@DataProvider(name="userRegistrationDataFromExcel")
	public Object[][] getDataFromExcel()
	{
		return ExcelUtil.getTestData("Opencart","Register");
	}
	
	@DataProvider(name="userRegistrationDataFromCSV")
	public Object[][] getDataFromCSV()
	{
		return CSVUtil.csvData("Register");
	}
	
	

	@Test(dataProvider = "userRegistrationDataFromCSV")
	public void verifyUserRegistration(String firstName, String lastName, String phoneNo, String password,
			String subscription)

	{
		boolean userRegistration = loginPage.navigateToRegistration().userRegistration(firstName, lastName, StringUtil.getEmail(),
				phoneNo, password, subscription);
		Assert.assertTrue(userRegistration);

	}

}
