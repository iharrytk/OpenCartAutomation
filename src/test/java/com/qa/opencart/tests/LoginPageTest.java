package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class LoginPageTest extends BaseTest {

	WebDriver driver;

	// Validate if the Login Page Title is displayed as "Account Login"
	@Test
	public void loginPageTitleTest() {
		String title = lp.getTitleLoginPage();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE_VALUE);

	}

	// Validate if the URL of Login Page is displayed as per the requirement and has
	// the word login
	@Test
	public void loginPageurlTest() {
		String url = lp.getCurrentURLofLoginPage();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));

	}

	// Validate if the Login Page footer section has 15 hyperlinks.
	@Test
	public void footersListTest() {
		List<String> footervalues = lp.getFooterList();
		int footerListCount = footervalues.size();
		Assert.assertEquals(footerListCount, 15);

	}

	// Validate if the Login Page has the forgot password link.
	@Test
	public void forgotPasswordLinkTest() {
		Boolean linkpresence = lp.isFPLinkDisplayed();
		Assert.assertTrue(linkpresence);

	}

}
