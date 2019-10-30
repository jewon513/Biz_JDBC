package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV2 extends DeptServiceV1 {

	public void deptMenu() {
		while (true) {
			System.out.println("==================================================");
			System.out.println("거래처 정보 관리");
			System.out.println("==================================================");
			System.out.println("1. 등록");
			System.out.println("2. 수정");
			System.out.println("3. 삭제");
			System.out.println("4. 검색");
			System.out.println("5. 종료");
			System.out.println("--------------------------------------------------");
			System.out.print("업무 선택 >> ");
			String strMenu = scan.nextLine();
			int intmenu = 0;
			try {
				intmenu = Integer.valueOf(strMenu);

			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				e.printStackTrace();
				continue;
				// TODO: handle exception
			}

			if (intmenu == 0) {
				System.out.println("업무 종료");
				break;
			} else if (intmenu == 1) {
				this.deptInsert();
			} else if (intmenu == 2) {
				this.deptUpdate();
			} else if (intmenu == 3) {
				this.deptDelete();
			} else if (intmenu == 4) {
				this.viewNameList();
			}

		}

	}

	public void deptInsert() {
		// TODO Auto-generated method stub
		
	}

	private void deptDelete() {

		while (true) {
			// 상호로 검색을 해서 리스트를 보여주고
			this.viewNameList();

			// 보여준 리스트 중에서 거래처 코드를 입력받아서 삭제

			System.out.println("삭제할 거래처 코드 (-Q : 종료)>> ");
			String strDCode = scan.nextLine();
			if (strDCode.equals("-Q"))
				break;

			DeptDTO deptDTO = deptDao.findById(strDCode);

			if (deptDTO == null) {
				System.out.println("삭제할 거래처 코드가 없습니다.");
				continue;
			}

			this.viewDetail(deptDTO);

			System.out.println("정말 삭제하시겠습니까? (Enter : 삭제)");

			String strYesNO = scan.nextLine();
			if (strYesNO.trim().isEmpty()) {
				int ret = deptDao.delete(strDCode);

				if (ret > 0) {
					System.out.println("삭제 완료");
					break;
				} else {
					System.out.println("삭제 실패");
				}
			}

		}

	}// end delete;

	private void deptUpdate() {
		while (true) {
			
			this.viewNameList();
			
			System.out.print("수정할 거래처 코드 (-Q : 종료)");
			String strDCode = scan.nextLine();
			if (strDCode.equals("-Q"))
				break;

			DeptDTO deptDTO = deptDao.findById(strDCode);

			if (deptDTO == null) {
				System.out.println("수정할 거래처 코드가 없습니다.");
				continue;
			}

			this.viewDetail(deptDTO);
			
			// 상호명 변경
			System.out.printf("수정할 거래처 상호 >>");
			String strDName = scan.nextLine();
			if (!strDName.trim().isEmpty()) {
				deptDTO.setD_name(strDName);
			}
			
			
			// 대표자 변경
			System.out.printf("수정할 대표자 이름 >>");
			String strDCEO = scan.nextLine();
			if (!strDCEO.trim().isEmpty()) {
				deptDTO.setD_ceo(strDCEO);
			}
			
			// 전화 변경
			System.out.printf("수정할 거래처 전화번호 >>");
			String strDTel = scan.nextLine();
			if (!strDTel.trim().isEmpty()) {
				deptDTO.setD_tel(strDTel);
			}
			
			// 주소 변경
			System.out.printf("수정할 거래처 주소 >>");
			String strDAddr = scan.nextLine();
			if (!strDAddr.trim().isEmpty()) {
				deptDTO.setD_addr(strDAddr);
			}
			
			// 수정한값 확인
			
			this.viewDetail(deptDTO);
			
			System.out.println("이대로 수정하시겠습니까? (Enter : 수정)");
			String strYes = scan.nextLine();
			if(strYes.trim().isEmpty()) {
				int ret = deptDao.update(deptDTO);
				
				if (ret>0) {
					System.out.println("데이터 수정 완료");
					break;
				}else {
					System.out.println("데이터 수정 실패");
				}
			}
			System.out.println("데이터 수정을 취소하셨습니다.");
		
		}
	}

}
