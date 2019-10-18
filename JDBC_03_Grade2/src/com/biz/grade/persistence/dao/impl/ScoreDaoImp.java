package com.biz.grade.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.ScoreDao;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;

public class ScoreDaoImp extends ScoreDao {

	@Override
	public List<ScoreVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreVO findById(long id) {
	
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " WHERE s_id = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			
			ResultSet rst = pStr.executeQuery();
			ScoreVO scoreVO = null;
			if(rst.next()) {
				scoreVO = this.rstTOScoreVO(rst);
			}
			rst.close();
			pStr.close();
			return scoreVO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	

	@Override
	public List<ScoreVO> findByStName(String stName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ScoreDTO scoreDTO) {
		
		PreparedStatement pStr = null;
		String sql =" INSERT INTO tbl_score ( "; 
		sql +=  " S_ID, "; 				
		sql +=  " S_SCORE, "; 			
		sql +=  " S_REMARK, "; 			
		sql +=	" S_SUBJECT, "; 			
		sql +=  " S_STD )";
		sql +=  " VALUES (?,?,?,?,?)";
		
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, scoreDTO.getS_id());
			pStr.setInt(2, scoreDTO.getS_score());
			pStr.setString(3, scoreDTO.getS_remark());
			pStr.setString(4, scoreDTO.getS_subject());
			pStr.setString(5, scoreDTO.getS_std());
			int  ret = pStr.executeUpdate();
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
				
	}

	@Override
	public int update(ScoreDTO scoreDTO) {
		
		PreparedStatement pStr = null;
		String sql = " UPDATE tbl_score SET ";
//		sql += " s_id = ?, ";
		sql += " s_score = ?, ";
		sql += " s_remark = ?, ";
		sql += " s_subject = ?, ";
		sql += " s_std = ? "; 
		sql += " WHERE s_id = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setInt(1, scoreDTO.getS_score());
			pStr.setString(2, scoreDTO.getS_remark());
			pStr.setString(3, scoreDTO.getS_subject());
			pStr.setString(4, scoreDTO.getS_std());
			pStr.setLong(5, scoreDTO.getS_id());
			
			int ret = pStr.executeUpdate();
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
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
		return null;
	}

}
