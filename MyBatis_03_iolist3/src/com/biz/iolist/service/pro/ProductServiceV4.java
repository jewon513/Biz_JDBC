package com.biz.iolist.service.pro;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV4 extends ProductServiceV2 {

	public void menuProduct() {
		while(true) {
			System.out.println("========================================");
			System.out.println("대한쇼핑몰 상품관리 시스템");
			System.out.println("========================================");
			System.out.println("1.등록 2.수정 3.삭제 4.검색 0.끝");
			System.out.println("----------------------------------------");
			System.out.print("업무 선택 >> ");
			
			String strMenu = scan.nextLine();
			int intMenu = 0;
			try {
				intMenu = Integer.valueOf(strMenu);	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			
			if(intMenu==1) {
				this.insertProduct2();
			}else if(intMenu==2) {
				this.searchPName();
				this.proUpdate();
			}else if(intMenu==3) {
				this.searchPName();
				this.deleteProduct();
			}else if(intMenu==4) {
				this.searchPName();
			}else if(intMenu==0) {
				System.out.println("종료");
				break;
			}
		}
	}
	
	/*
	 * 	1.상품코드를 어떻게 할 것인가?
	 * 		가. 자동으로 생성하기
	 * 		나. 직접 입력하기(이미 문서로 작성된 경우)
	 * 
	 * 	2.상품이름을 입력하는데, 완전히 일치하는 상품이 이미 있는 경우
	 * 		가. 코드가 다르면 그냥 입력하기
	 * 		나. 코드가 다르고 단가부분이 다르면 그냥 입력하기
	 * 			상품정보 : 상품이름, 품목, 주매입처
	 * 		다. 같은 상품이름 입력 금지
	 * 	3.단가부분
	 * 		가. 매입단가를 입력하면, 표준 판매단가를 계산하기
	 * 		나. 매입단가, 매출단가를 별도 입력
	 * 		다.	매입단가일경우 VAT 포함여부
	 * 		라.	매출단가, 소매점에서는 VAT 포함이 기본, 도매점일경우 VAT 포함 여부
	 * 
	 */
	public void insertProduct2() {
		String strPCode;
		while(true) {
			System.out.println("====================================================");
			System.out.println("상품 코드 입력 (ENTER : 자동생성, -Q:quit) >> ");
			strPCode = scan.nextLine();
			if(strPCode.equals("-Q")) break;
			if(strPCode.trim().isEmpty()) {
				// 코드 자동생성
				String strTMPCode = proDao.getMaxPCode();
				int intPCode = Integer.valueOf(strTMPCode.substring(1));
				intPCode ++ ;
				strPCode = strTMPCode.substring(0, 1);
				strPCode += String.format("%04d", intPCode);
				System.out.println("생성된 코드 : " + strPCode);
				break;
			}
			
			if(strPCode.length() != 5) {
				System.out.println("상품코드의 길이 규칙에 맞지 않습니다.");
				continue;
			}
			
			strPCode = strPCode.toUpperCase();
			if(!strPCode.substring(0, 1).equals("P")) {
				System.out.println("상품코드는 첫글자가 P로 시작되어야 합니다.");
				continue;
			}
			
			try {
				Integer.valueOf(strPCode.substring(1));
			} catch (Exception e) {
				System.out.println("상품코드 2번째부터는 숫자만 올 수 있음");
				continue;
			}
			
			if(proDao.findById(strPCode)!= null) {
				System.out.println("이미 등록된 코드입니다.");
				continue;
			}
			
			break;
		}
		
		if(strPCode.equals("-Q")) return;
		
		String strPName;
			while(true) {
				System.out.print("상품 이름 입력 (-Q : quit) >> ");
				strPName = scan.nextLine();
				if(strPName.equals("-Q")) break;
				if(strPName.trim().isEmpty()) {
					System.out.println("상품 이름은 반드시 입력해야 합니다.");
					continue;
				}
				
				ProductDTO proDTO = proDao.findBySName(strPName);
				if(proDTO != null) {
					System.out.println("====================================================");
					System.out.println("같은 이름의 상품이 존재합니다.");
				
					this.viewDetail(proDTO.getP_code());
					
					System.out.print("사용하시겠습니까? (Enter : 사용, NO : 다시입력) >> ");
					String yesNo = scan.nextLine();
					if(yesNo.trim().isEmpty()) break;
					continue;
				}
				break;
			}
		if(strPName.equals("-Q")) return;

		
		// 상품 매입단가 입력
		// 1. VAT 여부 입력
	    // 2. 과세이면 입력가격 / 1.1
		// 3. 면세이면 그대로
	    // 	  면세 : 농산물(1차식품, 쌀), 흰우유, 재포장상품
		
		String strVAT ="1";
		int intIPrice = 0 ;
		int intVAT =0;
		while(true) {
			System.out.println("과세구분(1:과세 , 0:면세, -Q:quit)");
			strVAT = scan.nextLine();
			if(strVAT.equals("-Q")) break;
			intVAT = 1;
			try {
				intVAT = Integer.valueOf(strVAT);
				if(intVAT < 0 || intVAT > 1) {
					System.out.println("과세구분값은 0 또는 1만 입력해주세요.");
					continue;
				}
			} catch (Exception e) {
				System.out.println("0 또는 1만 가능합니다");
				continue;
				// TODO: handle exception
			}
			break;
		}
		
		while(true) {
			System.out.println("매입단가 (-Q:quit) >> ");
			String strIPrice = scan.nextLine();
			
			try {
				intIPrice = intVAT == 1 ? (int)(Integer.valueOf(strIPrice) / 1.1) : Integer.valueOf(strIPrice);
			} catch (Exception e) {
				System.out.println("매입단가는 숫자만 가능!!");
				continue;
				// TODO: handle exception
			}
			break;
		}
		
		if(strVAT.equals("-Q")) return;
		
		//매출단가 입력
		String strOprice;
		int intOprice = 0;
		while(true) {
			System.out.println("매출단가 (-Q:quit)>> " );
			strOprice = scan.nextLine();
			if(strOprice.equals("-Q")) break;
			
			try {
				intOprice = Integer.valueOf(strOprice);
			} catch (Exception e) {
				System.out.println("매출단가는 숫자만 가능");
				continue;
				// TODO: handle exception
			}
			break;
			
		}// 매출단가 입력 끝
		
		if(strOprice.equals("-Q")) return;
		
		ProductDTO proDTO = ProductDTO.builder().p_code(strPCode).p_name(strPName).p_vat(strVAT).p_iprice(intIPrice).p_oprice(intOprice).build();
		
		int ret = proDao.insert(proDTO);
		if(ret>0){
			System.out.println("상품등록 성공");
		}else {
			System.out.println("상품등록 실패");
		}
	}
	
	public void insertProduct() {
	
		while(true) {
			
			System.out.print("상품 코드 입력 >> ");
			String strPcode = scan.nextLine();
			strPcode = strPcode.toUpperCase();
			ProductDTO proDTO = proDao.findById(strPcode.trim().toUpperCase());
			
			if(proDTO != null) {
				System.out.println("상품코드가 존재합니다. 다른 코드를 입력해주세요.");
				continue;
			}else {
				
					System.out.print("상품 이름 입력 >> ");
					String strName = scan.nextLine();
					if(strName.trim().isEmpty()) {
						System.out.println("상품명은 반드시 입력해야 합니다.");
						continue;
					}
					
					System.out.print("매입 가격 입력 >> ");
					String strIPrice = scan.nextLine();
					int intIPrice = 0;
					if(strIPrice.trim().length()>0) {
						try {
							intIPrice = Integer.valueOf(strIPrice);
						} catch (Exception e) {
							System.out.println("숫자만 입력해주세요.");
							continue;
						}
					}
				
					
					
					System.out.print("매출 가격 입력 >> ");
					String strOPrice = scan.nextLine();
					int intOPrice = 0;
					if(strOPrice.trim().length()>0) {
						try {
							intOPrice = Integer.valueOf(strOPrice);
						} catch (Exception e) {
							System.out.println("숫자만 입력해주세요.");
							continue;
						}
					}
					
					
					System.out.print("과세 여부 입력 (1 or 0)>>");
					String strVar = scan.nextLine();
					
					ProductDTO insertDTO = ProductDTO.builder().p_code(strPcode).p_name(strName).p_iprice(intIPrice).p_oprice(intOPrice).p_vat(strVar).build();
					
					int ret = proDao.insert(insertDTO);
					
					if(ret>0) {
						System.out.println("제품 등록 완료");
						return;
					}else {
						System.out.println("제품 등록 실패");
						return;
					}
					
			}
		}

	}
	
	/*
	 * 상품정보를 입력받고
	 * 상품코드를 입력받아서 delete 수행
	 */
	public void deleteProduct() {
		System.out.print("삭제할 상품코드 >> ");
		String strPCode = scan.nextLine();
		strPCode = strPCode.toUpperCase();
		
		ProductDTO proDTO = this.viewDetail(strPCode);
		if(proDTO == null) {
			return;
		}
		
		int ret = proDao.delete(strPCode);
		if(ret>0){
			System.out.println("정상적으로 삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
	}
	
}
