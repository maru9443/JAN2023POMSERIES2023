package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest{
	
	
	@DataProvider
	public Object[][] incorrectLoginTestData()
	{
		return new Object[][] {
			{"auto123@gmail.com","12345"},
			{"test@gmail.com","12345"},
			{"auto123@@gmail.com","test12"},
			{"auto123@gmail","@##"},
			
		};
		
	}
	
	
	
	@Test(dataProvider="incorrectLoginTestData")
	public void loginWithWrongCredentialsTest(String userName, String Password)
	{
		Assert.assertTrue(loginPage.doLoginWithWrongCredentials(userName, Password));
	}
	

}
