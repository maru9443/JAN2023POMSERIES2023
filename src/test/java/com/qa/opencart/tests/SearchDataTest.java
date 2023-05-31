package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProducutDataProvider;
import com.qa.opencart.pojo.Product;

public class SearchDataTest extends BaseTest {
	
	@BeforeClass
	public void searchSetup()
	{
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
//	
//	@DataProvider(name="productData")
//	public Object[][] getProductTestData()
//	{
//		return new Object[][] {
//			{new Product("Macbook","MacBook Pro",4)},
//			{new Product("iMac","iMac",3)},
//			{new Product("Samsung","Samsung Galaxy Tab 10.1",7)},
//			{new Product("Samsung","Samsung SyncMaster 941BW",1)},	
//		};
//	}
		
		
	@Test(dataProvider="productData",dataProviderClass=ProducutDataProvider.class)
	public void searchProductResultCountTest(Product product)
	{
		resultsPage=accPage.dosearch(product.getSearchKey());
		Assert.assertTrue(resultsPage.getProductResultsCount()>0);
	}
	
	@Test(dataProvider="productData",dataProviderClass=ProducutDataProvider.class)
	public void searchPageTitleTest(Product product)
	{
		resultsPage=accPage.dosearch(product.getSearchKey());
		String actSearchTitle=resultsPage.getResultsPageTitle(product.getSearchKey());
		System.out.println("search page title is:"+actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - " + product.getSearchKey());
		
	}
	
	@Test(dataProvider="productData",dataProviderClass=ProducutDataProvider.class)
	public void selectProductTest(Product product)
	{
		resultsPage=accPage.dosearch(product.getSearchKey());
		productInfoPage=resultsPage.selectProduct(product.getProductName());
		String actProductHeaderName=productInfoPage.getProductHeaderName();
		System.out.println("actual product name: "+actProductHeaderName);
		Assert.assertEquals(actProductHeaderName, product.getProductName());	
	}
	@Test(dataProvider="productData",dataProviderClass=ProducutDataProvider.class)
	public void productImagesTest(Product product)
	{
		resultsPage=accPage.dosearch(product.getSearchKey());
		productInfoPage=resultsPage.selectProduct(product.getProductName());
		int actProductImagesCount=productInfoPage.getProductImagesCount();
		System.out.println("actual product images count: "+actProductImagesCount);
		Assert.assertEquals(actProductImagesCount, product.getProductImages());	
	}
	
}
	























































