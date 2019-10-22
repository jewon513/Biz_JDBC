package com.biz.addr.service;

import java.util.Scanner;

import com.biz.addr.persistence.AddrDTO;
import com.biz.addr.persistence.dao.AddrDao;
import com.biz.addr.persistence.dao.AddrDaoImp;

public class AddrCUDSerivce {

	AddrDao addrDao = null;
	Scanner scan = null;
	
	public AddrCUDSerivce() {
		this.addrDao = new AddrDaoImp();
		scan = new Scanner(System.in);
	}
	
	public void AddrInsert() {
		
		while(true) {
			System.out.println("===============================================");
			System.out.println("주소록 입력 서비스");
			System.out.println("===============================================");
			System.out.print("이름 입력 (-Q : 종료 ) >> ");
			String strName = scan.nextLine();
			if(strName.equals("-Q")) break;
			
			System.out.print("전화번호 입력 (-Q : 종료 ) >> ");
			String strTel = scan.nextLine();
			if(strTel.equals("-Q")) break;
			
			System.out.print("주소 입력 (-Q : 종료 ) >> ");
			String strAddr = scan.nextLine();
			if(strAddr.equals("-Q")) break;
			

			System.out.print("관계 입력 (-Q : 종료 ) >> ");
			String strChain = scan.nextLine();
			if(strChain.equals("-Q")) break;
			
			AddrDTO addrDTO = AddrDTO.builder().name(strName)
												.tel(strTel)
												.addr(strAddr)
												.chain(strChain)
												.build();
			
			int ret = addrDao.insert(addrDTO);
			if(ret > 0) {
				System.out.println("데이터 입력 완료");
			}else {
				System.out.println("데이터 입력 실패");
			}
			
		}
	}
	
	public void AddrDelete() {
		
		while(true) {
			System.out.println("===============================================");
			System.out.println("주소록 제거 서비스");
			System.out.println("===============================================");
			System.out.print(" 해당 ID값 입력 (-Q : 종료 ) >> ");
			String strId = scan.nextLine();
			if (strId.equals("-Q")) break;
			long id = 0;
			try {
				id = Long.valueOf(strId);
				addrDao.delete(id);
			} catch (Exception e) {
				System.out.println("숫자만 입력하세요.");
				continue;
			}
			
		}
		System.out.println("===============================================");
	}
	
	public void AddrUpdate() {
		
		while(true) {
			System.out.println("===============================================");
			System.out.println("주소록 수정 서비스");
			System.out.println("===============================================");
			System.out.print("수정할 해당 ID값 입력 (-Q : 종료 ) >> ");
			String strId = scan.nextLine();
			if(strId.equals("-Q")) break;
			long id = 0;
			try {
				id = Long.valueOf(strId);
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			AddrDTO dto = addrDao.findById(id);
			
			System.out.printf("[현재 이름 : %s] 수정할 이름 입력 >> \n", dto.getName() );
			String strName = scan.nextLine();
			if(strName.trim().length()>0) {
				dto.setName(strName.trim());
			}
			
			
			System.out.printf("[현재 전화번호 : %s] 수정할 전화번호 입력 >> \n", dto.getTel() );
			String strTel = scan.nextLine();
			if(strTel.trim().length()>0) {
				dto.setTel(strTel.trim());
			}
			
			System.out.printf("[현재 주소 : %s] 수정할 주소 입력 >> \n", dto.getAddr() );
			String strAddr = scan.nextLine();
			if(strAddr.trim().length()>0) {
				dto.setAddr(strAddr.trim());
			}
			
			
			System.out.printf("[현재 관계 : %s] 수정할 관계 입력 >> \n", dto.getChain() );
			String strChain = scan.nextLine();
			if(strChain.trim().length()>0) {
				dto.setChain(strChain.trim());
			}
			
			int ret = addrDao.update(dto);
			
			if(ret > 0) {
				System.out.println("데이터 수정 완료");
			}else {
				System.out.println("데이터 수정 실패");
			}
		}
	}
	
}
