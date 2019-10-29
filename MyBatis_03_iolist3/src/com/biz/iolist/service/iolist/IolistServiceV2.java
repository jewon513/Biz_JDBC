package com.biz.iolist.service.iolist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.DeptDao;
import com.biz.iolist.config.IolistDao;
import com.biz.iolist.config.IolistViewDao;
import com.biz.iolist.config.ProDao;
import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.IolistVO;
import com.biz.iolist.persistence.ProductDTO;

public class IolistServiceV2 {

	protected IolistDao iolistDao;
	protected ProDao proDao;
	protected DeptDao deptDao;
	protected IolistViewDao iolistViewDao;
	protected Scanner scan;
	
	public IolistServiceV2() {
		
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		this.iolistDao = sqlSession.getMapper(IolistDao.class);
		this.proDao = sqlSession.getMapper(ProDao.class);
		this.deptDao = sqlSession.getMapper(DeptDao.class);
		this.iolistViewDao = sqlSession.getMapper(IolistViewDao.class);
		scan = new Scanner(System.in);
		
	}
	
	public void viewAllList() {
		
		List<IolistVO> ioList = iolistViewDao.selectAll();
		for (IolistVO iolistDTO : ioList) {
			System.out.println(iolistDTO);
		}
		
	}
		
	public void iolistInsert() {
		
		//SEQ 자동생성
		long intSEQ = iolistDao.getMaxSEQ();
		
		IolistDTO vo = iolistDao.findById(intSEQ);
		
		//DATE 현재 날짜 IO_DATE 
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat cd = new SimpleDateFormat("YYYY-MM-DD");
		String curDate = cd.format(date);
	
		
		//거래처를 이름으로 검색해서 LIST 보여주고
		//IO_DCODE 입력
		// DCODE가 tbl_dept에 존재하는지 확인
		// 있으면 dept detail을 보여주고 다음으로 넘어가기
		System.out.print("거래처 이름을 입력하세요 >> ");
		String d_name = scan.nextLine();
		List<DeptDTO> deptlist = deptDao.findByName(d_name);
		for (DeptDTO deptDTO : deptlist) {
			System.out.println(deptDTO);
		}
		
		String d_code;
		while(true) {
			System.out.print("거래처 코드를 입력하세요 >> ");
			d_code = scan.nextLine();
			DeptDTO deptDTO = deptDao.findById(d_code);
			
			if(deptDTO==null) {
				System.out.println("거래처 명단에 존재하지 않는 코드입니다.");
				continue;
			}else {
				this.deptViewDetail(deptDTO);
			}
			break;
		}
		
		//상품들을 이름으로 검색해서 LIST 보여주고
		//IO_PCODE 입력
			//	PCODE가 tbl_product에 존재하는지 확인
			//	있으면 product detail을 보여주고 다음으로 넘어가기
		
		System.out.print("상품명을 입력하세요 >> ");
		String p_name = scan.nextLine();
		List<ProductDTO> prolist = proDao.findByName(p_name);
		for (ProductDTO productDTO : prolist) {
			System.out.println(productDTO);
		}
		
		String p_code;
		while(true) {
			System.out.print("상품코드를 입력하세요 >>");
			p_code = scan.nextLine();
			ProductDTO proDTO = proDao.findById(p_code);
			
			if(proDTO==null) {
				System.out.println("상품 목록에 존재하지 않는 코드입니다.");
				continue;
			}else {
				this.proViewDetail(proDTO);
			}
			break;
			
		}
		
		//매입인지 매출인지 입력
			//매입이면 매입단가 가져오기
			//매출이면 매출단가 가져오기
	
		String p_vat;
		while(true) {
			System.out.print("매입인지 매출인지 선택하세요 (1 : 매출, 2 : 매입 ) >> ");
			String select = scan.nextLine();
			try {
				int intSelect = Integer.valueOf(select);
				if(intSelect == 1) {
					p_vat = "매입";
				}else if(intSelect == 2) {
					p_vat = "매출";
				}else {
					System.out.println("1또는 2만 입력해주세요.");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요! (1 또는 2)");
				continue;
				// TODO: handle exception
			}
		}
		
		//수량 입력하기
		int qty;
		while(true) {
			System.out.print("상품의 수량을 입력해주세요 >> ");
			String strqty = scan.nextLine();
			try {
				qty = Integer.valueOf(strqty);
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요");
				continue;
				// TODO: handle exception
			}
		}
		
		//매입합계 또는 매출합계를 계산하기.
		
		
		
		//insert
		
		
		
	}
	
	protected void deptViewDetail(DeptDTO deptDTO) {
		System.out.println("====================================================");
		System.out.printf("%s", deptDTO.getD_code() + "\t\t");
		System.out.printf("%s", deptDTO.getD_name() + "\t\t\t");
		System.out.printf("%s", deptDTO.getD_ceo() + "\t\t");
		System.out.printf("%s", deptDTO.getD_tel() + "\t");
		System.out.printf("%s", deptDTO.getD_addr() + "\n");
		System.out.println("====================================================");
	}
	
	protected void proViewDetail(ProductDTO proDTO) {
		System.out.println("====================================================");
		System.out.printf("상품코드 : %s\n", proDTO.getP_code());
		System.out.printf("상품이름 : %s\n", proDTO.getP_name());
		System.out.printf("매입단가 : %d\n", proDTO.getP_iprice());
		System.out.printf("매출단가 : %d\n", proDTO.getP_oprice());
		
		String strVAT ="";
		try {
			strVAT = proDTO.getP_vat().equals("1") ? "과세" : "면세";	
		} catch (Exception e) {
			strVAT = "";
		}
		System.out.printf("과세여부 : %s\n", strVAT);
		System.out.println("====================================================");
		
	}
}
