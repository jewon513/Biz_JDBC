package com.biz.grade.Service.extend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.Service.ScoreServiceV2;
import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;

public class ScoreServiceV2Ext extends ScoreServiceV2 {

	@Override
	public List<ScoreVO> selectAll() {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			ResultSet rst = pStr.executeQuery();
			List<ScoreVO> scoreList = new ArrayList<ScoreVO>();
			
			while(rst.next()) {
				scoreList.add(this.rstTOScoreVO(rst));
			}
			rst.close();
			pStr.close();
			return scoreList;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private ScoreVO rstTOScoreVO(ResultSet rst) throws SQLException {
		ScoreVO vo = ScoreVO.builder()
		.s_id(rst.getLong("s_id"))
		.s_score(rst.getInt("s_score"))
		.s_std(rst.getString("s_std"))
		.s_subject(rst.getString("s_subject"))
		.sb_name(rst.getString("sb_name"))
		.st_dept(rst.getNString("st_dept"))
		.st_grade(rst.getInt("st_grade"))
		.st_name(rst.getString("st_name"))
		.d_name(rst.getString("d_name"))
		.d_tel(rst.getString("d_tel"))
		.build();
		
		return vo;
	}
	
	@Override
	public ScoreVO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ScoreVO> findByStName(String stName) {
		// TODO stName을 매개변수로 받아서 학생이름으로 검색한 다음에 List<ScoreVO>로 return
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " WHERE st_name = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, stName);
			ResultSet rst = pStr.executeQuery();
			List<ScoreVO> scoreList = new ArrayList<ScoreVO>();
			while(rst.next()) {
				scoreList.add(this.rstTOScoreVO(rst));
			}
			rst.close();
			pStr.close();
			return scoreList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<ScoreVO> findByStNum(String strStNum) {
		// TODO strStNum을 매개변수로 받아서 학번으로 검색
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " WHERE s_std = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1,strStNum);
			ResultSet rst = pStr.executeQuery();
			
			List<ScoreVO> scList = new ArrayList<ScoreVO>();
			while(rst.next()) {
				scList.add(this.rstTOScoreVO(rst));
			}
			rst.close();
			pStr.close();
			return scList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
		
	}
	
	@Override
	public List<ScoreVO> findBySubject(String strSubject) {
		// TODO Auto-generated method stub
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " WHERE s_subject = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, strSubject);
			ResultSet rst = pStr.executeQuery();
			
			List<ScoreVO> scList = new ArrayList<ScoreVO>();
			while(rst.next()) {
				scList.add(this.rstTOScoreVO(rst));
			}
			rst.close();
			pStr.close();
			return scList;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int insert(ScoreDTO scoreDTO) {
		PreparedStatement pStr = null;
		String sql =" INSERT INTO tbl_score ( S_ID, " 		+
					" S_STD, "			+
					" S_SUBJECT, " 		+ 
					" S_SCORE, " 		+ 
					" S_REMARK ) " 		+
					" VALUES (?, ?, ?, ?, ?)";
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setLong(1, scoreDTO.getS_id());
			pStr.setString(2, scoreDTO.getS_std());
			pStr.setString(3, scoreDTO.getS_subject());
			pStr.setInt(4, scoreDTO.getS_score());
			pStr.setString(5, scoreDTO.getS_remark());
			
			// CRUD 중에서 CUD(INSERT, UPDATE, DELETE는 executeUpdate()를 사용한다.
			// 쿼리가 정상 수행되면 ret은 0보다 큰 값을 갖는다.
			int ret = pStr.executeUpdate();
			pStr.close();
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public int update(ScoreDTO scoreDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		PreparedStatement pStr = null;
		String sql = " DELETE FROM tbl_score ";
		sql += " WHERE s_id = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			int ret = pStr.executeUpdate();
			
			pStr.close();
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	

	

	

}
