package com.biz.addr.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.addr.config.DBConnection;
import com.biz.addr.persistence.AddrDTO;

public abstract class AddrDao {

	protected Connection dbConn = null;
	
	public AddrDao() {
		dbConn = DBConnection.getConnection();
	}
	
	public abstract List<AddrDTO> selectall();
	public abstract AddrDTO findById(long id);
	public abstract List<AddrDTO> findByName(String name);
	public abstract List<AddrDTO> findByTel(String tel);
	public abstract List<AddrDTO> findByChain(String chain);
	
	public abstract int insert(AddrDTO addrDTO);
	public abstract int delete(long id);
	public abstract int update(AddrDTO addrDTO);
}
