package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;

	// Constructor of the class
	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}

	// By locators of the class
	By username = By.id("input-email");
	By password = By.id("input-password");
	By footerlist = By.xpath("//footer//li/a");
	By loginbtn = By.xpath("//input[@type='submit']");
	By forgotPassword = By.linkText("Forgotten Password");

	// public page actions/methods
	public String getTitleLoginPage() {
		String title = driver.getTitle();
		System.out.println("the title of the page is:" + title);
		return title;

	}

	public String getCurrentURLofLoginPage() {
		String url = driver.getCurrentUrl();
		System.out.println("the url of the page is:" + url);
		return url;

	}

	public List<String> getFooterList() {

		List<WebElement> footerList = driver.findElements(footerlist);
		List<String> values = new ArrayList<String>();
		for (WebElement webElement : footerList) {
			String footerlistvalue = webElement.getText();
			values.add(footerlistvalue);
		}
		return values;

	}

	public boolean isFPLinkDisplayed() {
		return driver.findElement(forgotPassword).isDisplayed();

	}

}
