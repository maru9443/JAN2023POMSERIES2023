package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class LoginPageTest extends BaseTest {
	
	
	@Test
	public void loginPageTitleTest()
	{
		String actTitel=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitel, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void ForgotPwdLinkExist()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		
	}
	
	@Test
	public void loginPageUrlTest()
	{
		String acturl=loginPage.getLoginPageURL();
		Assert.assertTrue(acturl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void loginTest()
	{
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
		//Assert.assertTrue(accPage.getAcctPageTitle().equals("My Account"));
	}
	

}
