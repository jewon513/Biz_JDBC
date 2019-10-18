package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.persistence.domain.DeptDTO;

public abstract class DeptDao {

	protected Connection dbConn = null;
	public DeptDao() {
		this.dbConn = DBConnection.getDBConnection();
	}
	
	//조건없이 모든 데이터를 조회하는 method
	//List<> 형태의 데이터를 return;
	public abstract List<DeptDTO> selectAll();
	
	//PK를 조건으로 데이터를 조회하는 method
	//출력되는 것은 1개의 레코드이기 떄문에 StudentDTO로 설정
	public abstract DeptDTO findByid (String d_num);
	
	//학생이름으로 조회
	//리스트로 return
	public abstract List<DeptDTO> findByname (String d_name);
	
	//데이터 insert
	public abstract int insert(DeptDTO deptDTO);
	
	//데이터 update
	public abstract int update(DeptDTO deptDTO);
	
	//pk로 조건을 걸어서 1개의 레코드를 삭제
	public abstract int delete(String d_num);
	
}
