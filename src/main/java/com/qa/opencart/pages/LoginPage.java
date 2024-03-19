package com.qa.opencart.pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	ElementUtil eutil;

	// Constructor of the class
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eutil=new ElementUtil(this.driver);
	}

	// By locators of the class
	By usernameLocator = By.id("input-email");
	By passwordLocator = By.id("input-password");
	By footerlist = By.xpath("//footer//li/a");
	By loginbtn = By.xpath("//input[@type='submit']");
	By forgotPassword = By.linkText("Forgotten Password");

	// public page actions/methods
	public String getTitleLoginPage() {
		String title = eutil.waitForTitleContainsAndCapture(10, "Account Login");
		System.out.println("the title of the page is:" + title);
		return title;

	}

	public String getCurrentURLofLoginPage() {
		String url = eutil.waitForURLContainsAndCapture(10, "route=account/login");
		System.out.println("the url of the page is:" + url);
		return url;

	}

	public List<String> getFooterList() {

		List<WebElement> footerList = eutil.waitForElementsVisibility(footerlist,10);
		List<String> values = new ArrayList<String>();
		for (WebElement webElement : footerList) {
			String footerlistvalue = webElement.getText();
			values.add(footerlistvalue);
		}
		return values;

	}

	public boolean isFPLinkDisplayed() {
		return eutil.waitForElementVisibility(forgotPassword,10).isDisplayed();

	}
	
	//page chaining model
	public AccountsPage doLogin(String username,String password) {
		
		eutil.waitForElementVisibility(usernameLocator,10).sendKeys(username);
		eutil.doSendKeys(passwordLocator,password);
		eutil.doClick(loginbtn);
		return new AccountsPage(driver);
		
	}

}
