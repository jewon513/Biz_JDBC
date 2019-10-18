package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.Service.ScoreServiceV2;
import com.biz.grade.Service.extend.ScoreServiceV2Ext;
import com.biz.grade.persistence.domain.ScoreVO;

public class ScoreDeleteEx_01 {
/*
 * 1. 학생이름을 입력받아서
 * 2. 성적리스트를 보여주고
 * 3. 리스트를 보고 ID를 입력 받아서
 * 4. 해당하는 레코드를 삭제
 * 
 */
	public static void main(String[] args) {

		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		
		Scanner scan = new Scanner(System.in);
		String strStNum= null;
		List<ScoreVO> scoreList = null;
		/*
		 * 학번을 입력받아서
		 * 학번이 학생테이블에 있으면 리스트를 보여주고
		 * 그렇지 않으면 반복문을 계속 반복
		 */
		;
		
		while(true) {
			
			System.out.print("학번 (-Q:quit) >>");
			strStNum = scan.nextLine();
			if(strStNum.equals("-Q")) {
				System.out.println(" 성적 입력 종료 !!");
				break;
			}
			// 학번조회
			scoreList = sc.findByStNum(strStNum);
			if(scoreList == null || scoreList.size()<1) {
				System.out.println("학생정보에 학번이 없습니다.");
				System.out.println("학생정보를 먼저 등록하세요.");
				continue;
			}
			
			break;
			
		}
		if(strStNum.equals("-Q")) return;
		
		while(true) {
			System.out.println("=======================================");
			System.out.println("ID\t학생이름\t과목\t점수");
			System.out.println("=======================================");
			for(ScoreVO vo : scoreList) {
				System.out.print(vo.getS_id() + "\t");
				System.out.print(vo.getSt_name() + "\t\t");
				System.out.print(vo.getS_subject() + "\t");
				System.out.print(vo.getS_score() + "\n");
			}
			System.out.println("=======================================");
			System.out.print("삭제할 ID (-Q:quit) >> ");
			String strID = scan.nextLine();
			
			if(strID.equals("-Q")) {
				System.out.println("종료");
				break;
			}
			
			try {
				long intId = Long.valueOf(strID);
				int ret = sc.delete(intId);
				if(ret > 0) {
					System.out.println("삭제 완료");
					scoreList = sc.findByStNum(strStNum);
				}else {
					System.out.println("삭제 실패");
				}
				continue;
			} catch (Exception e) {
				System.out.println("ID는 숫자만 가능");
			}
			
		}
		
	}

}
