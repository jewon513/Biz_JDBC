package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

public class ScoreJDBCService2 {
//	protected String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
//	protected String url = "jdbc:oracle:thin:@localHost:1521:xe";
//	protected String userName = "grade";
//	protected String password = "grade";
	
	protected Connection dbConn = null;
	protected PreparedStatement pStr = null;
	
	protected List<ScoreVO> scoreList = null;
	
	public List<ScoreVO> getScoreList(){
		return this.scoreList;
	}
	
	protected void dbConnection() {
		try {
			Class.forName(DBConstract.DB_INPO.jdbcDriver);
			dbConn = DriverManager.getConnection(DBConstract.DB_INPO.URL
												,DBConstract.DB_INPO.User
												,DBConstract.DB_INPO.PASSWORD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ScoreVO> selectAll(){
		
		String sql = " SELECT * FROM tbl_score ";
		
		this.select(sql);
		
		return this.scoreList;
		
	}
	
	public List<ScoreVO> findById(int s_id){
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_id = '" + s_id + "'";
		
		this.select(sql);
		return this.scoreList;
	}
	
	public List<ScoreVO> findByName(String s_name){
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_std = '" + s_name + "'";
		this.select(sql);
		return this.scoreList;
	}
	
	public void select(String sql) {
		this.dbConnection();
		scoreList = new ArrayList<ScoreVO>();
		
		try {
			pStr = dbConn.prepareStatement(sql);
			ResultSet rst = pStr.executeQuery();
			while(rst.next()) {
				ScoreVO vo = ScoreVO.builder().s_id(rst.getInt(DBConstract.SCORE.S_ID))
												.s_std(rst.getString(DBConstract.SCORE.S_STD))
												.s_score(rst.getInt(DBConstract.SCORE.S_SCORE))
												.s_remark(rst.getString(DBConstract.SCORE.S_REM))
												.build();
				scoreList.add(vo);
				
			}
			rst.close();
			dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
