package com.biz.grade.service;

import java.util.Scanner;

public class BookRentManagementService {

	protected BookService bookSerivce = null;
	protected RentService rentService = null;
	protected UserService userService = null;
	protected Scanner scan = null;
	
	public BookRentManagementService() {
		bookSerivce = new BookService();
		rentService = new RentService();
		userService = new UserService();
		scan = new Scanner(System.in);
	}
	
	public void startMenu() {
		while(true) {
			System.out.println("============================================");
			System.out.println("Book Rent Management System 2019 Console");
			System.out.println("============================================");
			System.out.println("1. 도서 관련 서비스");
			System.out.println("2. 회원 관련 서비스");
			System.out.println("3. 대출 관련 서비스");
			System.out.println("0. 종료");
			System.out.println("============================================");
			System.out.print("업무 선택 >>");
			String strSelectMenu = scan.nextLine();
			int SelectMenu = 0;
			try {
				SelectMenu = Integer.valueOf(strSelectMenu);
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
				// TODO: handle exception
			}
			if(SelectMenu == 0 ) break;
			else if(SelectMenu == 1) {
				bookSerivce.BookMenu();
			}else if(SelectMenu == 2) {
				userService.userMenu();
			}else if(SelectMenu == 3) {
				rentService.rentMenu();
			}else {
				System.out.println("다시 입력해주세요.");
			}
		}
	}
}
