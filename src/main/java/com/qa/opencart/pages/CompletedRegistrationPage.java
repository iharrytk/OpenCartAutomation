package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class CompletedRegistrationPage {
	
	private WebDriver driver;
	ElementUtil eutil;

	public CompletedRegistrationPage(WebDriver driver) {
		this.driver=driver;
		eutil = new ElementUtil(this.driver);
	}
	
	By registrationSuccessMesgLocator=By.xpath("//div[@id='content']/h1");
	By logoutLocator=By.linkText("Logout");
	By RegistrationLocator=By.linkText("Register");
	
	
	public String getRegistrationMesg() {
		
		String mesg=eutil.doGetElementText(registrationSuccessMesgLocator);
		System.out.println("The actual message after completing registration:"+mesg);
//		if(mesg.contains("Your Account Has Been Created!")) {
//			return true;
//		}
//		return false;
		eutil.doClick(logoutLocator);
		eutil.doClick(RegistrationLocator);
		return mesg;
	}
}
