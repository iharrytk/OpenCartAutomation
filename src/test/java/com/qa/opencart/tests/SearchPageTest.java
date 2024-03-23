package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchPageTest extends BaseTest {
	// Login into the opencart application and pass the driver reference to
	// AccountPage object.From ap ,the driver
	// reference is given to SearchPage.
	@BeforeClass
	public void searchPageSetUp() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getSearchKeyData() {
		return new Object[][] { 
			{"macbook"},
			{"samsung"},
			{"imac"}
		};
	}

	// Validate the title of the page after searching for a product
	@Test(dataProvider="getSearchKeyData")
	public void searchResultsTitleTest(String searchKey) {
		sp = ap.doSearch(searchKey);
		String title = sp.getSearchResultsTitle(searchKey);
		Assert.assertEquals(title, "Search - " + searchKey);
	}

	// Validate the url of the page after searching for a product
	@Test(dataProvider="getSearchKeyData")
	public void searchResultsURLTest(String urlKey) {
		sp = ap.doSearch(urlKey);
		String url = sp.getSearchResultsURL(urlKey);
		Assert.assertTrue(url.contains(urlKey));
	}

	// Validate that products are displayed for given search criteria.
	@Test
	public void searchResultsProductCountTest() { 
		
		sp = ap.doSearch("macbook");
		int productsCount = sp.getSearchResultsProductsSize();
		Assert.assertTrue(productsCount > 0);
		System.out.println("We do have search results for the product");

	}

	// Validate that products that are displayed are relevant to the search
	// criteria.
	@Test
	public void searchResultsProductNamesTest() {
		sp = ap.doSearch("Samsung");
		List<String> productNamesList = sp.getSearchResultsProductsNames();
		for (String string : productNamesList) {
			// System.out.println("The product="+string);
			Assert.assertTrue(string.contains("Samsung"));
		}

		System.out.println("search results for the product are as per the search criteria");

	}
	
	@DataProvider
	public Object[][] getProductNameData(){
		
		return new Object[][] {
			
			{"MacBook","MacBook Pro","MacBook Pro"},
			{"samsung","Samsung Galaxy Tab 10.1","Samsung Galaxy Tab 10.1"},
		};
	}

	// Validate that header value of the selected product.
	@Test(dataProvider="getProductNameData")
	public void selectedProductNameTest(String searchKey,String selectedProduct,String productHeaderName ) {
		sp = ap.doSearch(searchKey);
		pi = sp.selectProduct(selectedProduct);
		String productHeader = pi.getProductHeader();
		System.out.println("The actual product name is:" + productHeader);
		Assert.assertEquals(productHeader, productHeaderName);

	}
	@DataProvider
	public Object[][] getProductImageCountData(){
		
		return new Object[][] {
			
			{"MacBook","MacBook Pro",4},
			{"samsung","Samsung Galaxy Tab 10.1",7},
		};
	}

	// Validate that total number of images of the selected product is as per the
	// expected value.
	@Test(dataProvider="getProductImageCountData")
	public void selectedProductImageCountTest(String searchKey,String selectedProduct,int imagesCount) {
		sp = ap.doSearch(searchKey);
		pi = sp.selectProduct(selectedProduct);
		int productImagesCount = pi.getProductImagesCount();
		System.out.println("The actual product count is:" + productImagesCount);
		Assert.assertEquals(productImagesCount, imagesCount);

	}

}
