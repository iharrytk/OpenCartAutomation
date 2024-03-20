package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	ElementUtil eutil;
	
	//By locators
	By searchResultProductNames=By.xpath("//div[contains(@class,'product-grid')]//div[@class='caption']//a");
	By searchResults=By.xpath("//div[@class='row']//div[contains(@class,'product-grid')]");

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		eutil=new ElementUtil(this.driver);
	}
	
	public String getSearchResultsTitle(String searchString) {
		String title=eutil.waitForTitleContainsAndCapture(10, searchString);
		return title;
		
	}
	
	public String getSearchResultsURL(String searchStringUrl) {
		
		String url=eutil.waitForURLContainsAndCapture(10, searchStringUrl);
		return url;
	}
	
	public int getSearchResultsProductsSize() {
		
		List<WebElement> searchlist=eutil.waitForElementsVisibility(searchResults, 10);
		return searchlist.size();
		
	}
	
	public List<String> getSearchResultsProductsNames() {
		List<WebElement> searchelementnames=eutil.waitForElementsVisibility(searchResultProductNames, 10);
		List<String> searchlistnames=new ArrayList<String>();
		for (WebElement webElement : searchelementnames) {
			String sarchresultname=webElement.getText();
			searchlistnames.add(sarchresultname);
			
		}
		return searchlistnames;
		
		
	}
	
	public ProductInfoPage selectProduct(String productName) {
		
		By productLink=By.linkText(productName);
		eutil.doClick(productLink);
		return new ProductInfoPage(driver);
	}

}
