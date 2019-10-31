package com.biz.grade.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.grade.config.DBConnection;
import com.biz.grade.dao.UserDao;
import com.biz.grade.persistence.UserDTO;

public class UserService {

	protected SqlSession sqlSession = null;
	protected Scanner scan = null;
	protected UserDao userDao = null;
	
	public UserService () {
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		userDao = sqlSession.getMapper(UserDao.class);
		scan = new Scanner(System.in);
	}
	
	public void viewUserAll() {
		System.out.println("=============================================");
		System.out.println("회원코드\t이름\t전화번호\t주소");
		System.out.println("=============================================");
		List<UserDTO> userList = userDao.selectAll();
		this.viewUserList(userList);
		System.out.println("=============================================");
	}
	
	public void userMenu() {
		while(true) {
			System.out.println("============================================");
			System.out.println("회원 정보 서비스");
			System.out.println("============================================");
			System.out.println("1. 회원 검색");
			System.out.println("2. 회원 등록");
			System.out.println("3. 회원 수정");
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
				this.userSearch();
			}else if(SelectMenu == 2) {
				this.userInsert();
			}else if(SelectMenu == 3) {
				this.userUpdate();
			}else {
				System.out.println("다시 입력해주세요.");
			}
		}
	}
	
	public void userSearch() {
		System.out.print("회원 이름 검색 >> ");
		String searchName = scan.nextLine();
		List<UserDTO> list = userDao.findByNameLike(searchName);
		System.out.println("=============================================");
		System.out.println("회원코드\t이름\t전화번호\t주소");
		System.out.println("=============================================");
		this.viewUserList(list);
		System.out.println("=============================================");
	}
	
	public void userInsert() {
		//TODO 유저 등록
		System.out.println("=============================================");
		System.out.println("회원 등록 서비스");
		System.out.println("=============================================");
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setU_code(this.makeUserCode());														//회원 코드 자동생성
		
		while(true) {																				//회원 이름 입력
			System.out.print("회원 이름 입력 (-Q : 종료) >> ");
			String u_name = scan.nextLine();
			if(u_name.equals("-Q")) return;
			if(u_name.trim().isEmpty()) {
				System.out.println("회원 이름은 반드시 입력해야합니다.");
				continue;
			}
			userDTO.setU_name(u_name);
			break;
		}
		
		while(true) {																				//회원 전화번호 입력
			System.out.print("전화번호 입력 (-Q : 종료) >> ");
			String u_tel = scan.nextLine();
			if(u_tel.equals("-Q")) return;
			userDTO.setU_tel(u_tel);
			
			List<UserDTO> userList = userDao.findByNameTel(userDTO.getU_name(), userDTO.getU_tel());
			if(userList.size()>0) {
				System.out.println("이름과 전화번호가 동일한 회원이 존재합니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}
		
		while(true) {																				//회원 주소 입력
			System.out.print("주소 입력 (-Q : 종료) >> ");
			String u_addr = scan.nextLine();
			if(u_addr.equals("-Q")) return;
			userDTO.setU_addr(u_addr);
			break;
		}
		
		this.viewUserDetail(userDTO);
		
		System.out.println("이대로 회원 등록합니까? (Enter : 등록) ");
		String yesNo = scan.nextLine();
		if(yesNo.trim().isEmpty()) {
			int ret = userDao.insert(userDTO);
			
			if(ret > 0 ) {
				System.out.println("회원 등록 완료");
			}else {
				System.out.println("회원 등록 실패");
			}
		}
	
		
	}
	
	public void userUpdate() {
		//TODO 유저 등록
		System.out.println("=============================================");
		System.out.println("회원 정보 수정 서비스");
		System.out.println("=============================================");
		System.out.print("회원 이름 검색 >> ");
		String searchName = scan.nextLine();
		List<UserDTO> list = userDao.findByNameLike(searchName);
		System.out.println("=============================================");
		this.viewUserList(list);
		System.out.println("=============================================");
		
		System.out.print("회원 코드 입력 >> ");
		String u_code = scan.nextLine();
		UserDTO userDTO = userDao.findById(u_code);
		
		while(true) {																				//회원 이름 수정
			System.out.printf("회원 이름 입력[%s] (-Q : 종료) >> ", userDTO.getU_name());
			String u_name = scan.nextLine();
			if(u_name.equals("-Q")) return;
			if(u_name.trim().isEmpty()) {
				break;
			}
			userDTO.setU_name(u_name);
			break;
		}
		
		while(true) {																				//회원 전화번호 입력
			System.out.printf("전화번호 입력[%s] (-Q : 종료) >> ", userDTO.getU_tel());
			String u_tel = scan.nextLine();
			if(u_tel.equals("-Q")) return;
			if(u_tel.trim().isEmpty()) {
				break;
			}
			userDTO.setU_tel(u_tel);
			
			List<UserDTO> userList = userDao.findByNameTel(userDTO.getU_name(), userDTO.getU_tel());
			if(userList.size()>0) {
				System.out.println("이름과 전화번호가 동일한 회원이 존재합니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}
		
		while(true) {																				//회원 주소 입력
			System.out.printf("주소 입력 [%s] (-Q : 종료) >> ", userDTO.getU_addr());
			String u_addr = scan.nextLine();
			if(u_addr.equals("-Q")) return;
			if(u_addr.trim().isEmpty()) {
				break;
			}
			userDTO.setU_addr(u_addr);
			break;
		}
		
		this.viewUserDetail(userDTO);
		
		System.out.println("이대로 회원정보를 수정합니까? (Enter : 수정) ");
		String yesNo = scan.nextLine();
		if(yesNo.trim().isEmpty()) {
			int ret = userDao.update(userDTO);
			
			if(ret > 0 ) {
				System.out.println("회원정보 수정 완료");
			}else {
				System.out.println("회원정보 수정 실패");
			}
		}
	
		
	}
	
	protected void viewUserDetail(UserDTO userDTO) {
		System.out.println("=============================================");
		System.out.print("회원 코드 : " + userDTO.getU_code() +"\n");
		System.out.print("이름 : " +userDTO.getU_name() +"\n");
		System.out.print("전화 번호 :" +userDTO.getU_tel()  +"\n");
		System.out.print("주소 : " +userDTO.getU_addr() +"\n");
		System.out.println("=============================================");
	}
	
	protected void viewUserDTO(UserDTO userDTO){
		System.out.print(userDTO.getU_code() +"\t");
		System.out.print(userDTO.getU_name() +"\t");
		System.out.print(userDTO.getU_tel()  +"\t");
		System.out.print(userDTO.getU_addr() +"\n");
	}
	
	protected void viewUserList(List<UserDTO> userList) {
		for (UserDTO userDTO : userList) {
			this.viewUserDTO(userDTO);
		}
	}
	
	protected String makeUserCode() {
		String getMaxCode = userDao.getMaxCode();
		int intCode = Integer.valueOf(getMaxCode.substring(1))+1;
		String u_code = "S"+String.format("%05d", intCode);
		
		return u_code;
	}
}
