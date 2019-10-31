package com.biz.grade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.grade.persistence.UserDTO;

public interface UserDao {

	public List<UserDTO> selectAll();
	public UserDTO findById(String u_code);
	public List<UserDTO> findByName(String u_name);
	public List<UserDTO> findByNameLike(String u_name);
	public String getMaxCode();
	public List<UserDTO> findByNameTel(@Param("u_name") String u_name, @Param("u_tel") String u_tel);
	
	public int insert(UserDTO userDTO);
	public int update(UserDTO userDTO);
	
}
