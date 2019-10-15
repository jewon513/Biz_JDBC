package com.biz.jdbc.service;

public class DBConstract {
	
	public static class DB_INPO{
		
		public static final String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		public static final String URL = "jdbc:oracle:thin:@localHost:1521:xe";
		public static final String User = "grade";
		public static final String PASSWORD = "grade";
	}

	public static class SCORE{
		
		public static final int S_ID = 1;
		public static final int S_STD = 2;
		public static final int S_SCORE = 3;
		public static final int S_REM = 4;
		
	}
	
}
