package com.shoppingcart.dao;

import java.sql.*;

import com.shoppingcart.dto.User;

public class UserDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String query;
	
	
	public UserDao(Connection con) {
		this.con = con;
	}
	
	public boolean userSignup(String EmailID,String name,long MOBno,String password) {
		for(int i=0;i<name.length();i++) {
			if((name.charAt(i)>='A' && name.charAt(i)<='Z') || (name.charAt(i)>='a' && name.charAt(i)<='z')  ) {
				continue;
			}else {return false;}
		}
		query="insert into shopping.signin values(?,?,?,?)";
		try {
		pstmt=con.prepareStatement(query);
		pstmt.setString(1, EmailID);
		pstmt.setLong(2, MOBno);
		pstmt.setString(3, name);
		pstmt.setString(4, password);
		pstmt.execute();
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	public User userLogin(String userName,String password) {
		
		User user=null;
		try {
			query="select * from shopping.signin where Email_id=? and password=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUserName(rs.getString(1));
				user.setPassword(rs.getString(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return user;
	}
}
