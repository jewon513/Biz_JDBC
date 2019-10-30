package com.biz.iolist.config;

import java.util.List;

import com.biz.iolist.persistence.IolistVO;

public interface IolistViewDao {

	public List<IolistVO> selectAll();
	public IolistVO findById(long io_seq);
	
	public List<IolistVO> findByDCode(String io_dcode);
	public List<IolistVO> findByPCode(String io_pcode);
	
	public List<IolistVO> findByPName(String p_name);
	public List<IolistVO> findByDName(String d_name);
	
}
