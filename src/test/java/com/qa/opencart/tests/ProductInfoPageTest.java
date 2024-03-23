package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void accountsPageSetUp() {

		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	//HashMap Output:
	//Brand=Apple, Availability=In Stock, ProductName=MacBook Pro, price=$2,000.00, 
	//Ex Tax=$2,000.00, Product Code=Product 18, Reward Points=800
	
	//LinkedHashMap Output:
	//Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=In Stock, 
	//price=$2,000.00, Ex Tax=$2,000.00, ProductName=MacBook Pro
	
	//TreeMap Output:
	//Availability=In Stock, Brand=Apple, Ex Tax=$2,000.00, Product Code=Product 18, 
	//ProductName=MacBook Pro, Reward Points=800, price=$2,000.00}
	

	@Test
	public void productDetailsValidationTest() {
		sp = ap.doSearch("Macbook");
		pi = sp.selectProduct("MacBook Pro");
		Map<String, String> productDetails = pi.getProductInfo();
		System.out.println(productDetails);
		softassert.assertEquals(productDetails.get("Brand"), "Apple");
		softassert.assertEquals(productDetails.get("Availability"), "In Stock");
		softassert.assertEquals(productDetails.get("ProductName"), "MacBook Pro");
		softassert.assertEquals(productDetails.get("price"), "$2,000.00");
		softassert.assertEquals(productDetails.get("Ex Tax"), "$2,000.00");
		softassert.assertEquals(productDetails.get("Product Code"), "Product 18");
		softassert.assertEquals(productDetails.get("Reward Points"), "800");
		softassert.assertAll();

	}

}
