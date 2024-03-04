package com.shoppingcart.dto;

import java.io.Serializable;

public class Cart extends Product  implements Serializable {
	
	private int quantity;

	
	@Override
	public String toString() {
		return "Cart [quantity=" + quantity + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
