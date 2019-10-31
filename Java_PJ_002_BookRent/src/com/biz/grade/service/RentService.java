package com.biz.grade.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.grade.config.DBConnection;
import com.biz.grade.dao.BookDao;
import com.biz.grade.dao.RentBookDao;
import com.biz.grade.dao.UserDao;
import com.biz.grade.persistence.BookDTO;
import com.biz.grade.persistence.RentBookDTO;
import com.biz.grade.persistence.UserDTO;

public class RentService {

	protected SqlSession sqlSession = null;
	protected BookDao bookDao = null;
	protected UserDao userDao = null;
	protected RentBookDao rentDao = null;
	protected Scanner scan = null;
	
	public RentService() {
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		bookDao = sqlSession.getMapper(BookDao.class);
		userDao = sqlSession.getMapper(UserDao.class);
		rentDao = sqlSession.getMapper(RentBookDao.class);
		scan = new Scanner(System.in);
	}

	public void viewRentListAll() {
		List<RentBookDTO> rentList = rentDao.selectAll();
		this.viewRentList(rentList);
	}
	
	public void rentMenu() {
		while(true) {
			System.out.println("============================================");
			System.out.println("도서 정보 서비스");
			System.out.println("============================================");
			System.out.println("1. 도서 대출");
			System.out.println("2. 도서 반납");
			System.out.println("3. 대여리스트 삭제");
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
				this.bookRentInsert();
			}else if(SelectMenu == 2) {
				this.bookReturnUpdate();
			}else if(SelectMenu == 3) {
				this.bookDelete();
			}else {
				System.out.println("다시 입력해주세요.");
			}
		}
	}
	
	
	
	private void bookDelete() {
		List<RentBookDTO> list = rentDao.selectAll();
		this.viewRentList(list);
		System.out.print("삭제할 SEQ를 입력하세요 (-Q : 종료 ) >> ");
		String strSeq = scan.nextLine();
		if(strSeq.equals("-Q")) return;
		
		
		int seq = 0 ;
		try {
			seq = Integer.valueOf(strSeq);
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요");
			// TODO: handle exception
		}
		
		int ret = rentDao.delete(seq);
		
		if(ret>0) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
		
	}

	public void bookRentInsert() {
		System.out.println("=============================================================================================");
		System.out.println("도서 대출 등록");
		System.out.println("=============================================================================================");
		RentBookDTO rentDTO = new RentBookDTO();
		while(true) {																				// 도서 이름으로 검색
			System.out.print("도서명 검색 (-Q : 종료 ) >>");
			String b_name = scan.nextLine();
			if(b_name.equals("-Q")) return;
			
			List<BookDTO> bookDTOList = bookDao.findByNameLike(b_name);
			this.viewBookList(bookDTOList);
			
			if(bookDTOList.size()<1) {
				System.out.println("검색 결과가 없습니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}
		
		while(true) {																				// 도서 코드 입력
			System.out.print("도서 코드 입력 (-Q : 종료 ) >> ");
			String b_code = scan.nextLine();
			BookDTO bookDTO = bookDao.findById(b_code);
			if(b_code.equals("-Q")) return;
			
			if(bookDTO==null) {
				System.out.println("등록되지 않은 코드 입니다.");
				continue;
			}
			
			List<RentBookDTO> rentList = rentDao.checkRent(b_code);
			if(rentList.size()>0) {
				System.out.println("이미 대출된 도서입니다.");
				this.viewRentList(rentList);
				continue;
			}
			
			rentDTO.setRent_bcode(b_code);
			break;
		}
		
		while(true) {																				// 회원 이름으로 검색
			System.out.print("회원 이름 검색 (-Q : 종료 ) >> ");
			String u_name = scan.nextLine();
			if(u_name.equals("-Q")) return;
			List<UserDTO> userDTOList = userDao.findByNameLike(u_name);
			
			
			if(userDTOList.size()<1) {
				System.out.println("검색 결과가 없습니다. 다시 입력해주세요.");
				continue;
			}
			
			this.viewUserList(userDTOList);
			break;
		}
		
		while(true) {																				// 회원 코드 입력
			System.out.print("회원 코드 입력 (-Q : 종료 ) >> ");
			String u_code = scan.nextLine();
			if(u_code.equals("-Q")) return;
			UserDTO userDTO = userDao.findById(u_code);
			
			if(userDTO==null) {
				System.out.println("등록되지 않은 코드 입니다.");
				continue;
			}
	
			
			rentDTO.setRent_ucode(u_code);
			break;
			
		}
		
		Calendar cal = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		cal.setTime(date);
		cal.add(Calendar.DATE, 14);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String rentDate = sd.format(date);
		String returnDate = sd.format(cal.getTime());
		
		rentDTO.setRent_date(rentDate);
		rentDTO.setRent_return_date(returnDate);
		
		int ret = rentDao.insert(rentDTO);
		
		if(ret>0) {
			System.out.println("대출 등록 성공");
		}else {
			System.out.println("대출 등록 실패");
		}
		
		
	}
	
	public void bookReturnUpdate() {
		
		System.out.println("=============================================================================================");
		System.out.println("미반납 도서 목록");
		List<RentBookDTO> rentList = rentDao.noReturnBookCheck();
		this.viewRentList(rentList);
		
		RentBookDTO rentDTO;
		while(true) {																									//미반납 확인
			System.out.print("반납할 SEQ 입력 (-Q : 종료 ) >> ");
			String strSeq = scan.nextLine();
			if(strSeq.equals("-Q")) return;
			int seq = 0;
			try {
				seq = Integer.valueOf(strSeq);
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
				// TODO: handle exception
			}
			rentDTO = rentDao.findById(seq);
			if(rentDTO == null) {
				System.out.println("대여 리스트에 존재하지 않습니다.");
				continue;
			}
			
			List<RentBookDTO> checkList = rentDao.checkRent(rentDTO.getRent_bcode());
			if(checkList.size()<1) {
				System.out.println("미반납 도서가 아닙니다.");
				continue;
			}
			break;
		}
		
		if(rentDTO.getRent_retur_yn()==null) {
			rentDTO.setRent_retur_yn("Y");
		}
		
		// 포인트 부분 추가
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date beginDate = new Date(System.currentTimeMillis());
			Date endDate = sd.parse(rentDTO.getRent_return_date());
			
			long diff = endDate.getTime() - beginDate.getTime();
			if(diff>=0) {
				rentDTO.setRent_point(5);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		int ret = rentDao.update(rentDTO);
		if(ret >0) {
			System.out.println("반납 완료");	
		}else {
			System.out.println("반납 실패");
		}
		
	}
	
	
	
	
	
	protected void viewUserDTO(UserDTO userDTO){
		System.out.print(userDTO.getU_code() +"\t");
		System.out.print(userDTO.getU_name() +"\t");
		System.out.print(userDTO.getU_tel()  +"\t");
		System.out.print(userDTO.getU_addr() +"\n");
	}
	
	protected void viewUserList(List<UserDTO> userList) {
		System.out.println("=============================================");
		System.out.println("회원코드\t이름\t전화번호\t주소");
		System.out.println("=============================================");
		for (UserDTO userDTO : userList) {
			this.viewUserDTO(userDTO);
		}
		System.out.println("=============================================");
	}
	
	protected void viewRentList(List<RentBookDTO> rentBookList) {
		System.out.println("=============================================================================================");
		System.out.println("SEQ\t대출일\t\t반납예정일\t도서코드\t회원코드\t도서반납여부\t포인트");
		for (RentBookDTO rentBookDTO : rentBookList) {
			this.viewRentDTO(rentBookDTO);
		}
		System.out.println("=============================================================================================");
	}
	
	protected void viewRentDTO(RentBookDTO rentBookDTO) {
		System.out.print(rentBookDTO.getRent_seq() +"\t");
		System.out.print(rentBookDTO.getRent_date() +"\t");
		System.out.print(rentBookDTO.getRent_return_date()+"\t");
		System.out.print(rentBookDTO.getRent_bcode()+"\t\t");
		System.out.print(rentBookDTO.getRent_ucode()+"\t\t");
		System.out.print(rentBookDTO.getRent_retur_yn()+"\t\t");
		System.out.print(rentBookDTO.getRent_point() +"\n");
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
}
