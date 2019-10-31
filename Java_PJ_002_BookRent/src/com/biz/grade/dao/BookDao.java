package com.biz.grade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.grade.persistence.BookDTO;

public interface BookDao {

	public String getMaxBookCode();
	
	public List<BookDTO> selectAll();
	public BookDTO findById(String b_code);
	public List<BookDTO> findByName(String b_name);
	public List<BookDTO> findByNameLike(String b_name);
	public List<BookDTO> findByAutherLike(String b_name);
	public List<BookDTO> findByNameAuther(@Param("b_name") String b_name, @Param("b_auther") String b_auther);
	
	public int insert(BookDTO bookDTO);
	public int update(BookDTO bookDTO);
	
	
}
