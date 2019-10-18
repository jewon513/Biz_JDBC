package com.biz.grade.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.StudentDao;
import com.biz.grade.persistence.domain.StudentDTO;

/*
 * StudentDao를 상속받아서 코드를 구체화 하는 실행 class
 */
public class StudentDaoImp extends StudentDao {

	@Override
	public List<StudentDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO findById(String st_num) {
		PreparedStatement pStr = null;
		String sql =  DBContract.SQL.SELECT_STUDENT;
		sql += " WHERE st_num = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, st_num.toUpperCase());
			ResultSet rst = pStr.executeQuery();
			StudentDTO stdDTO = null;
			if(rst.next()) {
				stdDTO = this.rst_2DTO(rst);
			}
			rst.close();
			pStr.close();
			return stdDTO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private StudentDTO rst_2DTO(ResultSet rst) throws SQLException {
		return StudentDTO.builder().st_num(rst.getString("st_num"))
												.st_name(rst.getString("st_name"))
												.st_grade(rst.getInt("st_grade"))
												.st_dept(rst.getString("st_dept"))
												.st_tel(rst.getString("st_tel"))
												.st_addr(rst.getString("st_addr"))
												.build();
		
	}

	@Override
	public List<StudentDTO> findByname(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDTO> findByGrade(int grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(StudentDTO stdDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(StudentDTO stdDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String st_num) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
