package com.biz.addr.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

private static Connection dbConn=null;
	
	static {
		String jdbDriver ="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@localHost:1521:xe";
		String user="user4";
		String password="user4";
		
		// JDBC 드라이버를 메모리에 LOAD
		try {
			Class.forName(jdbDriver);
			dbConn = DriverManager.getConnection(url, user, password);
			System.out.println("DBConnection OK");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return dbConn;
	}
	
}
