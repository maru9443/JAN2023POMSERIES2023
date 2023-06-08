package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	
	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	
	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotPwdlink=By.linkText("Forgotten Password");
	private By footerLinks=By.xpath("//footer//a");
	private By loginErrorMessage=By.cssSelector("div.alert.alert-danger.alert-dismissable");
	private By registerLink=By.linkText("Register");
	
	
	@Step("getting login page title.................")
	public String getLoginPageTitle() 
	{
		return eleUtil.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	@Step("getting login page URL........")
	public String getLoginPageURL() 
	{
		return eleUtil.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
		
	}
	
	
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.checkElementIsDisplayed(forgotPwdlink);
	}
	
	
	public List<String> getFooterLinksList()
	{
		List<WebElement> footerLinksList=eleUtil.waitForElementsVisible(footerLinks, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> footerTextList=new ArrayList<String>();
		for(WebElement e:footerLinksList)
		{
			String text=e.getText();
			footerTextList.add(text);
		}
		return footerTextList;
		}
	
	public AccountsPage doLogin(String userName, String pwd)
	{
		System.out.println("Correct credentials are :"+userName+":"+pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		//return the next landing page----Accounts Page---Page chaining model
		
		return new AccountsPage(driver);
	
	}
	
	
	public boolean doLoginWithWrongCredentials(String userName, String pwd)
	{
		System.out.println("Wrong credentials are :"+userName+":"+pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		//return the next landing page----Accounts Page---Page chaining model
		String errorMessage=eleUtil.doGetElementText(loginErrorMessage);
		System.out.println(errorMessage);
		
		if(errorMessage.contains(AppConstants.LOGIN_ERROR_MESSAGE))
		{
			return true;
		}
		return false;
	
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
