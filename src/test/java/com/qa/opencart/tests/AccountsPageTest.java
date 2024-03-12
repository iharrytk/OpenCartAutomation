package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountsPageSetUp() {
		ap = lp.doLogin("daxjk@gmail.com", "daxjk");
	}

	@Test
	public void accountsPageTitleTest() {
		String title = ap.getTitleAccountsPage();
		Assert.assertEquals(title, "My Account");

	}

	@Test
	public void accountsPageURLTest() {
		String url = ap.getCurrentURLofAccountsPage();
		Assert.assertTrue(url.contains("route=account/account"));

	}

}
