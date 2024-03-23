package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {
	@DataProvider
	public Object[][] getIncorrectLoginCredentialsData() {
		return new Object[][] { { "daxjk@gmail.com", "dax" }, { "daxjk", "daxjk" }

		};
	}

	@Test(dataProvider = "getIncorrectLoginCredentialsData")
	public void doLoginNegativeTest(String username, String password) {

		Assert.assertTrue(lp.doLoginWithIncorrectLoginCredentials(username, password));

	}

}
