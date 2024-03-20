package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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

	// Validate the title of the page after searching for a product
	@Test
	public void searchResultsTitleTest() {
		sp = ap.doSearch("macbook");
		String title = sp.getSearchResultsTitle("macbook");
		Assert.assertEquals(title, "Search - " + "macbook");
	}

	// Validate the url of the page after searching for a product
	@Test
	public void searchResultsURLTest() {
		sp = ap.doSearch("macbook");
		String url = sp.getSearchResultsURL("macbook");
		Assert.assertTrue(url.contains("macbook"));
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

	// Validate that header value of the selected product.
	@Test
	public void selectedProductNameTest() {
		sp = ap.doSearch("MacBook");
		pi = sp.selectProduct("MacBook Pro");
		String productHeader = pi.getProductHeader();
		System.out.println("The actual product name is:" + productHeader);
		Assert.assertEquals(productHeader, "MacBook Pro");

	}

	// Validate that total number of images of the selected product is as per the expected value.
	@Test
	public void selectedProductImageCountTest() {
		sp = ap.doSearch("MacBook");
		pi = sp.selectProduct("MacBook Pro");
		int productImagesCount = pi.getProductImagesCount();
		System	.out.println("The actual product count is:" + productImagesCount);
		Assert.assertEquals(productImagesCount, 4);

	}

}
