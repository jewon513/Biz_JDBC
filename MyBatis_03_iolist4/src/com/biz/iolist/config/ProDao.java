package com.biz.iolist.config;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.persistence.ProductDTO;

public interface ProDao {

	public int getIprice(String p_code);
	public int getOprice(String p_code);
	
	public String getMaxPCode();
	
	public List<ProductDTO> selectAll();
	public ProductDTO findById(String p_code);
	public List<ProductDTO> findByName(String p_name);
	
	// 상품정보를 입력할때 완전히 일치하는 이름을 가진 상품이 있는가를 검사
	public ProductDTO findBySName(String p_name);
	
	public int insert(ProductDTO productDTO);
	public int update(ProductDTO productDTO);
	public int delete(String p_code);
	
	public List<ProductDTO> findByIPrice(@Param("sprice") int sprice, @Param("eprice") int eprice);
	

	
}
