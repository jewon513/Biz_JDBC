package com.biz.iolist.service.iolist;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.DeptDao;
import com.biz.iolist.config.IolistDao;
import com.biz.iolist.config.IolistViewDao;
import com.biz.iolist.config.ProDao;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.IolistVO;

public class IolistServiceV1 {

	protected IolistDao iolistDao;
	protected ProDao proDao;
	protected DeptDao deptDao;
	protected IolistViewDao iolistViewDao;
	
	public IolistServiceV1() {
		
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		this.iolistDao = sqlSession.getMapper(IolistDao.class);
		this.proDao = sqlSession.getMapper(ProDao.class);
		this.deptDao = sqlSession.getMapper(DeptDao.class);
		this.iolistViewDao = sqlSession.getMapper(IolistViewDao.class);
		
	}
	
	public void viewAllList() {
		
		List<IolistVO> ioList = iolistViewDao.selectAll();
		for (IolistVO iolistDTO : ioList) {
			System.out.println(iolistDTO);
		}
		
	}
	
}
