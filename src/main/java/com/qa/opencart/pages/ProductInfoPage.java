package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	ElementUtil eutil;
	Map<String,String> productInfo=new LinkedHashMap<String,String>();

	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eutil=new ElementUtil(this.driver);
	}

	By productHeader=By.xpath("//div[@id='content']//h1");
	By productImages=By.xpath("//div[@id='content']//li/a[contains(@href,'jpg')]");
	By productMetaData=By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[position()=1]/li");
	By productPricingData=By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[position()=2]/li");
	
	public String getProductHeader() {
		String header=eutil.doGetElementText(productHeader);
		return header;
	}
	
	public int getProductImagesCount() {
		
		List<WebElement> images= eutil.waitForElementsVisibility(productImages,AppConstants.MEDIUM_DEFAULT_WAIT);
		return images.size();
	}
	
	public Map<String, String> getProductInfo() {
		getProductMetaData();
		getProductPricingData();
		productInfo.put("ProductName",getProductHeader());
		
		return productInfo;
		
		
	}
	public Map<String, String> getProductMetaData() {
		
		List<WebElement> productMetadata=eutil.waitForElementsVisibility(productMetaData,AppConstants.MEDIUM_DEFAULT_WAIT);
		for (WebElement webElement : productMetadata) {
			String metadata=webElement.getText();
			String metadatainfo[]=metadata.split(":");
			String key=metadatainfo[0].trim();
			String value=metadatainfo[1].trim();
			productInfo.put(key, value);	
		}
		
		return productInfo;
		
	}
	
	public Map<String, String> getProductPricingData() {
		List<WebElement> productPricingdata=eutil.waitForElementsVisibility(productPricingData,AppConstants.MEDIUM_DEFAULT_WAIT);
		String price=productPricingdata.get(0).getText();
		String exTaxprice=productPricingdata.get(1).getText();
		productInfo.put("price", price);
		String extaxprice[]=exTaxprice.split(":");
		String key=extaxprice[0].trim();
		String value=extaxprice[1].trim();
		productInfo.put(key, value);
		return productInfo;
	}
	
	
	

}
