package com.biz.iolist.service.iolist;

import java.text.ParseException;
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
import com.biz.iolist.service.dept.DeptServiceV3;
import com.biz.iolist.service.iolist.view.IolistViewServicev1;
import com.biz.iolist.service.pro.ProductServiceV4;

public class IolistServiceV3 {

	protected IolistDao iolistDao;
	protected ProDao proDao;
	protected DeptDao deptDao;
	protected IolistViewDao iolistViewDao;
	protected Scanner scan;
	
	protected IolistViewServicev1 ioView;
	protected ProductServiceV4 proService;
	protected DeptServiceV3 deptService;
	
	public IolistServiceV3() {
		
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		this.iolistDao = sqlSession.getMapper(IolistDao.class);
		this.proDao = sqlSession.getMapper(ProDao.class);
		this.deptDao = sqlSession.getMapper(DeptDao.class);
		this.iolistViewDao = sqlSession.getMapper(IolistViewDao.class);
		ioView = new IolistViewServicev1();
		scan = new Scanner(System.in);
		proService = new ProductServiceV4();
		deptService = new DeptServiceV3();
		
	}
	
	public void viewAllList() {
		
		List<IolistVO> ioList = iolistViewDao.selectAll();
		for (IolistVO iolistDTO : ioList) {
			System.out.println(iolistDTO);
		}
		
	}
	
	public void iolistMenu() {
		
		while(true) {
			System.out.println("====================================================");
			System.out.println("새나라 마트 매입매출 관리 V1");
			System.out.println("====================================================");
			System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
			System.out.println("업무 선택 >> ");
			String strMenu = scan.nextLine();
			int intMenu = 0;
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			if(intMenu == 0) {
				break;
			}else if(intMenu == 1) {
				this.insert();
			}else if(intMenu == 2) {
				this.update();
			}else if(intMenu == 3) {
				this.delete();
			}else if(intMenu == 4) {
				this.search();
			}
		}
		
		
	}

		
	protected void search() {
		// TODO Auto-generated method stub
		
	}

	protected void delete() {
		// TODO Auto-generated method stub
		
	}

	protected void update() {
		// TODO Auto-generated method stub
		
	}

	protected void insert() {
		// TODO 매입매출 등록 method
		System.out.println("================================================");
		System.out.println("매입매출 등록");
		System.out.println("================================================");
		
		IolistDTO iolistDTO = new IolistDTO();
		
		while(true) {
			System.out.println("거래구분 입력(1:매입, 2:매출 -1:종료) >>");
			String strInout = scan.nextLine();
			try {
				int intInout = Integer.valueOf(strInout);
				if(intInout<0) break;
				
				if(intInout == 1) {
					iolistDTO.setIo_inout("매입");
				}else if(intInout == 2) {
					iolistDTO.setIo_inout("매출");
				}else {
					System.out.println("다시 입력해주세요");
					continue;
				}
				
				strInout = intInout == 1 ? "매입" : "매출";
				iolistDTO.setIo_inout(strInout);
				
			}catch(Exception e) {
				System.out.println("매입 매출 구분을 다시 입력해주세요.");
				continue;
			}
			break;	
		}
		
		if(iolistDTO.getIo_inout().isEmpty()) return;
		
		
		while(true) {
			
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			String curDate = sd.format(date);
			
			System.out.printf("거래일자(%s)", curDate);
			String strDate = scan.nextLine();
			if(strDate.trim().isEmpty()) {
				iolistDTO.setIo_date(curDate);
			}else {
				
				try {
					sd.parse(strDate);
				} catch (ParseException e) {
					System.out.println("날짜형식이 잘못되었습니다.");
					continue;
					// TODO: handle exception
				}
				
				iolistDTO.setIo_date(strDate);
			}
			
			break;
		}
		
		while(true) {
			System.out.println("거래처명 입력 (-Q:quit) >> ");
			String strDName = scan.nextLine();
			
			if(strDName.equals("-Q")) break;
			
			List<DeptDTO> deptList = deptDao.findByName(strDName);
			if(deptList != null && deptList.size()>0) {
				System.out.println("=============================");
				for (DeptDTO deptDTO : deptList) {
					System.out.println(deptDTO);
				}
				System.out.println("=============================");
				System.out.println("거래처코드 입력 >> ");
				String strDcode = scan.nextLine();
				
				DeptDTO deptDTO = deptDao.findById(strDcode);
				if(deptDTO==null) {
					System.out.println("거래처 코드가 없습니다.");
					continue;
				}else {
					iolistDTO.setIo_dcode(strDcode);
				}
			}else {
				continue;
			}
			break;
		}
		
		if(iolistDTO.getIo_dcode().isEmpty()) return;
		
		while(true) {
			System.out.println("상품명 입력");
			String strPName = scan.nextLine();
			List<ProductDTO> proList = proDao.findByName(strPName);
			
			if(proList == null || proList.size()<1) {
				System.out.println("찾는 상품이 없음");
				continue;
			}else {
				for (ProductDTO productDTO : proList) {
					System.out.println(productDTO);
				}
				System.out.println("=============================");
				System.out.println("상품코드 입력 (-Q:quit) >> ");
				String strPCode = scan.nextLine();
				
				if(strPCode.equals("-Q")) break;
				
				ProductDTO proDTO = proDao.findById(strPCode);
				if(proDTO ==null) {
					System.out.println("상품 코드가 없습니다.");
					continue;
				}else {
					iolistDTO.setIo_pcode(strPCode);
					int intPrice = iolistDTO.getIo_inout().equals("매입") ? proDTO.getP_iprice() : proDTO.getP_oprice();
					iolistDTO.setIo_price(intPrice);
				}
			}
			
			break;
		}
		
		if(iolistDTO.getIo_pcode().isEmpty()) return;
		
		
		while(true) {
			System.out.printf("단가입력(%d) >> " , iolistDTO.getIo_price());
			String strPrice = scan.nextLine();
			
			try {
				int price = Integer.valueOf(strPrice);
				iolistDTO.setIo_price(price);
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		}
		
		while(true) {
			System.out.println("수량입력 >>");
			String strQty = scan.nextLine();
			
			try {
				int intQty = Integer.valueOf(strQty);
				iolistDTO.setIo_qty(intQty);
			} catch (Exception e) {
				System.out.println("수량은 숫자로만 입력!!");
				// TODO: handle exception
			}
			
			break;
		}
		
		int sum = iolistDTO.getIo_price() * iolistDTO.getIo_qty();
		iolistDTO.setIo_total(sum);
		
		int ret = iolistDao.insert(iolistDTO);
		if(ret >0 ) {
			System.out.println("데이터 추가 완료");
		}else {
			System.out.println("데이터 추가 실패");
		}
		
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
	
	protected void iolistViewDetail(IolistDTO iolistDTO) {
		System.out.println("====================================================");
		System.out.printf("SEQ : %d\n", iolistDTO.getIo_seq());
		System.out.printf("날짜 : %s\n", iolistDTO.getIo_date());
		System.out.printf("거래처 코드 :%s\n", iolistDTO.getIo_dcode());
		System.out.printf("구분 : %s\n", iolistDTO.getIo_inout());
		System.out.printf("상품 코드 :%s\n", iolistDTO.getIo_pcode());
		System.out.printf("상품 가격 :%d\n", iolistDTO.getIo_price());
		System.out.printf("상품 수량 :%d\n", iolistDTO.getIo_qty());
		System.out.printf("총액 : %d\n", iolistDTO.getIo_total());
		System.out.println("====================================================");
		
	}
}
