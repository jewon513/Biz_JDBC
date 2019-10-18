package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
/*
 * 	Service 클래스
 *  main()에서 호출하여 다양한 연산을 수행하는 용도
 *  file읽기, 쓰기, 읽은 후 연산처리
 *  JDBC를 연결하여 데이터 연동
 *  
 *  Dao Class
 *  	Data Access Object (데이터베이스 접근 객체)
 *  	Service 클래스의 연산기능중에서 순수하게 JDBC와 연동하여 직접 DB를 읽고(SELECT)
 *  	DB를 UPDATE(INSERT, UPDATE, DELETE) 수행하는 기능을 Service로부터 분리한 것
 * 		이제부터 Service 클래스는 비즈니스 로직만 담당하는 역할을 수행
 * 
 * 	비즈니스로직
 * 		사용자로부터 어떤 데이터를 입력받고, 결과를 보여주는 용도
 * 		main()과 Dao 클래스사이에서 연산을 주도적으로 수행한다.
 * 		mian()에서 입력된 데이터를 -->Service에서 받아서 가공, 검증등을 수행하고 -->Dao에게 보내서 UPDATE
 * 		
 *  main()에서 명령실행을 하면
 *  
 * 	Dao에서 SELECT한 Data --> Service에서 다양한 방법으로 가공 View를 수행
 * 	
 */
public abstract class ScoreDao {

	protected Connection dbConn = null;
	
	//ScoreServiceV2 생성자
	public ScoreDao() {
		this.dbConn = DBConnection.getDBConnection();
	}
	
	public abstract List<ScoreVO> selectAll();
	public abstract ScoreVO findById(long id);
	
	//학생이름으로 검색하기
	public abstract List<ScoreVO> findByStName(String stName);
	
	public abstract int insert(ScoreDTO scoreDTO);
	public abstract int update(ScoreDTO scoreDTO);
	public abstract int delete(long id);

	public abstract List<ScoreVO> findByStNum(String strStNum);

	public abstract List<ScoreVO> findBySubject(String strSubject);
	
	
}
