package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {

	WebDriver driver;
	protected LoginPage lp;
	protected AccountsPage ap;
	protected Properties prop;
	protected DriverFactory df;

	@BeforeTest
	public void setUp() {
		df=new DriverFactory();
		prop=df.init_prop();
		driver=df.init_driver(prop);
		lp = new LoginPage(driver);

	}

	@AfterTest
	public void tearDown() {
		driver.quit();

	}

}
