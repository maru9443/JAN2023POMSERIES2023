package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
private WebDriver driver;
private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) 
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	private By myaccount=By.linkText("My Account");
	private By logout=By.linkText("Logout");
	private By accHeaders=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchIcon=By.cssSelector("div#search button");
	
	
	public String getAcctPageTitle()
	{
		return eleUtil.waitForTitleIsAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.isElementDisplayed(logout);
	}
	
	public boolean isMyAccountLinkExist()
	{
		return eleUtil.isElementDisplayed(myaccount);
	}
	
	public List<String> getAccountPageHeadersList()
	{
		List<WebElement> headreslist=eleUtil.waitForElementsVisible(accHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headresValList=new ArrayList<String>();
		
		for(WebElement e:headreslist)
		{
			String text=e.getText();
			headresValList.add(text);
		}
		return headresValList; 
		
	}
	
	public ResultsPage dosearch(String searchTerm)
	{
		eleUtil.waitForElementVisible(search, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(search, searchTerm);
		eleUtil.doClick(searchIcon);
		
//		driver.findElement(search).sendKeys(searchTerm);
//		driver.findElement(searchIcon).click();
//		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
	}
	
	
	
	
	
	
	
	
	

}
