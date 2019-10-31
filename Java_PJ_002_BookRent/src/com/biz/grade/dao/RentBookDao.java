package com.biz.grade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.grade.persistence.RentBookDTO;

public interface RentBookDao {

	public List<RentBookDTO> selectAll();
	public RentBookDTO findById(int seq);
	
	public List<RentBookDTO> checkRent(@Param("rent_bcode") String rent_bcode);
	public List<RentBookDTO> noReturnBookCheck();
	
	public int insert (RentBookDTO rentBookDTO);
	public int update (RentBookDTO rentBookDTO);
	public int delete (int seq);
}
