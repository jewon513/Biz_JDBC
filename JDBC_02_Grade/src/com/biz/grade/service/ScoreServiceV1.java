package com.biz.grade.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.utils.DBContarct;

public class ScoreServiceV1 extends ScoreService {

	@Override
	public int insert(ScoreDTO scoreDTO) {
		// TODO
		this.dbConnection();
		PreparedStatement pStr = null;
		String sql = "INSERT INTO tbl_score ( ";
		sql += " S_ID," + " S_SCORE, " + " S_REMARK, " + " S_SUBJECT, " + " S_STD ) ";
		sql += " VALUES(?,?,?,?,?) ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, scoreDTO.getS_id());
			pStr.setInt(2, scoreDTO.getS_score());
			pStr.setString(3, scoreDTO.getS_remark());
			pStr.setString(4, scoreDTO.getS_subject());
			pStr.setString(5, scoreDTO.getS_std());
			
			// 만약 insert가 정상적으로 수행되면 ret > 0
			// 그렇지 않으면 ret은 0, 간혹 < 0의 값이 나타는 경우도 있다.
			int ret = pStr.executeUpdate();
			
			dbConn.close();
			
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<ScoreDTO> selectAll() {
		// TODO 여기는 모든 레코드를 조회하는 method
		this.dbConnection(); // dbConn이 생성된다.
		PreparedStatement pStr = null;
//		String sql = " SELECT s_id, s_std, s_subject, s_score, s_remark FROM ";
//		sql += DBContarct.TABLE.SCORE;

		try {
			pStr = dbConn.prepareStatement(DBContarct.SQL.SCORE_SELECT);
			ResultSet rst = pStr.executeQuery();
			List<ScoreDTO> scoreList = new ArrayList<ScoreDTO>();

			// ResultSet으로부터 데이터를 getter할 때 칼럼의 위치값(숫자)로 사용하던 것을
			// 칼럼의 이름으로 사용할 수 있다.
			while (rst.next()) {
				scoreList.add(ScoreDTO.builder().s_id(rst.getLong("s_id")).s_std(rst.getString("s_std"))
						.s_subject(rst.getString("s_subject")).s_score(rst.getInt("s_score"))
						.s_remark(rst.getString("s_remark")).build());
			}
			rst.close();
			dbConn.close();

			return scoreList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ScoreDTO findById(long id) {
		// TODO primary key인 id를 넘겨 받아 한개의 레코드만 넘겨준다
		this.dbConnection();
		PreparedStatement pStr = null;

		String sql = DBContarct.SQL.SCORE_SELECT;
		sql += " WHERE s_id = ?";

		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			ResultSet rst = pStr.executeQuery();
			ScoreDTO scoreDTO = null;
			if (rst.next()) {
				scoreDTO = ScoreDTO.builder().s_id(rst.getLong("s_id")).s_std(rst.getString("s_std"))
						.s_score(rst.getInt("s_score")).s_subject(rst.getString("s_subject"))
						.s_remark(rst.getString("s_remark")).build();

			}
			rst.close();
			dbConn.close();
			return scoreDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ScoreDTO> findByName(String name) {
		this.dbConnection();
		PreparedStatement pStr = null;

		String sql = DBContarct.SQL.SCORE_SELECT;
		sql += " WHERE s_std = ?";

		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			ResultSet rst = pStr.executeQuery();

			List<ScoreDTO> scoreList = new ArrayList<ScoreDTO>();

			while (rst.next()) {
				scoreList.add(ScoreDTO.builder().s_id(rst.getLong("s_id")).s_std(rst.getString("s_std"))
						.s_subject(rst.getString("s_subject")).s_score(rst.getInt("s_score"))
						.s_remark(rst.getString("s_remark")).build());
			}

			rst.close();
			dbConn.close();
			return scoreList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ScoreDTO> findBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ScoreDTO scoreDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
