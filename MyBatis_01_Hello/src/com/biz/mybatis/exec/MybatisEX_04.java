package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDao;
import com.biz.mybatis.persistence.BookDTO;

public class MybatisEX_04 {

	public static void main(String[] args) {
		
		
		/*
		 * JDBC의 다양한 클래스를 대신하여 java 어플리케이션과 DMBS간의 연결Connection을 대신 관리해줄 클래스
		 * 그 클래스를 이용하여 객체를 선언함.
		 * 
		 * session
		 * 네트워크 환경에서 지점과 지점사이가 다양한 방법으로 연결되고 데이터를 주고받을 준비가 된 통로
		 */
		
		SqlSession sqlSession = DBConnection.getSqlSessionFacotry().openSession(true);
		
		BookDao bookDao = sqlSession.getMapper(BookDao.class);
		
		String[] codes = {"B0011", "B0012", "B0013","B0016","B0017","B0018"};
		
		for(String code : codes) {
			BookDTO bookDTO = BookDTO.builder().
					b_code(code).
					b_comp("이지스퍼블리싱").
					b_name(code+"-"+(int)(Math.random()*10)).
					b_writer("박은종").
					b_price(15000).
					build();
			bookDao.update(bookDTO);
		}
		

		
		
		List<BookDTO> bookList = bookDao.selectAll();
		for(BookDTO dto : bookList) {
			System.out.println(dto);
		}
	}

}
