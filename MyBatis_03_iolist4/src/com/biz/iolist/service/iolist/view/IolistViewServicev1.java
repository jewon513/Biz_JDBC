package com.biz.iolist.service.iolist.view;

import java.util.List;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.IolistViewDao;
import com.biz.iolist.persistence.IolistVO;

public class IolistViewServicev1 {

	IolistViewDao ioViewDao;
	
	public IolistViewServicev1() {
		ioViewDao=DBConnection.getSqlSessionFactory().openSession(true).getMapper(IolistViewDao.class); 
	}
	
	protected void viewItem(IolistVO iolistVO) {
		System.out.print(iolistVO.getIo_seq() + "\t");
		System.out.print(iolistVO.getIo_date() +"\t");
		System.out.print(iolistVO.getIo_inout() +"\t");
		System.out.printf("(%s)%s",iolistVO.getIo_dcode(),iolistVO.getD_name() +"\t");
		System.out.printf("(%s)%s",iolistVO.getIo_pcode(),iolistVO.getP_name() +"\t");
		System.out.print(iolistVO.getIo_qty() +"\t");
		System.out.print(iolistVO.getIo_price() +"\t");
		System.out.print(iolistVO.getIo_total()+"\n");
	}
	
	protected void viewList(List<IolistVO> iolist) {
		System.out.println("========================================================================");
		System.out.println("매입매출정보");
		System.out.println("========================================================================");
		System.out.println("SEQ\t거래일자\t구분\t거래처\t상품\t수량\t단가\t합계");
		for (IolistVO iolistVO : iolist) {
			this.viewItem(iolistVO);
		}
		System.out.println("========================================================================");
		
	}
	
	public void viewAllList() {
		
		List<IolistVO> iolist = ioViewDao.selectAll();
		if(iolist != null && iolist.size()>0) {
			this.viewList(iolist);
		}
	}
	
	// 상품코드를 매개변수로 받아 리스트로 출력
	public void viewListByPCode(String pcode) {
		List<IolistVO> iolist = ioViewDao.findByPCode(pcode);
		if(iolist != null && iolist.size()>0) {
			this.viewList(iolist);
		}
	}
	
	//상품이름을 매개변수로 받아 리스트로 출력
	public void viewListByPName(String pname) {
		List<IolistVO> iolist = ioViewDao.findByPName(pname);
		if(iolist != null && iolist.size()>0) {
			this.viewList(iolist);
		}
	}
	
	//거래처코드 매개변수로 받아 리스트로 출력
	public void viewListByDCode(String dcode) {
		List<IolistVO> iolist = ioViewDao.findByDCode(dcode);
		if(iolist != null && iolist.size()>0) {
			this.viewList(iolist);
		}
	}
	
	//거래처명을 매개변수로 받아 리스트로 출력
	public void viewListByDName(String dname) {
		List<IolistVO> iolist = ioViewDao.findByDName(dname);
		if(iolist != null && iolist.size()>0) {
			this.viewList(iolist);
		}
	}
	
	
}
