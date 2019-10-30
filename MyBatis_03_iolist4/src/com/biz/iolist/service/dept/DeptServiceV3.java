package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV3 extends DeptServiceV2 {

	/*
	 * 1. 거래처 코드는 자동으로 생성할 것이다. 
	 * 2. 상호는 같은 상호가 있으더라도 대표자명이 다르면 입력 가능
	 */
	public void deptInsert() {

		// 코드 자동 생성
		String strMaxDcode = deptDao.getMaxDcode();
		int intMaxDcode = Integer.valueOf(strMaxDcode.substring(1)) + 1;
		String dCode = strMaxDcode.substring(0, 1) + String.format("%04d", intMaxDcode);

		System.out.println("==================================================");
		System.out.println("거래처 등록");
		System.out.println("==================================================");
		System.out.println("거래처 코드 : " + dCode);
		String strDName;
		while (true) {
			System.out.print("거래처 이름 입력 (-Q : 종료) >> ");
			strDName = scan.nextLine();
			if (strDName.equals("-Q"))
				return;
			if (strDName.trim().isEmpty()) {
				System.out.println("거래처 이름은 반드시 입력해야 합니다.");
				continue;
			}
			break;
		}

		String strDCEO;
		while (true) {
			System.out.print("대표자 이름 입력 (-Q : 종료) >> ");
			strDCEO = scan.nextLine();
			if (strDCEO.equals("-Q"))
				return;
			if (strDCEO.trim().isEmpty()) {
				System.out.println("대표자 이름은 반드시 입력해야 합니다.");
				continue;
			}
			break;
		}

		List<DeptDTO> list = deptDao.findByNameAndCEO(strDName, strDCEO);
		if (list.size() > 0) {
			System.out.println("상호명과 대표자가 같은 거래처가 존재합니다.");
			return;
		}

		System.out.print("거래처 연락처 입력 (-Q : 종료) >> ");
		String strDtel = scan.nextLine();
		if (strDtel.equals("-Q"))
			return;

		System.out.print("거래처 주소 입력 (-Q : 종료) >> ");
		String strDAddr = scan.nextLine();
		if (strDAddr.equals("-Q"))
			return;

		DeptDTO deptDTO = DeptDTO.builder().d_code(dCode).d_name(strDName).d_ceo(strDCEO).d_tel(strDtel)
				.d_addr(strDAddr).build();

		int ret = deptDao.insert(deptDTO);

		if (ret > 0) {
			System.out.println("데이터 입력 완료");
		} else {
			System.out.println("데이터 입력 실패");
		}

	}
}
