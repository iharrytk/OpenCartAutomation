package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class AccountsPageTest extends BaseTest {

	// Precondition-To validate test cases in AccountsPage,We need to login using
	// doLogin method from LoginPage class.
	// same driver reference is being given to AccountsPage class.
	@BeforeClass
	public void accountsPageSetUp() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	// Validate if the Account Page Title is displayed as "My Account"
	@Test
	public void accountsPageTitleTest() {
		String title = ap.getTitleAccountsPage();
		Assert.assertEquals(title, "My Account");

	}

	// Validate if the URL of Account Page is displayed as per the requirement and
	// has
	// the word account
	@Test
	public void accountsPageURLTest() {
		String url = ap.getCurrentURLofAccountsPage();
		Assert.assertTrue(url.contains("route=account/account"));

	}

	// Validate the Account Page Headers Count is same as expected value
	@Test
	public void accountPageHeadersCountTest() {

		Assert.assertEquals(ap.getAccountsPageHeaders().size(), 4);
	}

	// Validate the Account Page Headers List is as the given expected list of
	// values.
	@Test
	public void accountPageHeadersListTest() {
		List<String> actualList = ap.getAccountsPageHeaders();
		List<String> expectedList = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
		Assert.assertEquals(actualList, expectedList);
	}

	// Validate if the right panel Links contains My Account link in the
	// AccountsPage
	@Test
	public void accountPageRightPanelMyAccountLinkExistTest() {
		List<String> rpList = ap.getAccountsPageRightPanelLinks();
		Assert.assertTrue(rpList.contains("My Account"));
	}

}
