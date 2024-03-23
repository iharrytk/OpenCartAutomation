package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	ElementUtil eutil;

	// Constructor of the class
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eutil = new ElementUtil(this.driver);
	}

	// By locators of the class
	By usernameLocator = By.id("input-email");
	By passwordLocator = By.id("input-password");
	By footerlist = By.xpath("//footer//li/a");
	By loginbtn = By.xpath("//input[@type='submit']");
	By forgotPassword = By.linkText("Forgotten Password");
	By incorrectLoginErrorMessage = By
			.xpath("//div[@id='account-login']//div[@class='alert alert-danger alert-dismissible']");

	// public page actions/methods
	public String getTitleLoginPage() {
		String title = eutil.waitForTitleContainsAndCapture(AppConstants.MEDIUM_DEFAULT_WAIT,
				AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("the title of the page is:" + title);
		return title;

	}

	public String getCurrentURLofLoginPage() {
		String url = eutil.waitForURLContainsAndCapture(AppConstants.MEDIUM_DEFAULT_WAIT,
				AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("the url of the page is:" + url);
		return url;

	}

	public List<String> getFooterList() {

		List<WebElement> footerList = eutil.waitForElementsVisibility(footerlist, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> values = new ArrayList<String>();
		for (WebElement webElement : footerList) {
			String footerlistvalue = webElement.getText();
			values.add(footerlistvalue);
		}
		return values;

	}

	public boolean isFPLinkDisplayed() {
		return eutil.waitForElementVisibility(forgotPassword, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();

	}

	// page chaining model
	public AccountsPage doLogin(String username, String password) {

		eutil.waitForElementVisibility(usernameLocator, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eutil.doSendKeys(passwordLocator, password);
		eutil.doClick(loginbtn);
		return new AccountsPage(driver);

	}

	public boolean doLoginWithIncorrectLoginCredentials(String username, String password) {
		System.out.println("Entering Incorrect Username" + username + "and Password" + password);
		eutil.waitForElementPresence(usernameLocator, AppConstants.MEDIUM_DEFAULT_WAIT);
		eutil.doSendKeys(usernameLocator, username);
		eutil.doSendKeys(passwordLocator, password);
		eutil.doClick(loginbtn);
		String errorMessage = eutil.doGetElementText(incorrectLoginErrorMessage);
		System.out.println("Incorrect Login Error Message:" + errorMessage);
		if (errorMessage.equals(AppConstants.INCORRECT_LOGIN_ERROR_MESSAGE)) {
			return true;
		}
		return false;

	}

}
