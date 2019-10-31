package com.biz.grade.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.grade.config.DBConnection;
import com.biz.grade.dao.BookDao;
import com.biz.grade.persistence.BookDTO;

public class BookService {

	protected SqlSession sqlSession = null;
	protected BookDao bookDao = null;
	protected Scanner scan = null;

	public BookService() {
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		bookDao = sqlSession.getMapper(BookDao.class);
		scan = new Scanner(System.in);
	}

	public void BookselectAll() {
		List<BookDTO> bookDTOList = bookDao.selectAll();

		this.viewBookList(bookDTOList);
	}

	public void BookFindById() {
		System.out.print("도서 코드 입력 >> ");
		String b_code = scan.nextLine();

		BookDTO bookDTO = bookDao.findById(b_code);
		this.viewBookDTODtaile(bookDTO);
	}

	public void BookFindByName() {
		System.out.println("도서명 입력 >> ");
		String b_name = scan.nextLine();

		List<BookDTO> bookDTOList = bookDao.findByNameLike(b_name);
		this.viewBookList(bookDTOList);
	}
	
	public void BookMenu() {
		while(true) {
			System.out.println("============================================");
			System.out.println("도서 정보 서비스");
			System.out.println("============================================");
			System.out.println("1. 도서 검색");
			System.out.println("2. 도서 등록");
			System.out.println("3. 도서 수정");
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
				this.BookFindNameAuther();
			}else if(SelectMenu == 2) {
				this.BookInsert();
			}else if(SelectMenu == 3) {
				this.BookFindByName();
				this.BookUpdate();
			}else {
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	public void BookInsert() {
		// TODO 책 등록
		System.out.println("============================================");
		System.out.println("도서 등록 서비스");
		System.out.println("============================================");

		BookDTO bookDTO = new BookDTO();
		bookDTO.setB_code(this.makeBookCode());

		while (true) { // 도서명 등록
			System.out.print("도서명 입력 (-Q : 종료) >> ");
			String b_name = scan.nextLine();
			if (b_name.equals("-Q"))
				return;
			if (b_name.trim().isEmpty()) {
				System.out.println("도서명은 반드시 입력해야 합니다.");
				continue;
			}

			List<BookDTO> bookList = bookDao.findByName(b_name); // 도서 이름 중복 찾기

			if (bookList.size() > 0) {
				System.out.println("중복되는 도서명이 있습니다. 다시 입력해주세요");
				continue;
			}
			bookDTO.setB_name(b_name);

			break;
		}

		while (true) { // 저자 등록
			System.out.print("저자 입력 (-Q : 종료) >> ");
			String b_auther = scan.nextLine();
			if (b_auther.equals("-Q"))
				return;
			if (b_auther.trim().isEmpty()) {
				System.out.println("저자는 반드시 입력해야 합니다.");
				continue;
			}

			bookDTO.setB_auther(b_auther);
			break;
		}

		while (true) { // 대여료 등록
			System.out.print("대여료 입력 (-Q : 종료) >> ");
			String strB_rprice = scan.nextLine();
			if (strB_rprice.equals("-Q"))
				return;
			if (strB_rprice.trim().isEmpty()) {
				System.out.println("대여료는 반드시 입력해야 합니다.");
				continue;
			}

			try {
				int b_rprice = Integer.valueOf(strB_rprice);
				bookDTO.setB_rprice(b_rprice);
				break;
			} catch (Exception e) {
				System.out.println("대여료는 숫자만 입력해주세요.");
				continue;
			}

		}

		while (true) { // 출판년도 등록
			System.out.print("출판년도 입력 (-Q : 종료) >> ");
			String strB_year = scan.nextLine();
			if (strB_year.equals("-Q"))
				return;
			if (strB_year.trim().isEmpty()) {
				System.out.println("출판년도는 반드시 입력해야 합니다.");
				continue;
			}

			try {
				int b_year = Integer.valueOf(strB_year);
				bookDTO.setB_year(b_year);
				break;
			} catch (Exception e) {
				System.out.println("출판년도는 숫자만 입력해주세요.");
				continue;
			}

		}

		while (true) { // 구입가격 등록
			System.out.print("구입가격 입력 (-Q : 종료) >> ");
			String strB_iprice = scan.nextLine();
			if (strB_iprice.equals("-Q"))
				return;

			int b_iprice = 0;
			if (strB_iprice.trim().isEmpty()) {
				bookDTO.setB_iprice(b_iprice);
				break;
			}

			try {
				b_iprice = Integer.valueOf(strB_iprice);
				bookDTO.setB_iprice(b_iprice);
			} catch (Exception e) {
				System.out.println("구입가격은 숫자만 입력해주세요.");
				continue;
			}
			break;
		}

		while (true) { // 구입가격 등록
			System.out.print("출판사 입력 (-Q : 종료) >> ");
			String b_comp = scan.nextLine();
			if (b_comp.equals("-Q"))
				return;
			if (b_comp.trim().isEmpty()) {
				bookDTO.setB_comp("");
			}

			bookDTO.setB_comp(b_comp);

			break;
		}

		this.viewBookDTODtaile(bookDTO);

		System.out.println("이대로 등록하시겠습니까? (Enter : 등록)");
		String yesNo = scan.nextLine();

		if (yesNo.trim().isEmpty()) {
			int ret = bookDao.insert(bookDTO);
			if (ret > 0) {
				System.out.println("데이터 등록 성공");
			} else {
				System.out.println("데이터 등록 실패");
			}
		}

	}

	public void BookUpdate() {
		// TODO 책 수정
		System.out.println("============================================");
		System.out.println("도서 정보 수정 서비스");
		System.out.println("============================================");
		BookDTO bookDTO = null;
		while(true) {
			System.out.print("수정할 도서 코드 입력 (-Q : 종료) >> ");										//수정할 도서를 도서코드로 검색
			String b_code = scan.nextLine();
			if (b_code.equals("-Q")) return;
			if(b_code.trim().isEmpty()) break;
			bookDTO = bookDao.findById(b_code);															

			this.viewBookDTODtaile(bookDTO);
			
			System.out.println("이 도서가 맞습니까? (Enter : 계속 진행) >>");
			String yesNo = scan.nextLine();
			if(yesNo.trim().isEmpty()) {
				break;
			}
		}
		
		while (true) { 																						//도서명 수정
			System.out.printf("도서명 입력 [%s] (-Q : 종료) >> ", bookDTO.getB_name());
			String b_name = scan.nextLine();
			if (b_name.equals("-Q"))
				return;
			if (b_name.trim().isEmpty()) {
				break;
			}

			List<BookDTO> bookList = bookDao.findByName(b_name); 											// 도서 이름 중복 찾기

			if (bookList.size() > 0) {
				System.out.println("중복되는 도서명이 있습니다. 다시 입력해주세요");
				continue;
			}
			bookDTO.setB_name(b_name);

			break;
		}

		while (true) { 																						// 저자 수정
			System.out.printf("저자 입력 [%s] (-Q : 종료) >> ", bookDTO.getB_auther());
			String b_auther = scan.nextLine();
			if (b_auther.equals("-Q"))
				return;
			if (b_auther.trim().isEmpty()) {
				break;
			}
			
			bookDTO.setB_auther(b_auther);
			break;
		}

		while (true) { 																						// 대여료 수정
			System.out.printf("대여료 입력 [%d] (-Q : 종료) >> ", bookDTO.getB_rprice());
			String strB_rprice = scan.nextLine();
			if (strB_rprice.equals("-Q"))
				return;
			if (strB_rprice.trim().isEmpty()) {
				break;
			}

			try {
				int b_rprice = Integer.valueOf(strB_rprice);
				bookDTO.setB_rprice(b_rprice);
				break;
			} catch (Exception e) {
				System.out.println("대여료는 숫자만 입력해주세요.");
				continue;
			}

		}

		while (true) { 																						// 출판년도 수정
			System.out.printf("출판년도 입력[%d] (-Q : 종료) >> ", bookDTO.getB_year());
			String strB_year = scan.nextLine();
			if (strB_year.equals("-Q"))
				return;
			if (strB_year.trim().isEmpty()) {
				break;
			}

			try {
				int b_year = Integer.valueOf(strB_year);
				bookDTO.setB_year(b_year);
				break;
			} catch (Exception e) {
				System.out.println("출판년도는 숫자만 입력해주세요.");
				continue;
			}

		}

		while (true) { 																						// 구입가격 수정
			System.out.printf("구입가격 입력 [%d](-Q : 종료) >> ", bookDTO.getB_iprice());
			String strB_iprice = scan.nextLine();
			if (strB_iprice.equals("-Q"))
				return;

			if (strB_iprice.trim().isEmpty()) {
				break;
			}

			try {
				int b_iprice = Integer.valueOf(strB_iprice);
				bookDTO.setB_iprice(b_iprice);
			} catch (Exception e) {
				System.out.println("구입가격은 숫자만 입력해주세요.");
				continue;
			}
			break;
		}

		while (true) { 																						// 출판사 수정
			System.out.printf("출판사 입력 [%d] (-Q : 종료) >> ", bookDTO.getB_comp());
			String b_comp = scan.nextLine();
			if (b_comp.equals("-Q"))
				return;
			if (b_comp.trim().isEmpty()) {
				break;
			}

			bookDTO.setB_comp(b_comp);

			break;
		}

		this.viewBookDTODtaile(bookDTO);

		System.out.println("이대로 수정하시겠습니까? (Enter : 수정)");
		String yesNo = scan.nextLine();

		if (yesNo.trim().isEmpty()) {
			int ret = bookDao.update(bookDTO);
			if (ret > 0) {
				System.out.println("데이터 수정 성공");
			} else {
				System.out.println("데이터 수정 실패");
			}
		}
	}
	
	public void BookFindNameAuther() {
		//TODO 도서 검색
		System.out.print("검색할 도서명을 입력하세요 >> ");
		String b_name = scan.nextLine();
		System.out.print("검색할 저자를 입력하세요 >> ");
		String b_auther = scan.nextLine();
		
		if(b_name.trim().isEmpty()) {
			List<BookDTO> bookDTOList = bookDao.findByAutherLike(b_auther);
			this.viewBookList(bookDTOList);
		}else if(b_auther.trim().isEmpty()) {
			List<BookDTO> bookDTOList = bookDao.findByNameLike(b_name);
			this.viewBookList(bookDTOList);
		}else {
			List<BookDTO> bookDTOList = bookDao.findByNameAuther(b_name, b_auther);
			this.viewBookList(bookDTOList);
		}
	}

	protected void viewBookDTO(BookDTO bookDTO) {
		System.out.print(bookDTO.getB_code() + "\t\t");
		System.out.print(bookDTO.getB_name() + "\t\t\t\t");
		System.out.print(bookDTO.getB_auther() + "\t");
		System.out.print(bookDTO.getB_rprice() + "\t");
		System.out.print(bookDTO.getB_year() + "\t");
		System.out.print(bookDTO.getB_iprice() + "\t");
		System.out.print(bookDTO.getB_comp() + "\n");
	}

	protected void viewBookList(List<BookDTO> bookDTOList) {
		System.out.println(
				"========================================================================================================================================");
		System.out.println("도서코드\t도서명\t\t\t\t\t저자\t대여료\t출판년도\t구입가격\t출판사");
		System.out.println(
				"========================================================================================================================================");
		for (BookDTO bookDTO : bookDTOList) {
			this.viewBookDTO(bookDTO);
		}
		System.out.println(
				"========================================================================================================================================");
	}

	protected void viewBookDTODtaile(BookDTO bookDTO) {
		System.out.println(
				"========================================================================================================================================");
		System.out.println("도서코드 : " + bookDTO.getB_code());
		System.out.println("도서명 : " + bookDTO.getB_name());
		System.out.println("저자 : " + bookDTO.getB_auther());
		System.out.println("대여료 : " + bookDTO.getB_rprice());
		System.out.println("출판년도 : " + bookDTO.getB_year());
		System.out.println("구입가격 : " + bookDTO.getB_iprice());
		System.out.println("출판사 : " + bookDTO.getB_comp());
		System.out.println(
				"========================================================================================================================================");
	}

	protected String makeBookCode() {
		String getMaxCode = bookDao.getMaxBookCode();
		int getintCode = Integer.valueOf(getMaxCode.substring(2)) + 1;
		String b_code = "BK" + String.format("%04d", getintCode);
		return b_code;
	}
}
