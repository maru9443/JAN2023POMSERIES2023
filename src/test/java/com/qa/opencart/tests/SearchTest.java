package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProducutDataProvider;

public class SearchTest extends BaseTest{
	
	
	@BeforeClass
	public void searchSetup()
	{
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
//	@DataProvider
//	public Object[][] getProductSearchKeyData()
//	{
//		return new Object[][] {
//			{"Macbook"},
//			{"iMac"},
//			{"Samsung"},
//			
//		};
//	}
//	
		
	@Test(dataProvider="productDataWithSearchKey",dataProviderClass=ProducutDataProvider.class)
	public void searchProductResultCountTest(String searchkey)
	{
		resultsPage=accPage.dosearch(searchkey);
		Assert.assertTrue(resultsPage.getProductResultsCount()>0);
	}
	
	@Test(dataProvider="productDataWithSearchKey",dataProviderClass=ProducutDataProvider.class)
	public void searchPageTitleTest(String searchkey)
	{
		resultsPage=accPage.dosearch(searchkey);
		String actSearchTitle=resultsPage.getResultsPageTitle(searchkey);
		System.out.println("search page title is:"+actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - " + searchkey);
		
	}
	
//	@DataProvider
//	public Object[][] getProductTestData(){
//		return new Object[][] {
//			{"Macbook","MacBook Pro"},
//			{"iMac","iMac"},
//			{"Samsung","Samsung SyncMaster 941BW"},
//			{"Samsung","Samsung Galaxy Tab 10.1"},	
//			
//		};
//	}
			
	
	@Test(dataProvider="productDataWithName",dataProviderClass=ProducutDataProvider.class)
	public void selectProductTest(String searchKey, String productName)
	{
		resultsPage=accPage.dosearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		String actProductHeaderName=productInfoPage.getProductHeaderName();
		System.out.println("actual product name: "+actProductHeaderName);
		Assert.assertEquals(actProductHeaderName, productName);	
	}
	
	
//	@DataProvider
//	public Object[][] getProductImagesTestData(){
//		return new Object[][] {
//			{"Macbook","MacBook Pro",4},
//			{"iMac","iMac",3},
//			{"Samsung","Samsung SyncMaster 941BW",1},
//			{"Samsung","Samsung Galaxy Tab 10.1",7},	
//			
//		};
//	}
	
	
	@Test(dataProvider="productDataWithIamge",dataProviderClass=ProducutDataProvider.class)
	public void productImagesTest(String searchKey,String productName, int expImagesCount)
	{
		resultsPage=accPage.dosearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		int actProductImagesCount=productInfoPage.getProductImagesCount();
		System.out.println("actual product images count: "+actProductImagesCount);
		Assert.assertEquals(actProductImagesCount, expImagesCount);	
	}
	
}
	


