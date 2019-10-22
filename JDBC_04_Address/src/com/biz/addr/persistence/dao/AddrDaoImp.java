package com.biz.addr.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.addr.persistence.AddrDTO;

import oracle.jdbc.proxy.annotation.Pre;

public class AddrDaoImp extends AddrDao {

	@Override
	public List<AddrDTO> selectall() {
		PreparedStatement pStr = null;
		String sql = " SELECT ID, " + 
					"NAME, " + 
					"TEL, " + 
					"ADDR, " + 
					"CHAIN "
				+ "FROM tbl_addr ";
		try {
			pStr = dbConn.prepareStatement(sql);
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> AddrList = new ArrayList<AddrDTO>();
			
			while(rst.next()) {
				AddrList.add(this.rst_2_dto(rst));
			}
			rst.close();
			pStr.close();
			return AddrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	private AddrDTO rst_2_dto(ResultSet rst) throws SQLException {
		return AddrDTO.builder().id(rst.getLong("id"))
		.name(rst.getString("name"))
		.tel(rst.getString("tel"))
		.addr(rst.getString("addr"))
		.chain(rst.getString("chain"))
		.build();
	}

	@Override
	public AddrDTO findById(long id) {
		PreparedStatement pStr = null;
		String sql = " SELECT ID, " + 
				"NAME, " 	+ 
				"TEL, " 	+ 
				"ADDR, " 	+ 
				"CHAIN " 	+
				"FROM tbl_addr ";
		sql += " WHERE id = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			ResultSet rst = pStr.executeQuery();
			
			AddrDTO addrDTO = null;
			if(rst.next()) {
				addrDTO = this.rst_2_dto(rst);
			}
			rst.next();
			pStr.close();
			return addrDTO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<AddrDTO> findByName(String name) {
		PreparedStatement pStr = null;
		String sql = " SELECT ID, " + 
				"NAME, " 	+ 
				"TEL, " 	+ 
				"ADDR, " 	+ 
				"CHAIN " 	+
				"FROM tbl_addr ";
		sql += " WHERE name = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> AddrList = new ArrayList<AddrDTO>();
			
			while(rst.next()) {
				AddrList.add(this.rst_2_dto(rst));
			}
			rst.close();
			pStr.close();
			return AddrList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
		

	@Override
	public List<AddrDTO> findByTel(String tel) {
		PreparedStatement pStr = null;
		String sql = " SELECT ID, " + 
				"NAME, " 	+ 
				"TEL, " 	+ 
				"ADDR, " 	+ 
				"CHAIN " 	+
				"FROM tbl_addr ";
		sql += " WHERE tel = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, tel);
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> AddrList = new ArrayList<AddrDTO>();
			
			while(rst.next()) {
				AddrList.add(this.rst_2_dto(rst));
			}
			rst.close();
			pStr.close();
			return AddrList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AddrDTO> findByChain(String chain) {
		PreparedStatement pStr = null;
		String sql = " SELECT ID, " + 
				"NAME, " 	+ 
				"TEL, " 	+ 
				"ADDR, " 	+ 
				"CHAIN " 	+
				"FROM tbl_addr ";
		sql += " WHERE chain = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, chain);
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> AddrList = new ArrayList<AddrDTO>();
			
			while(rst.next()) {
				AddrList.add(this.rst_2_dto(rst));
			}
			rst.close();
			pStr.close();
			return AddrList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int insert(AddrDTO addrDTO) {
		PreparedStatement pStr =null;
		String sql = " INSERT INTO tbl_addr ";
		sql += 	" ( ID, "		+
				" NAME, " 	+ 
				" TEL, " 	+ 
				" ADDR, " 	+ 
				" CHAIN ) ";
		sql += " VALUES ( SEQ_ADDR.NEXTVAL, ";
		sql += " ?,?,?,? )";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, addrDTO.getName());
			pStr.setString(2, addrDTO.getTel());
			pStr.setString(3, addrDTO.getAddr());
			pStr.setString(4, addrDTO.getChain());
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
	public int delete(long id) {
		PreparedStatement pStr = null;
		String sql = " DELETE FROM tbl_addr ";
		sql += " WHERE id = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			int ret = pStr.executeUpdate();
			pStr.close();
			return ret;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	@Override
	public int update(AddrDTO addrDTO) {
		PreparedStatement pStr =null;
		String sql = " UPDATE tbl_addr SET";
		sql += 	" NAME = ?, " 	+ 
				" TEL = ?,  " 	+ 
				" ADDR = ?, " 	+ 
				" CHAIN = ? ";
		sql += " WHERE id = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1,addrDTO.getName());
			pStr.setString(2,addrDTO.getTel());
			pStr.setString(3,addrDTO.getAddr());
			pStr.setString(4,addrDTO.getChain());
			pStr.setLong(5,addrDTO.getId());
			
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
