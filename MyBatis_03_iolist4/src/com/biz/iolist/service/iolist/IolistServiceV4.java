package com.biz.iolist.service.iolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.ProductDTO;

public class IolistServiceV4 extends IolistServiceV3{

	protected void update() {
		System.out.println("================================================");
		System.out.println("매입매출 수정");
		System.out.println("================================================");
		
		System.out.print("거래처명 >> ");
		String strDName = scan.nextLine();
		ioView.viewListByDName(strDName);
		
		System.out.print("수정할 SEQ >> ");
		String stdSEQ = scan.nextLine();
		
		long longSEQ = 0;
		try {
			longSEQ = Long.valueOf(stdSEQ);
		} catch (Exception e) {
			System.out.println("SEQ 형식이 틀렸습니다.");
			return;
			// TODO: handle exception
		}
		
		IolistDTO iolistDTO = iolistDao.findById(longSEQ);
		
		while(true) {
			System.out.printf("거래구분 입력[%s](1:매입, 2:매출 -1:종료) >> ", iolistDTO.getIo_inout());
			String strInout = scan.nextLine();
			
			if(strInout.trim().isEmpty()) break;
			
			try {
				int intInout = Integer.valueOf(strInout);
				if(intInout<0) return;
				
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
//			
				System.out.printf("거래일자[%s] >> ", iolistDTO.getIo_date());
				String strDate = scan.nextLine();
				
				if(strDate.trim().isEmpty()) {
//					iolistDTO.setIo_date(curDate);
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
			System.out.print("거래처명 입력 (-Q:quit) >> ");
			strDName = scan.nextLine();
			
			if(strDName.trim().isEmpty()) break;
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
			System.out.printf("상품명 입력 >> ");
			String strPName = scan.nextLine();
			if(strPName.trim().isEmpty()) break;
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
			if(strPrice.trim().isEmpty()) break;
			
			try {
				int price = Integer.valueOf(strPrice);
				iolistDTO.setIo_price(price);
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		}
		
		while(true) {
			System.out.printf("수량입력[%d] >>", iolistDTO.getIo_qty());
			String strQty = scan.nextLine();
			if(strQty.trim().isEmpty()) break;
			
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
		
		int ret = iolistDao.update(iolistDTO);
		if(ret >0 ) {
			System.out.println("데이터 변경 완료");
		}else {
			System.out.println("데이터 변경 실패");
		}
		
	}
	
	protected void delete() {
		
		System.out.println("삭제할 SEQ >> ");
		String strDelete = scan.nextLine();
		
		long longDelete = 0;
		try {
			longDelete = Long.valueOf(strDelete);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		IolistDTO iolistDTO = iolistDao.findById(longDelete);
		this.iolistViewDetail(iolistDTO);
		
		System.out.println("삭제 하시겠습니까 (Enter : 삭제) >> ");
		String yesNo = scan.nextLine();
		if(yesNo.trim().isEmpty()) {
			int ret = iolistDao.delete(longDelete);
			
			if(ret > 0) {
				System.out.println("데이터 삭제 완료");
			}else {
				System.out.println("데이터 삭제 실패");
			}
		}
		
	}
	
}
