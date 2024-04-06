package com.qa.opencart.dataproviders;

import org.testng.annotations.DataProvider;

import com.qa.opencart.pojo.ProductDetails;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoDataProviders {

	@DataProvider
	public Object[][] allproductinfo() {
		return new Object[][] {

				{ "samsung", "Samsung Galaxy Tab 10.1", "SAM1","1000","Pre-Order","$241.99","$199.99","Samsung Galaxy Tab 10.1",""},
				{ "macbook", "MacBook Pro", "Product 18","800","In Stock","$2,000.00","$2,000.00","MacBook Pro","Apple"}

		};

	}
	
	@DataProvider
	public Object[][] productdetailsInExcel() {
		Object[][] excelProductDetails = ExcelUtil.getTestData("productinfo");
		return excelProductDetails;

	}
	
	@DataProvider
	public Object[][] allproductinfoPOJO() {
		return new Object[][]{

			{new ProductDetails("samsung", "Samsung Galaxy Tab 10.1", "SAM1","1000","Pre-Order","$241.99","$199.99","Samsung Galaxy Tab 10.1","")},
			{new ProductDetails("macbook", "MacBook Pro", "Product 18","800","In Stock","$2,000.00","$2,000.00","MacBook Pro","Apple")}

		};

	}

}
