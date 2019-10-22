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

	public void updateBook() {

		System.out.println("=====================================================================");
		System.out.println("도서정보 수정");
		System.out.println("=====================================================================");
		System.out.print("수정할 도서코드 (-Q:quit) >> ");
		String strCode = scan.nextLine();
		if(strCode.equals("-Q")); // 입력 끝내기
		
		//키보드로 입력받은 코드에 해당하는 도서정보를 가져올것
		BookDTO bookDTO = bookDao.findById(strCode);
		//보통의 경우 PK를 수정하는 프로세스는 좋지 않다.
		//PK를 수정해야 할 경우에는 기존의 Data를 삭제하고 새로운 데이터를 INSERT 하거나
		//기존의 Data는 그대로 유지하고 새로운 데이터를 INSERT 한다.
		//각 항목별로 값을 수정
		System.out.printf("[현재 도서명 : %s] 변경할 도서명 >> ",bookDTO.getB_name() );
		String strName = scan.nextLine();
		
		//새로운 도서명을 입력했을때는 bookDTO의 도서명을 변경하고
		//그냥 ENTER만 입력했을때는 변경하지 않는다.
		if(strName.trim().length()>0) {
			bookDTO.setB_name(strName.trim());
		}
		
		System.out.printf("[현재 출판사 : %s] 변경할 출판사 >> ", bookDTO.getB_comp());
		String strComp = scan.nextLine();
		if(strComp.trim().length()>0) {
			bookDTO.setB_comp(strComp.trim());
		}
		
		System.out.printf("[현재 저자 : %s] 변경할 저자 >> ", bookDTO.getB_writer());
		String strWriter = scan.nextLine();
		if(strWriter.trim().length()>0) {
			bookDTO.setB_writer(strWriter.trim());
		}
		
		System.out.printf("[현재 가격 : %d] 변경할 가격 >> ", bookDTO.getB_price());
		String strPrice = scan.nextLine();
		
		int intPrice = 0;
		
		// 입력된 가격을 숫자로 변환하는 코드에서 만약 값을 입력하지 않고 Enter를 누르면
		// NumberFormatException이 발생한다.
		try {
			intPrice = Integer.valueOf(strPrice);
			
			bookDTO.setB_price(intPrice);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//여기에 도착했을때
		//bookDTO에 담긴 값은
		//처음에 table에서 읽은 값이 저장되어있거나
		//중간에 키보드로 입력한 값으로 변경되었을것이다.
		
		int ret = bookDao.update(bookDTO);
		
		if(ret > 0) {
			System.out.println("업데이트 성공");
		}else {
			System.out.println("업데이트 실패");
		}
		

	}
}
