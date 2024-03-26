package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;
import com.qa.opencart.pojo.Product;

public class SearchPageDataProvidersTest extends BaseTest {

	@BeforeClass
	public void searchPageSetUp() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	// Validate the title of the page after searching for a product
	@Test(dataProvider = "AllProductDetails",dataProviderClass = ProductDataProvider.class)
	public void searchResultsTitleTest(Product product) {
		sp = ap.doSearch(product.getSearchKey());
		String title = sp.getSearchResultsTitle(product.getSearchKey());
		Assert.assertEquals(title, "Search - " + product.getSearchKey() );
	}

	// Validate the url of the page after searching for a product
	@Test(dataProvider = "AllProductDetails",dataProviderClass = ProductDataProvider.class)
	public void searchResultsURLTest(Product product) {
		sp = ap.doSearch(product.getUrlKey());
		String url = sp.getSearchResultsURL(product.getUrlKey());
		Assert.assertTrue(url.contains(product.getUrlKey()));
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
	@Test(dataProvider = "AllProductDetails",dataProviderClass = ProductDataProvider.class)
	public void selectedProductNameTest(Product product) {
		sp = ap.doSearch(product.getSearchKey());
		pi = sp.selectProduct(product.getSelectedProduct());
		String productHeader = pi.getProductHeader();
		System.out.println("The actual product name is:" + productHeader);
		Assert.assertEquals(productHeader, product.getProductHeaderName());

	}

	// Validate that total number of images of the selected product is as per the
	// expected value.
	@Test(dataProvider = "AllProductDetails",dataProviderClass = ProductDataProvider.class)
	public void selectedProductImageCountTest(Product product) {
		sp = ap.doSearch(product.getSearchKey());
		pi = sp.selectProduct(product.getSelectedProduct());
		int productImagesCount = pi.getProductImagesCount();
		System.out.println("The actual product count is:" + productImagesCount);
		Assert.assertEquals(productImagesCount, product.getImagesCount());

	}

}
