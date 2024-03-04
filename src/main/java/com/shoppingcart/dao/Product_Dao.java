package com.shoppingcart.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.shoppingcart.connection.Db_connection;
import com.shoppingcart.dto.Cart;
import com.shoppingcart.dto.Product;

public class Product_Dao {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String query;

	public Product_Dao(Connection con) {
		super();
		this.con = con;
	}

	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<Product>();
		query = "select * from shopping.products";
		try {
			con = Db_connection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getInt(1));
				product.setProduct_name(rs.getString(2));
				product.setProduct_catogery(rs.getString(3));
				product.setProduct_price(rs.getDouble(4));
				product.setProduct_img(rs.getString(5));

				list.add(product);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		List<Cart> cartProducts = new ArrayList<Cart>();
		try {
			if (cartList.size() > 0) {
				for (Cart cart : cartList) {
					query = "Select * from shopping.products where pid=?";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setInt(1, cart.getProduct_id());
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Cart item = new Cart();
						item.setProduct_id(rs.getInt(1));
						item.setProduct_name(rs.getString(2));
						item.setProduct_catogery(rs.getString(3));
						item.setProduct_price(rs.getDouble(4) * cart.getQuantity());
						item.setQuantity(cart.getQuantity());

						cartProducts.add(item);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return cartProducts;
	}

	public double getCartPrice(ArrayList<Cart> cartList) {

		double sum = 0;
		try {
			for (Cart cart : cartList) {
				query = "select * from shopping.products where pid=? ";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, cart.getProduct_id());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					sum += rs.getDouble(4) * cart.getQuantity();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return sum;
	}

	public Product getProductById(int id) {
		Product product = null;

		try {
			query="Select * from shopping.products where pid=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				product=new Product();
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_catogery(rs.getString("product_category"));
				product.setProduct_price(rs.getDouble("product_price"));
				}
		} catch (Exception e) {
			System.out.println(e);
		}
		return product;
	}
}
