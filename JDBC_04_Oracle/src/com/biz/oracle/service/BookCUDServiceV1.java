package com.biz.oracle.service;

import java.util.Scanner;

import com.biz.oracle.persistence.BookDTO;
import com.biz.oracle.persistence.dao.BookDao;
import com.biz.oracle.persistence.dao.BookDaoImp;

public class BookCUDServiceV1 {

	private BookDao bookDao = null;
	private Scanner scan = null;
	
	public BookCUDServiceV1() {
		scan = new Scanner(System.in);
		bookDao = new BookDaoImp();
	}
	
	public void inputBook() {
		while(true) {
			System.out.println("================================================");
			System.out.println("도서정보 등록");
			System.out.println("================================================");
			
			String strB_name =null;
			while(true) {
				
				System.out.print("1. 도서명 (-Q:quit) >> ");
				strB_name = scan.nextLine();
				if(strB_name.equals("-Q")) break;
				if(strB_name.isEmpty()) {
					System.out.println("도서명은 반드시 입력해주세요.");
					continue;
				}
				break;
			}
			if(strB_name.equals("-Q")) break;
			
			System.out.print("2. 출판사 (-Q:quit) >> ");
			String strB_comp = scan.nextLine();
			if(strB_comp.equals("-Q")) break;
			
			System.out.print("3. 저자 (-Q:quit) >> ");
			String strB_writer = scan.nextLine();
			if(strB_writer.equals("-Q")) break;
			
			String strB_price = null;
			int intB_price = 0;
			while(true) {
				try {
					System.out.print("4. 가격 (-Q:quit) >> ");
					 strB_price = scan.nextLine();
					if(strB_price.equals("-Q")) break;
					
					intB_price = Integer.valueOf(strB_price); 
					
				} catch (Exception e) {
					System.out.println("가격은 숫자로만 입력해주세요.");
					continue;
				}
				break;
			}
			if(strB_price.equals("-Q")) break;
		
			BookDTO bookDTO = BookDTO.builder().b_name(strB_name)
												.b_comp(strB_comp)
												.b_writer(strB_writer)
												.b_price(intB_price)
												.build();
			
			int ret = bookDao.insert(bookDTO);
			if(ret>0) {
				System.out.println("데이터 INSERT 성공");
			}else {
				System.out.println("데이터 INSERT 실패");
			}
		}
	}

	public void deleteBook() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("=====================================================================");
			System.out.print("삭제할 레코드 (-Q : quit) >> ");
			String strCode = scan.nextLine();
			
			if(strCode.equals("-Q")) break;
			
			BookDTO bookDTO = bookDao.findById(strCode);
			if(bookDTO == null) {
				System.out.println("도서 코드가 없습니다.");
				continue;
			}
			
			int ret = bookDao.delete(strCode);
			if(ret>0) {
				System.out.println("데이터 DELETE 성공");
			}else {
				System.out.println("데이터 DELETE 실패");
			}
		}
	}
}
