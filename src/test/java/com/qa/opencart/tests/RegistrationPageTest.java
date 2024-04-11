package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetUp() {

		rp = lp.goToRegisterPage();

	}

	@DataProvider
	public Object[][] userRegistrationData() {

		return new Object[][] { { "alex", "xander", "4587857452", "alex", "No" },
				{ "coco", "channel", "4587857452", "coco", "Yes" }, { "dax", "daxxy", "4585289632", "daxxy", "No" } };
	}

	public String randomEmail() {

		return "testautomation" + System.currentTimeMillis() + "@gmail.com";
	}

	// Validate whether we the user is able to perform registration in the Register
	// Page
	@Test(dataProvider = "userRegistrationData")
	public void doregisterTest(String firtname, String lastname, String phonenumber, String password,
			String subscribe) {

		crp = rp.doRegisterUser(firtname, lastname, randomEmail(), phonenumber, phonenumber, subscribe);
		String registrationMessage = crp.getRegistrationMesg();
		Assert.assertEquals(registrationMessage, "Your Account Has Been Created!");
	}

	@DataProvider(name = "regExcelData")
	public Object[][] getRegExcelTestData() {

		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}

	// Validate whether we the user is able to perform registration in the Register
	// Page
//	@Test(dataProvider = "regExcelData")
//	public void doregisterwithExcelDataTest(String firtname, String lastname, String phonenumber, String password,
//			String subscribe) {
//
//		crp = rp.doRegisterUser(firtname, lastname, randomEmail(), phonenumber, phonenumber, subscribe);
//		String registrationMessage = crp.getRegistrationMesg();
//		Assert.assertEquals(registrationMessage, AppConstants.USER_RESG_SUCCESS_MESSG);
//	}

}
