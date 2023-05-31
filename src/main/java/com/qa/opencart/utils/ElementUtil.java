package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.frameworkexception.FrameException;

public class ElementUtil {
private WebDriver driver;
private JavaScriptUtil jsUtil;
private final int DEFAULT_TIME_OUT=5;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver= driver;
		jsUtil=new JavaScriptUtil(this.driver); 
	}
	
	public  void doSendKeys(By locator, String value) {
		if(value==null)
		{
			System.out.println("null values are not allowed");
			throw new FrameException("VALUECANNOTBENULL");
		}
		
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
//		doClear(locator);
//		getElement(locator).sendKeys(value);

	}
	
//	public  void doSendKeys(By locator, String value) {
//		if(value==null)
//		{
//			throw new MySeleniumException("VALUECANNOTBENULL");
//		}
//		
//		doClear(locator);
//		getElement(locator).sendKeys(value);
//
//	}
	
	public  void doClick(By locator)
	{
		getElement(locator).click();
	}

	public  WebElement getElement(By locator,int timeOut)
	{
		WebElement element= waitForElementVisible(locator,timeOut);
		if(Boolean.parseBoolean(DriverFactory.highlightElement))
		{
			jsUtil.flash(element);
		}
		return element;
	    
	}
	
	public WebElement getElement(By locator)
	{
		WebElement element=null;
		try
		{
			element=driver.findElement(locator);
			System.out.println("Element is not found with locator: "+locator);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Element is not found using this locator: "+locator);
		    element=waitForElementVisible(locator,DEFAULT_TIME_OUT);
		    
		}
		
		if(Boolean.parseBoolean(DriverFactory.highlightElement))
		{
			jsUtil.flash(element);
		}
		
	return element;
	}
	
	
	public  void doClear(By locator)
	{
		getElement(locator).clear();
	}
	public  String doGetElementText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public boolean checkElementIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	public  String doGetAttributeValue(By locator, String attrName)
	{
		return getElement(locator).getAttribute(attrName);
		
	}
	public  int getElementsCount(By locator)
	{
		return getElements(locator).size();
	}


	public  List<String> getElementsAttributeValue(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList=new ArrayList<String>();//0
		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			//System.out.println(attrValue);
			eleAttrList.add(attrValue);
			
		}
		return eleAttrList;
	}
	public List<String> getElementsTextList(By locator)
	{
		List<WebElement> elementsLinksList=getElements(locator);
		List<String> elementsTextList=new ArrayList<String>();
		
		for(WebElement e: elementsLinksList )
		{
			String text=e.getText();
			elementsTextList.add(text);
		}
		return elementsTextList;
				
	}
	public  void clickElementFromPageSection(By locator, String eleText) {
		List<WebElement> eleLinksList = getElements(locator);
		for (WebElement e : eleLinksList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(eleText)) {
				e.click();
				break;
			}
		}
	}
	
	public  void search(By searchLocator, String searchKey, String suggName,By suggestions) throws InterruptedException
	{
		//driver.findElement(searchLocator).sendKeys(searchKey);
		doSendKeys(searchLocator,searchKey); // existing method in ElementUtil
		Thread.sleep(3000);
		
		//List<WebElement> suggList=driver.findElements(suggestions);
		List<WebElement> suggList=getElements(suggestions); // existing method in ElementUtil
		
		
		System.out.println("Total suggestions are: "+suggList.size());
		if(suggList.size()>0)
		{
		
		for(WebElement e: suggList)
		{
			String text=e.getText();
			if(text.length()>0)
			{
			System.out.println(text);
			if(text.contains(suggName))
			{
				e.click();
				break;
			}
		    }
		
		else
		{
			System.out.println("blank values-----No Suggestions");
			break;
		}
		}
		}
		else {
			System.out.println("No such suggestions found");
		}
			
		}
	
	
	public  boolean isElementDisplayed(By locator)
	{
		List<WebElement> eleList=getElements(locator);
		if(eleList.size()>0)
		{
			System.out.println(locator+"Element is presenton the page");
			return true;
		}
		else {
			return false;
		}
	}

	

	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
//	public String doGetElementText(By locator)
//	{
//		return getElement(locator).getText();
//	}
//	
	
	//******************************DROP-DOWN CLASSES***************************************//
	
	
	public  void doSelectDropDoenByIndex(By locator, int index)
	{
		Select select=new Select(getElement(locator));
		select.selectByIndex(index);
	}
	public  void doSelectDropDownByVisibleText(By locator, String text)
	{
		Select select=new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	public  void doSelectDropDownByValueAttribute(By locator, String value)
	{
		Select select=new Select(getElement(locator));
		select.selectByValue(value);
	}
	public void doSelectDropDownValue(By locator, String dropdownvalue)
	{
		Select select=new Select(getElement(locator));
		List<WebElement> optionslist=select.getOptions();
		List<String> optionsvaluelist=new ArrayList<String>();
		System.out.println("total values are "+optionslist.size());
		
		for(WebElement e:optionslist)
		{
			String text=e.getText();
			//System.out.println(text);
			optionsvaluelist.add(text);
			
			if(text.equals(dropdownvalue))
			{
				e.click();
				break;
			}
		}
	}
	public  List<String> getAllDropDownOptions(By locator)
	{
		Select select=new Select(getElement(locator));
		List<String> countryvaluelist=new ArrayList<String>();

		List<WebElement> countryElements=select.getOptions();
		System.out.println("the total countries present are: "+countryElements.size());
		
		for(WebElement e:countryElements)
		{
			String text=e.getText();
			System.out.println(text);
			countryvaluelist.add(text);	
		}
		
		return countryvaluelist;	
		
	}
	public  boolean doSelectValueFromDopdownWithoutSelect(By locator, String value)
	{
		boolean flag=false;
		List<WebElement> optionslist=getElements(locator);
		for(WebElement e:optionslist)
		{
			String text=e.getText();
			if(text.equals(value))
			{
				flag=true;
				e.click();
				break;
			}
			
		}
		if(flag==false)
		{
			System.out.println(value+"is not present in the dropdoen"+locator);
			
		}
		return flag;
	}	
	public  int getElementValueCount(By locator)
	{
		return getAllDropDownOptions(locator).size();
	}
	//****************************------Wait-----******************//
	
	public WebElement waitForElementVisible(By locator, int timeout)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public List<WebElement> waitForElementsVisible(By locator, int timeout)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public String waitForURLContainsAndCapture(String urlFraction, int timeOut)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if( wait.until(ExpectedConditions.urlContains(urlFraction)));
		String url=driver.getCurrentUrl();
		return url;
	}
	
	public String waitForTitleIsAndCapture(String tilteFraction, int timeout)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.titleContains(tilteFraction)))
		{
			String title=driver.getTitle();
			return title;
		}
		else {
			System.out.println("title is not present within the given timeout:"+timeout);
			return null;
		}
	}
	
     
     

}

