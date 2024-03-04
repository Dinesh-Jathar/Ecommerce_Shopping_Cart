package com.shoppingcart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_connection {

	private static Connection connection=null;
	
	//helper method for getting connection interface object
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		if(connection==null) {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			}
			return connection;
	}
	
}
