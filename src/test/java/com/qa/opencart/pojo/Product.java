package com.qa.opencart.pojo;

public class Product {
	
	private String searchKey;
	private String productName;
	private int ProductImages;
	
	
	public Product(String searchKey, String productName, int productImages) {
		
		this.searchKey = searchKey;
		this.productName = productName;
		ProductImages = productImages;
	}


	public String getSearchKey() {
		return searchKey;
	}


	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getProductImages() {
		return ProductImages;
	}


	public void setProductImages(int productImages) {
		ProductImages = productImages;
	}


	@Override
	public String toString() {
		return "Product [searchKey=" + searchKey + ", productName=" + productName + ", ProductImages=" + ProductImages
				+ "]";
	}
	
	
	
	
	
	
	

}
