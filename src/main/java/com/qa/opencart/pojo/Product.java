package com.qa.opencart.pojo;

public class Product {
	private String searchKey;
	private String urlKey;
	private String selectedProduct;
	private String productHeaderName;
	private int imagesCount;

	public Product(String searchKey, String urlKey, String selectedProduct, String productHeaderName, int imagesCount) {
		super();
		this.searchKey = searchKey;
		this.urlKey = urlKey;
		this.selectedProduct = selectedProduct;
		this.productHeaderName = productHeaderName;
		this.imagesCount = imagesCount;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	public String getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(String selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public String getProductHeaderName() {
		return productHeaderName;
	}

	public void setProductHeaderName(String productHeaderName) {
		this.productHeaderName = productHeaderName;
	}

	public int getImagesCount() {
		return imagesCount;
	}

	public void setImagesCount(int imagesCount) {
		this.imagesCount = imagesCount;
	}

	@Override
	public String toString() {

		return "Product[searchKey=" + searchKey + ",urlKey=" + urlKey + ",selectedProduct=" + selectedProduct
				+ ",productHeaderName=" + productHeaderName + ",imagesCount=" + imagesCount + "]";

	}

}
