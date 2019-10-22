package com.biz.addr.service;

import java.util.List;
import java.util.Scanner;

import com.biz.addr.persistence.AddrDTO;
import com.biz.addr.persistence.dao.AddrDao;
import com.biz.addr.persistence.dao.AddrDaoImp;

public class AddrService {

	AddrDao addrDao = null;
	Scanner scan = new Scanner(System.in);
	
	public AddrService() {
		this.addrDao = new AddrDaoImp();
	}
	
	private void print(AddrDTO dto) {
		System.out.print(dto.getId() + "\t");
		System.out.print(dto.getName() + "\t");
		System.out.print(dto.getTel() + "\t");
		System.out.print(dto.getAddr() + "\t");
		System.out.print(dto.getChain() + "\n");
	}
	
	public void viewAll() {
		System.out.println("===============================================");
		System.out.println("주소록 전체 조회");
		System.out.println("===============================================");
		System.out.println("ID\t이름\t전화번호\t주소\t관계");
		System.out.println("-----------------------------------------------");
		List<AddrDTO> list = addrDao.selectall();
		for(AddrDTO dto : list) {
			this.print(dto);
		}
		System.out.println("===============================================");
	}
	
	public void searchId() {
		
		while(true) {
			System.out.println("===============================================");
			System.out.println("주소록 ID 조회");
			System.out.println("===============================================");
			System.out.print(" ID 입력 (-Q : 종료 ) >> ");
			String strID = scan.nextLine();
			if(strID.equals("-Q")) break;
			System.out.println("-----------------------------------------------");
			long id = 0;
			try {
				id = Long.valueOf(strID);
				AddrDTO dto = addrDao.findById(id);
				System.out.println("ID\t이름\t전화번호\t주소\t관계");
				System.out.println("-----------------------------------------------");
				this.print(dto);
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
			}
			System.out.println("===============================================");
		}
		
		
	}
	
	public void searchName() {
		while(true) {
			System.out.println("===============================================");
			System.out.println("주소록 Name 조회");
			System.out.println("===============================================");
			System.out.print(" Name 입력 (-Q : 종료 ) >> ");
			String strName = scan.nextLine();
			if(strName.equals("-Q")) break;
			System.out.println("-----------------------------------------------");
			List<AddrDTO> list = addrDao.findByName(strName);
			
			System.out.println("ID\t이름\t전화번호\t주소\t관계");
			System.out.println("-----------------------------------------------");
			for(AddrDTO dto : list) {
				this.print(dto);
			}
			System.out.println("===============================================");
			
		}
	}
	
	public void searchTel() {
		while(true) {
			System.out.println("===============================================");
			System.out.println("주소록 Tel 조회");
			System.out.println("===============================================");
			System.out.println(" Tel (XXX-XXXX-XXXX)");
			System.out.print(" Tel 입력 (-Q : 종료 ) >> ");
			String strTel = scan.nextLine();
			if(strTel.equals("-Q")) break;
			System.out.println("-----------------------------------------------");
			
			List<AddrDTO> list = addrDao.findByTel(strTel);
			
			System.out.println("ID\t이름\t전화번호\t주소\t관계");
			System.out.println("-----------------------------------------------");
			for(AddrDTO dto : list) {
				this.print(dto);
			}
			System.out.println("===============================================");
			
		}
		
	}
	
	public void searchChain() {
		while(true) {
			System.out.println("===============================================");
			System.out.println("주소록 Chain 조회");
			System.out.println("===============================================");
			System.out.print(" Chain 입력 (-Q : 종료 ) >> ");
			String strChain = scan.nextLine();
			if(strChain.equals("-Q")) break;
			System.out.println("-----------------------------------------------");
			
			List<AddrDTO> list = addrDao.findByChain(strChain);
			
			System.out.println("ID\t이름\t전화번호\t주소\t관계");
			System.out.println("-----------------------------------------------");
			for(AddrDTO dto : list) {
				this.print(dto);
			}
			System.out.println("===============================================");
			
		}
		
	}
	
	
	
}
