package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	ElementUtil eutil;

	public AccountsPage(WebDriver driver) {

		this.driver = driver;
		eutil=new ElementUtil(this.driver);
	}

	// private By locators of the Accounts Page
	private By accountsHeaders = By.xpath("//div[@id='content']/h2");
	private By rightSideShortCutLinks = By.xpath("//aside[@id='column-right']//a");
	private By searchbar = By.xpath("//input[@name='search']");
	private By searchIcon = By.xpath("//div[@id='search']//button");

	// public page actions/methods
	public String getTitleAccountsPage() {
		String title = eutil.waitForTitleContainsAndCapture(10, "My Account");
		System.out.println("the title of the page is:" + title);
		return title;

	}

	public String getCurrentURLofAccountsPage() {
		String url = eutil.waitForURLContainsAndCapture(10, "route=account/account");
		System.out.println("the url of the page is:" + url);
		return url;

	}

	public List<String> getAccountsPageHeaders() {

		List<WebElement> headers = eutil.waitForElementsVisibility(accountsHeaders,10);
		List<String> headersvalues = new ArrayList<String>();
		for (WebElement ele : headers) {
			String value = ele.getText();
			headersvalues.add(value);
		}
		return headersvalues;

	}

	public List<String> getAccountsPageRightPanelLinks() {

		List<WebElement> rightpanelLinks = eutil.waitForElementsVisibility(rightSideShortCutLinks,10);
		List<String> rightPanelvalues = new ArrayList<String>();
		for (WebElement ele : rightpanelLinks) {
			String value = ele.getText();
			rightPanelvalues.add(value);
		}
		return rightPanelvalues;

	}

	public SearchPage doSearch(String searchstring) {
		eutil.doSendKeys(searchbar,searchstring);
		eutil.doClick(searchIcon);
		return new SearchPage(driver);
	}

}
