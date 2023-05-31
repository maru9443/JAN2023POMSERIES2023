package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By resultsProduct=By.cssSelector("div.product-layout.product-grid");

	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	
	public String getResultsPageTitle(String SearchKey)
	{
		return eleUtil.waitForTitleIsAndCapture(SearchKey, 5);
	}
	
	public int getProductResultsCount()
	{
		int resultCount=eleUtil.waitForElementsVisible(resultsProduct, 10).size();
		System.out.println("product serach result count"+resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		By productNameLocator=By.linkText(productName);
		eleUtil.doClick(productNameLocator);
		return new ProductInfoPage(driver);
	}

}
