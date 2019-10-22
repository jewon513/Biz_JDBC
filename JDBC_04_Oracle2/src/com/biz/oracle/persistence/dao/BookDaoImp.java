package com.biz.oracle.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.oracle.config.DBContract;
import com.biz.oracle.persistence.BookDTO;

public class BookDaoImp extends BookDao {

	@Override
	public List<BookDTO> selectAll() {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_BOOKS;
		try {
			// sql 문자열을 JDBC 드라이버를 통해 DBMS로 전송하기 위한 데이터형으로 변환하는 절차
			pStr = dbConn.prepareStatement(sql);
			
			// DBMS가 보낸 결과를 ResultSet에 받기
			ResultSet rst = pStr.executeQuery();
			
			//rst가 받은 데이터가 최소 1개 이상의 record이면 rst.next() method는 결과값이 true가 되고
			//rst 내부에서는 record 값을 추출할 수 있는 상태가 된다.
			
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			
			
			// 반복문 내에서 rst.next()를 실행하여 record가 있는가를 검사하고
			// 있으면 while() {} 내의 코드를 실행하여 값을 추출
			while(rst.next()) {
				bookList.add(this.rst_2_DTO(rst));
			}
			
			rst.close();
			pStr.close();
			return bookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private BookDTO rst_2_DTO(ResultSet rst) throws SQLException {
		BookDTO bookDTO = BookDTO.builder()
				.b_code(rst.getString("b_code"))
				.b_name(rst.getString("b_name"))
				.b_comp(rst.getString("b_comp"))
				.b_writer(rst.getString("b_writer"))
				.b_price(rst.getInt("b_price"))
				.build();
		return bookDTO;
	}
	
	@Override
	public BookDTO findById(String b_code) {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_BOOKS;
		sql += " WHERE b_code = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, b_code);
			ResultSet rst = pStr.executeQuery();
			
			BookDTO bookDTO = null;
			if(rst.next()) {
				bookDTO = this.rst_2_DTO(rst);
			}
			rst.close();
			pStr.close();
			return bookDTO;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<BookDTO> findByName(String b_name) {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_BOOKS;
		sql += " WHERE b_name LIKE '%' || ? || '%' ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, b_name);
			ResultSet rst = pStr.executeQuery();
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			while(rst.next()) {
				bookList.add(this.rst_2_DTO(rst));
			}
			rst.close();
			pStr.close();
			return bookList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookDTO> findByComp(String b_comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByWriter(String b_writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int sPrice, int ePrice) {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_BOOKS;
		sql += " WHERE b_price BETWEEN ? AND ? ";
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setInt(1, sPrice);
			pStr.setInt(2, ePrice);
			ResultSet rst = pStr.executeQuery();
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			
			while(rst.next()) {
				bookList.add(this.rst_2_DTO(rst));
			}
			rst.close();
			pStr.close();
			return bookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int insert(BookDTO bookDTO) {
		PreparedStatement pStr = null;
		String sql = " INSERT INTO tbl_books ( ";
		sql += " B_CODE, ";  
		sql += " B_NAME, ";  
		sql += " B_COMP, "; 
		sql += " B_WRITER, ";  
		sql += " B_PRICE ) ";
		sql += " VALUES ( "
				+ " 'B'||LPAD(SEQ_BOOKS.NEXTVAL,4,'0'), "
				+ " ?, "
				+ " ?, "
				+ " ?, "
				+ " ? )";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, bookDTO.getB_name());
			pStr.setString(2, bookDTO.getB_comp());
			pStr.setString(3, bookDTO.getB_writer());
			pStr.setInt(4, bookDTO.getB_price());
			
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
	public int update(BookDTO bookDTO) {

		PreparedStatement pStr = null;
		String sql = " UPDATE TBL_BOOKS SET ";
		sql += " B_NAME = ?, ";
		sql += " B_COMP = ?, ";
		sql += " B_WRITER = ?, ";
		sql += " B_PRICE = ? ";
		sql += " WHERE b_code = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, bookDTO.getB_name());
			pStr.setString(2, bookDTO.getB_comp());
			pStr.setString(3, bookDTO.getB_writer());
			pStr.setInt(4, bookDTO.getB_price());
			pStr.setString(5, bookDTO.getB_code());
			
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
	public int delete(String b_code) {
		PreparedStatement pStr = null;
		String sql = " DELETE FROM tbl_books ";
		sql += " WHERE b_code = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, b_code);
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
