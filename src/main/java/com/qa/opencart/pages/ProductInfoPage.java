package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	ElementUtil eutil;

	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eutil=new ElementUtil(this.driver);
	}

	By productHeader=By.xpath("//div[@id='content']//h1");
	By productImages=By.xpath("//div[@id='content']//li/a[contains(@href,'jpg')]");
	
	public String getProductHeader() {
		String header=eutil.doGetElementText(productHeader);
		return header;
	}
	
	public int getProductImagesCount() {
		
		List<WebElement> images= eutil.waitForElementsVisibility(productImages,10);
		return images.size();
	}

}
