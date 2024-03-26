package com.qa.opencart.dataproviders;

import org.testng.annotations.DataProvider;

import com.qa.opencart.pojo.Product;

public class ProductDataProvider {
	
	@DataProvider(name="AllProductDetails")
	public Object[][] getAllProductData() {
		return new Object[][] { 
			{new Product("macbook", "macbook", "MacBook Pro","MacBook Pro", 4)},
			{new Product("samsung", "samsung", "Samsung Galaxy Tab 10.1","Samsung Galaxy Tab 10.1", 7)},
			
		};
	}
	
	@DataProvider
	public Object[][] getSearchKeyData() {
		return new Object[][] { 
			{"macbook"},
			{"samsung"},
			{"imac"}
		};
	}
	
	@DataProvider
	public Object[][] getProductNameData(){
		
		return new Object[][] {
			
			{"MacBook","MacBook Pro","MacBook Pro"},
			{"samsung","Samsung Galaxy Tab 10.1","Samsung Galaxy Tab 10.1"},
		};
	}
	
	@DataProvider
	public Object[][] getProductImageCountData(){
		
		return new Object[][] {
			
			{"MacBook","MacBook Pro",4},
			{"samsung","Samsung Galaxy Tab 10.1",7},
		};
	}

}
