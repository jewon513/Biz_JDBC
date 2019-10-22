package com.biz.addr.exec;

import java.util.Scanner;

import com.biz.addr.service.AddrCUDSerivce;
import com.biz.addr.service.AddrService;

public class AddrEx_01 {

	public static void main(String[] args) {
		
		AddrService as = new AddrService();
		AddrCUDSerivce acs = new AddrCUDSerivce();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("===============================================");
			System.out.println("\t\t주소록 서비스");
			System.out.println("===============================================");
			System.out.println("1. 전체조회 ");
			System.out.println("2. 주소록입력 ");
			System.out.println("3. 주소록삭제 ");
			System.out.println("4. 주소록수정 ");
			System.out.println("5. 종료 ");
			System.out.println("===============================================");
			System.out.print("선택 >> ");
			String strMenu = scan.nextLine();
			int menu = 0;
			try {
				menu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
			}
			if(menu==1) {
				as.viewAll();
			}else if(menu==2) {
				as.viewAll();
				acs.AddrInsert();
			}else if(menu==3) {
				as.viewAll();
				acs.AddrDelete();
			}else if(menu==4) {
				as.viewAll();
				acs.AddrUpdate();
			}else if(menu==5) {
				break;
			}
		}
		
	}
	
}
