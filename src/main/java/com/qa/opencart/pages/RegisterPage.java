package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	ElementUtil eutil;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		eutil = new ElementUtil(this.driver);

	}

	private By firstNameLocator = By.id("input-firstname");
	private By lastNameLocator = By.id("input-lastname");
	private By emailLocator = By.id("input-email");
	private By telephoneLocator = By.id("input-telephone");
	private By passwordLocator = By.id("input-password");
	private By confirmPasswordLocator = By.id("input-confirm");
	private By agreeCheckBoxLocator = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By submitLocator=By.xpath("//input[@type='submit' and @value='Continue']");
	private By subcribeRadioYesLocator=By.xpath("(//div[@class='col-sm-10']/label/input)[1]");
	private By subcribeRadioNoLocator=By.xpath("(//div[@class='col-sm-10']/label/input)[2]");

	public CompletedRegistrationPage doRegisterUser(String firtname,String lastname,String emailid,String phonenumber,String password,String subscribe) {
		eutil.waitForElementVisibility(firstNameLocator, AppConstants.MEDIUM_DEFAULT_WAIT);
		eutil.doSendKeys(firstNameLocator, firtname);
		eutil.doSendKeys(lastNameLocator, lastname);
		eutil.doSendKeys(emailLocator, emailid);
		eutil.doSendKeys(telephoneLocator, phonenumber);
		eutil.doSendKeys(passwordLocator, password);
		eutil.doSendKeys(confirmPasswordLocator, password);
		doSubscribe(subscribe);
		eutil.doClick(agreeCheckBoxLocator);
		eutil.doClick(submitLocator);
		return new CompletedRegistrationPage(driver);
		
		

	}
	private void doSubscribe(String subscribe) {
		if(subscribe.equals("Yes")) {
			eutil.doClick(subcribeRadioYesLocator, AppConstants.SHORT_DEFAULT_WAIT);
		}
		else {
			eutil.doClick(subcribeRadioNoLocator, AppConstants.SHORT_DEFAULT_WAIT);
		}
		
	}

}
