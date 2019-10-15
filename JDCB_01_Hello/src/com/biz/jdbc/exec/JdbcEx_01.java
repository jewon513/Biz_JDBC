package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEx_01 {

	public static void main(String[] args) {

		System.out.println("Hello Oracle");
		
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localHost:1521:xe";
		String userName = "grade";
		String password = "grade";
		
		try {
			/*
			 * jdbcDriver(oracle.jdbc.driver.OracleDriver.cass)를 사용할 수 있도록
			 * JVM에 등록(loading) 하라는 지시이다.
			 */
			Class.forName(jdbcDriver);
			/*
			 * url : 연결주소
			 * userName : DB 사용자
			 * password : 사용자의 pw
			 * 을 사용하여 DMBS와 SQL을 주고받을 통로를 설정 
			 */
			Connection dbConn = null;
			dbConn = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection OK");
			
			//이제 DBMS에게 SQL을 전송하는 절차가 필요하다.
			//JDBC와 우리의 App간의 데이터 교환이 이루지는 영역
			PreparedStatement pStr = null;
			
			//연결된 통로를 통해서 SQL문을 전달하고
			//그 결과를 pStr 변수에 받는다.
			String sql = "SELECT * FROM tbl_score";
			pStr = dbConn.prepareStatement(sql);
			
			//sql(SELECT) 문을 실행하도록 지시
			//SELECT 된 결과를 ResultSet에 받아라
			ResultSet rst = pStr.executeQuery(); // 
			
			/*
			 * 이제 rst 객체에는 DB로부터 읽어온 리스트가 ResultSet 데이터 type으로 저장되어 있다.
			 * 이 ResultSet은 일종의 Iterator 처럼 취급할 수 있다.
			 * 
			 * rst.next() method가 한번 실행 될 때마다 데이터 리스트의 앞쪽부터 한개씩 꺼내서
			 * 읽을 수 있도록 준비를 한다.
			 * 
			 * 또 rst.next() method가 실행되면 현재 위치의 다음 레코드를 읽는다.
			 * 
			 * 마지막 리스트를 모두 읽은 후 또 rst.next()가 실행되면 false값을 return
			 * 
			 */
			while(rst.next()){
				// 읽을 준비가 된 리스트의 요소의 1번 칼럼의 값을 꺼내라
				// 저장된 데이터 칼럼의 type이 숫자형이면 숫자 get() method로 읽을 수 있다.
				System.out.print(rst.getInt(1) + "\t");
				
				// 읽을 준비가된 리스트 요소의 2번칼럼의 값을 꺼내라
				System.out.print(rst.getString(2) + "\t");
				
				// 읽을 준비가된 리스트 요소의 3번칼럼의 값을 꺼내라
				System.out.print(rst.getInt(3) + "\n");
			}
			rst.close();
			dbConn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
