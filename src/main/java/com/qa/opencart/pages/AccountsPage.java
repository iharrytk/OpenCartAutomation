package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountsPage {

	private WebDriver driver;

	public AccountsPage(WebDriver driver) {

		this.driver = driver;
	}

	// private By locators of the Accounts Page
	By accountsHeaders = By.xpath("//div[@id='content']/h2");
	By rightSideShortCutLinks = By.xpath("//aside[@id='column-right']//a");

	// public page actions/methods
	public String getTitleAccountsPage() {
		String title = driver.getTitle();
		System.out.println("the title of the page is:" + title);
		return title;

	}

	public String getCurrentURLofAccountsPage() {
		String url = driver.getCurrentUrl();
		System.out.println("the url of the page is:" + url);
		return url;

	}

	public List<String> getAccountsPageHeaders() {

		List<WebElement> headers = driver.findElements(accountsHeaders);
		List<String> headersvalues = new ArrayList<String>();
		for (WebElement ele : headers) {
			String value = ele.getText();
			headersvalues.add(value);
		}
		return headersvalues;

	}

	public List<String> getAccountsPageRightPanelLinks() {

		List<WebElement> rightpanelLinks = driver.findElements(rightSideShortCutLinks);
		List<String> rightPanelvalues = new ArrayList<String>();
		for (WebElement ele : rightpanelLinks) {
			String value = ele.getText();
			rightPanelvalues.add(value);
		}
		return rightPanelvalues;

	}

}
