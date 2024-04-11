package com.qa.opencart.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductInfoDataProviders;
import com.qa.opencart.pojo.ProductDetails;

public class ProductInfoPageDataProvidersTest extends BaseTest {

	@BeforeClass
	public void accPagSetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	//To Validate all the productdetails for a product being searched with testdata from data provider annotations
	@Test(dataProvider = "allproductinfo", dataProviderClass = ProductInfoDataProviders.class)
	public void productInfoDetailsTest(String searchstring, String productSelected, String productcode,
			String rewardpoints, String availability, String price, String extax, String productName, String brand) {
		sp = ap.doSearch(searchstring);
		pi = sp.selectProduct(productSelected);
		Map<String, String> productDataMap = pi.getProductInfo();
		System.out.println("The product details list:" + productDataMap);
		softassert.assertEquals(productDataMap.get("Product Code"), productcode);
		softassert.assertEquals(productDataMap.get("Reward Points"), rewardpoints);
		softassert.assertEquals(productDataMap.get("Availability"), availability);
		softassert.assertEquals(productDataMap.get("price"), price);
		softassert.assertEquals(productDataMap.get("Ex Tax"), extax);
		softassert.assertEquals(productDataMap.get("ProductName"), productName);
		softassert.assertEquals(productDataMap.get("Brand"), brand);

	}
	
	//To Validate all the productdetails for a product being searched with testdata from Excel file and using ApachePOI
//	@Test(dataProvider = "productdetailsInExcel", dataProviderClass = ProductInfoDataProviders.class)
//	public void productInfoDetailsExcelUtilTest(String searchstring, String productSelected, String productcode,
//			String rewardpoints, String availability, String price, String extax, String productName, String brand) {
//		sp = ap.doSearch(searchstring);
//		pi = sp.selectProduct(productSelected);
//		Map<String, String> productDataMap = pi.getProductInfo();
//		System.out.println("The product details list:" + productDataMap);
//		softassert.assertEquals(productDataMap.get("Product Code"), productcode);
//		softassert.assertEquals(productDataMap.get("Reward Points"), rewardpoints);
//		softassert.assertEquals(productDataMap.get("Availability"), availability);
//		softassert.assertEquals(productDataMap.get("price"), price);
//		softassert.assertEquals(productDataMap.get("Ex Tax"), extax);
//		softassert.assertEquals(productDataMap.get("ProductName"), productName);
//		softassert.assertEquals(productDataMap.get("Brand"), brand);
//
//	}
	
	
	
	//To Validate all the productdetails for a product being searched with testdata using POJO class ProductDetails
		@Test(dataProvider = "allproductinfoPOJO", dataProviderClass = ProductInfoDataProviders.class)
		public void productInfoDetailsPOJOTest(ProductDetails pd) {
			sp = ap.doSearch(pd.getSearchString());
			pi = sp.selectProduct(pd.getProductSearch());
			Map<String, String> productDataMap = pi.getProductInfo();
			System.out.println("The product details list:" + productDataMap);
			softassert.assertEquals(productDataMap.get("Product Code"), pd.getProductCode());
			softassert.assertEquals(productDataMap.get("Reward Points"), pd.getRewardPoints());
			softassert.assertEquals(productDataMap.get("Availability"), pd.getAvailability());
			softassert.assertEquals(productDataMap.get("price"), pd.getPrice());
			softassert.assertEquals(productDataMap.get("Ex Tax"), pd.getExtaxPrice());
			softassert.assertEquals(productDataMap.get("ProductName"), pd.getProductName());
			softassert.assertEquals(productDataMap.get("Brand"), pd.getBrand());

		}

	

}
