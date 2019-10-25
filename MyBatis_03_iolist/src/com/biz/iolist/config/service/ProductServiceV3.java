package com.biz.iolist.config.service;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2 {

	public void insertProduct() {
		System.out.println("상품 코드 입력 >> ");
		String strPcode = scan.nextLine();
		ProductDTO proDTO = proDao.findById(strPcode.trim().toUpperCase());
		
		if(proDTO != null) {
			System.out.println("상품코드가 존재합니다. 다른 코드를 입력해주세요.");
		}else {
			while(true) {
				System.out.println("상품 이름 입력 >> ");
				String strName = scan.nextLine();
				if(strName.trim().isEmpty()) {
					System.out.println("상품명은 반드시 입력해야 합니다.");
					continue;
				}
				
				System.out.println("매입 가격 입력 >> ");
				String strIPrice = scan.nextLine();
				int intIPrice = 0;
				try {
					intIPrice = Integer.valueOf(strIPrice);
				} catch (Exception e) {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}
				
				System.out.println("매출 가격 입력 >> ");
				String strOPrice = scan.nextLine();
				int intOPrice = 0;
				try {
					intOPrice = Integer.valueOf(strOPrice);
				} catch (Exception e) {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}
				
				System.out.println("과세 여부 입력 >>");
				String strVar = scan.nextLine();
				
				ProductDTO insertDTO = ProductDTO.builder().p_code(strPcode).p_name(strName).p_iprice(intIPrice).p_oprice(intOPrice).p_vat(strVar).build();
				
				proDao.insert(insertDTO);
				
				
			}
			
			
		}

	}
	
}
