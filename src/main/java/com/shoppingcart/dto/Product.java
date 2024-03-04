package com.shoppingcart.dto;

public class Product {

	private int product_id;
	private String product_name;
	private String product_catogery;
	private double product_price;
	private String product_img;
	
	


	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_catogery() {
		return product_catogery;
	}
	public void setProduct_catogery(String product_catogery) {
		this.product_catogery = product_catogery;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	
	
}
