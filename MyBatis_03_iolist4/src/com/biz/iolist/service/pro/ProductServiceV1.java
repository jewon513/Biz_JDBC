package com.biz.iolist.service.pro;

import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.ProDao;
import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV1 {

	protected ProDao proDao;
	protected Scanner scan;
	

	public ProductServiceV1() {
		this.proDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(ProDao.class);
		scan = new Scanner(System.in);
	}
	
	protected ProductDTO viewDetail(String strPCode) {
		ProductDTO proDTO = proDao.findById(strPCode);
		if (proDTO == null) {
			System.out.println("상품이 없습니다.");
			return null;
		}
		
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
		
		return proDTO;
	}
	
	public void proUpdate() {
//		List<ProductDTO> proList = proDao.selectAll();
//		for (ProductDTO productDTO : proList) {
//			System.out.println(productDTO);
//		}
		System.out.println("====================================================");
		System.out.print("수정할 상품코드 >> ");
		String strPCode = scan.nextLine();
		strPCode = strPCode.toUpperCase();
		ProductDTO proDTO = this.viewDetail(strPCode);
		
		System.out.print("상품명 >> ");
		String strName = scan.nextLine();

		// strNAme 변수에 아무런 문자열도 들어있지 않다면
		// 그냥 Enter만 눌렀을 경우
		if(!strName.trim().isEmpty()) {
			proDTO.setP_name(strName);
		}
		
		System.out.print("매입단가 >> ");
		String strIPrice = scan.nextLine();
		
		/*
		 * 만약 매입단가를 입력하지 않고 그냥 Enter만 눌렀다면
		 * Integer.valueOf(strIPrice)에서 exception이 발생
		 * 그렇다면 바로 catch 문으로 넘어가서 set 안됨
		 */
		try {
			proDTO.setP_iprice(Integer.valueOf(strIPrice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.print("매출단가 >> ");
		String strOPrice = scan.nextLine();
		
		try {
			proDTO.setP_oprice(Integer.valueOf(strOPrice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		int ret = proDao.update(proDTO);
		
		if (ret >0) {
			System.out.println("데이터 수정 완료");
		}else {
			System.out.println("데이터 수정 실패");
		}
		
		System.out.println(proDao.findById(strPCode));
		
		
	}
	
	
	
}
