package com.biz.iolist.service.dept;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.DeptDao;
import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV1 {

	protected DeptDao deptDao = null;
	Scanner scan = null;
	
	public DeptServiceV1() {
		deptDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(DeptDao.class);
		scan = new Scanner(System.in);
	}
	
	// deptDao.selectAll()을 호출하여 전체 리스트 조회
	
	public void viewAllList() {
		List<DeptDTO> list = deptDao.selectAll();
		
		this.viewList(list);
	}
	
	// 키보드에서 거래처이름을 입력하여 거래처 리스트를 보여주는 method
	public void viewNameList() {
		
		System.out.print("거래처 이름 입력 >> ");
		String d_name = scan.nextLine();
		
		List<DeptDTO> list = deptDao.findByName(d_name);
		
		this.viewList(list);
		
	}
	
	// 키보드에서 거래처이름과 대표이름을 입력하여 거래처 리스트를 보여주는 method
	public void viewNameAndCEOList() {
		
		System.out.print("거래처 이름 입력 >> ");
		String d_name = scan.nextLine();
		
		System.out.print("대표자 이름 입력 >> ");
		String d_ceo = scan.nextLine();
		
		List<DeptDTO> list = deptDao.findByNameAndCEO(d_name, d_ceo);
		
		this.viewList(list);
		
	}
	
	// 각 view에서 List를 출력할 때 사용할 method
	// List를 반복하면서 DTO를 매개변수로 전달
	protected void viewList(DeptDTO deptDTO) {
		System.out.printf("%s", deptDTO.getD_code() + "\t\t");
		System.out.printf("%s", deptDTO.getD_name() + "\t\t\t");
		System.out.printf("%s", deptDTO.getD_ceo() + "\t\t");
		System.out.printf("%s", deptDTO.getD_tel() + "\t");
		System.out.printf("%s", deptDTO.getD_addr() + "\n");
	}
	
	// List를 받아서 출력할때 사용할 method
	protected void viewList(List<DeptDTO> deptList) {
		
		if(deptList.size()<1 || deptList == null) {
			System.out.println("조회 결과 없습니다.");
		}
		

		System.out.println("=============================================================================");
		System.out.println("거래처코드\t거래처이름\t\t\t대표자이름\t연락처\t주소");
		System.out.println("=============================================================================");
		for (DeptDTO deptDTO : deptList) {
			this.viewList(deptDTO);
		}
		System.out.println("=============================================================================");
	}
	
}
