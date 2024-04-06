package com.qa.opencart.pojo;

public class ProductDetails {
	
	private String searchString;
	private String productSearch;
	private String productCode;
	private String rewardPoints;
	private String availability;
	private String price;
	private String extaxPrice;
	private String productName;
	private String brand;
	
	public ProductDetails(String searchString, String productSearch, String productCode, String rewardPoints,
			String availability, String price, String extaxPrice, String productName, String brand) {
		super();
		this.searchString = searchString;
		this.productSearch = productSearch;
		this.productCode = productCode;
		this.rewardPoints = rewardPoints;
		this.availability = availability;
		this.price = price;
		this.extaxPrice = extaxPrice;
		this.productName = productName;
		this.brand = brand;
	}
	
	@Override
	public String toString() {
		return "ProductDetails [searchString=" + searchString + ", productSearch=" + productSearch + ", productCode="
				+ productCode + ", rewardPoints=" + rewardPoints + ", availability=" + availability + ", price=" + price
				+ ", extaxPrice=" + extaxPrice + ", productName=" + productName + ", brand=" + brand + "]";
	}

	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getProductSearch() {
		return productSearch;
	}
	public void setProductSearch(String productSearch) {
		this.productSearch = productSearch;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(String rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getExtaxPrice() {
		return extaxPrice;
	}
	public void setExtaxPrice(String extaxPrice) {
		this.extaxPrice = extaxPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	

}
