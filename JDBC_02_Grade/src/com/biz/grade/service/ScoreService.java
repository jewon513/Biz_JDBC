package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.utils.DBContarct;

/*
 * 	추상클래스 선언
 * 	추상클래스
 * 		※일부는 구현된 method, 일부는 형태만 갖는 method가 포함된 클래스
 * 		
 */
public abstract class ScoreService {

	protected Connection dbConn = null;
	
	/*
	 * dbConn 을 설정하여 DBMS에 접속할 수 있도록 통로를 설정해주는 method
	 */
	protected void dbConnection() {
		
		try {
			Class.forName(DBContarct.DbConn.JdbcDriver);
			dbConn = DriverManager.getConnection(DBContarct.DbConn.URL,
												DBContarct.DbConn.USER,
												DBContarct.DbConn.PASSWORD);
			System.out.println("DbConnections OK!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾지 못함!!!");
		} catch (SQLException e) {
			System.out.println("DBMS 접속 오류!!!");
		}
		
	}
	
	// CRUD
	public abstract int insert(ScoreDTO scoreDTO);
	
	// 모든 레코드를 조회하는 method
	// 1개 이상의 레코드
	public abstract List<ScoreDTO> selectAll();
	
	// id 값을 매개변수로 받아서 1개의 레코드만 조회하는 method
	public abstract ScoreDTO findById(long id);
	
	// 이름으로 조회
	public abstract List<ScoreDTO> findByName(String name);
	
	// 과목별로 점수리스트를 보고 싶다.
	public abstract List<ScoreDTO> findBySubject(String subject);
	
	public abstract int update(ScoreDTO scoreDTO);
	
	public abstract int delete(long id);
	
	
	

	
}
