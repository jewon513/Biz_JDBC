package com.biz.oracle.service;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

import com.biz.oracle.persistence.BookDTO;
import com.biz.oracle.persistence.dao.BookDao;
import com.biz.oracle.persistence.dao.BookDaoImp;

public class BookServiceV1 {
	
	// 객체 선언(아직 사용준비 전 단계)
	BookDao bookDao = null;
	Scanner scan = null;
	
	// 클래스 생성자
	public BookServiceV1() {
		//선언된 객체를 사용할 수 있도록 초기화
		//초기화된 클래스를 인스턴스 했다고 한다.
		scan = new Scanner(System.in);
		bookDao = new BookDaoImp();
	}
	
	/*
	 * 도서정보 전체리스트를 DB로부터 읽어서 console에 보이기
	 */
	public void viewBookList() {
		
		//TODO dao의 selectAll() method를 호출하여 전체 리스트를 DB로부터 가져와서 bookList에 받기
		List<BookDTO> bookList = bookDao.selectAll();
		this.viewList(bookList);
		
	}
		/*
		 * 키보드에서 도서이름을 입력받아서 리스트를 콘솔에 보이기
		 */
	public void searchBookName(boolean bConti) {
		
		while(true) {
			if(this.searchBookName() != null) break;
		}
		
	}
	
	public String searchBookName() {
		
			System.out.println("=====================================================================");
			System.out.println("도서검색");
			System.out.println("=====================================================================");
			System.out.print("도서명 (-Q:quit) >> ");
			String strName = scan.nextLine();
			if(strName.equals("-Q")) return "-Q";
			this.searchBookName(strName);
			return strName;
			
	}
	
	public boolean searchBookName(String strName) {
		
		List<BookDTO> bookList = bookDao.findByName(strName);
		if(bookList==null || bookList.size()<1) {
			System.out.println("찾는 도서명이 없음!");
			return false;
		}
		this.viewList(bookList);
		return true;
		
}
	
	private void viewList(List<BookDTO> bookList) {
		System.out.println("=====================================================================");
		System.out.println("도서 리스트 V1");
		System.out.println("=====================================================================");
		System.out.println("코드\t도서명\t출판사\t저자\t가격");
		System.out.println("---------------------------------------------------------------------");
		for(BookDTO DTO : bookList) {
			System.out.print(DTO.getB_code() +"\t");
			System.out.print(DTO.getB_name() +"\t");
			System.out.print(DTO.getB_comp() +"\t");
			System.out.print(DTO.getB_writer() +"\t");
			System.out.print(DTO.getB_price()+"\n");
		}
		System.out.println("=====================================================================");
	}

	public void searchBookPrice() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("=====================================================================");
			System.out.println("도서 가격 검색");
			System.out.println("=====================================================================");
		
			try {
				System.out.print("시작가격(-Q:quit) >>");
				String strSprice = scan.nextLine();
				if(strSprice.equals("-Q")) break;
				int sPrice = Integer.valueOf(strSprice);
				
				System.out.print("종료가격(-Q:quit) >>");
				String strEprice = scan.nextLine();
				if(strEprice.equals("-Q")) break;
				int ePrice = Integer.valueOf(strEprice);
				
				List<BookDTO> bookList = bookDao.findByPrice(sPrice, ePrice);
				
				this.viewList(bookList);
			} catch (Exception e) {
				System.out.println("가격은 숫자로만 입력하세요.");
				continue;
			}
		}
	}

	
}












