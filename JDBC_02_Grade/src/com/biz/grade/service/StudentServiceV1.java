package com.biz.grade.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.utils.DBContarct;

public class StudentServiceV1 extends StudentService {

	@Override
	public int insert(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentDTO> selectAll() {
		
		return null;
	}

	@Override
	public StudentDTO findById(String num) {
		
		this.dbConnection();
		PreparedStatement pStr=null;
		String sql = DBContarct.SQL.STUDENT_SELECT;
		sql += " WHERE ST_NUM = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, num);
			ResultSet rst = pStr.executeQuery();
			
			if(rst.next()) {
				StudentDTO sDTO = this.rstTOdto(rst);
				return sDTO;
			}
			rst.close();
			dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentDTO> findByName(String name) {
		this.dbConnection();
		PreparedStatement pStr = null;
		
		String sql = DBContarct.SQL.STUDENT_SELECT;
		sql += " WHERE st_name = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			ResultSet rst = pStr.executeQuery();
			
			
			// stdList를 생성을 해버리면 stdList가 null이 아니다.
			List<StudentDTO> stdList =  new ArrayList<StudentDTO>();
			
			while(rst.next()) {
				stdList.add(this.rstTOdto(rst));
//						StudentDTO.builder()
//						.st_num(rst.getString("st_num"))
//						.st_name(rst.getString("st_name"))
//						.st_addr(rst.getString("st_addr"))
//						.st_grade(rst.getInt("st_grade"))
//						.st_tel(rst.getString("st_tel"))
//						.st_dept(rst.getString("st_dept"))
//						.build()
						
			}
			rst.close();
			dbConn.close();
			return stdList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<StudentDTO> findBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private StudentDTO rstTOdto(ResultSet rst) throws SQLException {
		return StudentDTO.builder()
		.st_num(rst.getString("st_num"))
		.st_name(rst.getString("st_name"))
		.st_addr(rst.getString("st_addr"))
		.st_grade(rst.getInt("st_grade"))
		.st_tel(rst.getString("st_tel"))
		.st_dept(rst.getString("st_dept"))
		.build();
	}

}
