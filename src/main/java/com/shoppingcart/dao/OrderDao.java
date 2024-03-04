package com.shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shoppingcart.dto.Cart;
import com.shoppingcart.dto.Order;
import com.shoppingcart.dto.Product;
import com.shoppingcart.dto.User;

public class OrderDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String query;

	public OrderDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertOrder(Order order) {

		boolean result = false;

		try {
			query = "insert into shopping.orders(p_id,u_name,o_quantity,o_date) values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, order.getProduct_id());
			pstmt.setString(2, order.getUserName());
			pstmt.setInt(3, order.getQuantity());
			pstmt.setString(4, order.getDate());
			pstmt.executeUpdate();
			result = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;

	}

	public boolean checkOutOrders(List<Cart> cartList, User user) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		Date date = new Date();
		boolean result = false;
		for (Cart c : cartList) {
			try {

				query = "insert into shopping.orders(p_id,u_name,o_quantity,o_date) values(?,?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, c.getProduct_id());
				pstmt.setString(2, user.getUserName());
				pstmt.setInt(3, c.getQuantity());
				pstmt.setString(4, dateFormat.format(date));
				pstmt.executeUpdate();
				result = true;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return result;
	}
	
	public List<Order> userOrder(String name) {
		List<Order>listOfOrder=new ArrayList<Order>();
		query="SELECT * FROM shopping.orders where u_name=? ";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			Product_Dao dao=new Product_Dao(con);
			while(rs.next()) {
				Order order=new Order();
				int productId=rs.getInt("p_id");
				Product product=dao.getProductById(productId);
				order.setOrderId(rs.getInt("o_id"));
				order.setProduct_id(productId);
				order.setProduct_name(product.getProduct_name());
				order.setProduct_catogery(product.getProduct_catogery());
				order.setProduct_price(product.getProduct_price()*rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				listOfOrder.add(order);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return listOfOrder;
	}
	
	public void cancelOrder(int id) {
				query="delete from shopping.orders where o_id=?";
				try {
					pstmt=con.prepareStatement(query);
					pstmt.setInt(1, id);
					pstmt.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
}
