package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

public class ScoreJDBCService3 {
	
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
		this.dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			this.setScoreList(pStr);
			dbConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.scoreList;
		
	}
	
	public List<ScoreVO> findById(int s_id){
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_id = ?";
		
		this.dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setInt(1, s_id);
			
			this.setScoreList(pStr);
			dbConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.scoreList;
	}
	
	// 아이디 값을 2개 전달받아서 범위 검색을 수행하는 method
	public List<ScoreVO> findById(int s_id, int e_id){
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_id = BETWEEN ? AND ? ";  // ? => 대치문자열
		
		this.dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setInt(1, s_id);
			pStr.setInt(2, e_id);
			
			this.setScoreList(pStr);
			dbConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.scoreList;
	}
	
	public List<ScoreVO> findByName(String s_name){
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_std = ?";
		this.dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, s_name);
			this.setScoreList(pStr);
			dbConn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.scoreList;
	
	}
	
	
	// ResultSet에서 데이터를 추출하여 List로 변환
	public void setScoreList(PreparedStatement pStr) throws SQLException {
		scoreList = new ArrayList<ScoreVO>();
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
		
	}
	
	public int insert(ScoreVO scoreVO) {
		String sql = " INSERT INTO tbl_score ( ";
		sql+= 		 " s_id, ";
		sql+= 		 " s_std, ";
		sql+= 		 " s_subject, ";
		sql+= 		 " s_score, ";
		sql+= 		 " s_remark )";
		sql+= 		 " VALUES (?,?,001,?,?) ";
		
		this.dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, scoreVO.getS_id());
			pStr.setString(2, scoreVO.getS_std());
			pStr.setInt(3, scoreVO.getS_score());
			pStr.setString(4, scoreVO.getS_remark());
			
			int ret = pStr.executeUpdate();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
