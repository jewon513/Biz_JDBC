package com.biz.grade.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.SubjectDao;
import com.biz.grade.persistence.domain.StudentDTO;
import com.biz.grade.persistence.domain.SubjectDTO;

public class SubjectDaoImp extends SubjectDao {

	@Override
	public List<SubjectDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectDTO findByid(String s_code) {
		
		PreparedStatement pStr = null;
		String sql =  DBContract.SQL.SELECT_SUBJECT;
		sql += " WHERE s_code = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, s_code.toUpperCase());
			ResultSet rst = pStr.executeQuery();
			SubjectDTO subDTO = null;
			if(rst.next()) {
				subDTO = this.rst_2DTO(rst);
			}
			rst.close();
			pStr.close();
			return subDTO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private SubjectDTO rst_2DTO(ResultSet rst) throws SQLException {
		return SubjectDTO.builder().s_code(rst.getString("s_code"))
									.s_subject(rst.getString("s_subject"))
									.s_pro(rst.getString("s_pro"))
									.build();
	}

	@Override
	public List<SubjectDTO> findBySubject(String s_subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubjectDTO> findByPro(String s_pro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(SubjectDTO subjectDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SubjectDTO subjectDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String s_code) {
		// TODO Auto-generated method stub
		return 0;
	}

}
