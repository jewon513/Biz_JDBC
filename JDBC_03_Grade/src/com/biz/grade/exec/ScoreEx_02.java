package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.Service.ScoreServiceV2;
import com.biz.grade.Service.extend.ScoreServiceV2Ext;
import com.biz.grade.persistence.ScoreVO;

public class ScoreEx_02 {

	public static void main(String[] args) {
		
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		
		Scanner scan= new Scanner(System.in);
		
		while(true) {
			System.out.println("==================================");
			System.out.println("성적검색");
			System.out.println("==================================");
			System.out.print("이름 입력 (-Q:quie) >> ");
			String strName = scan.nextLine();
			
			if(strName.equals("-Q")) break;

			List<ScoreVO> scoreList = sc.findByStName(strName);
			
			if(scoreList == null || scoreList.size()<1) {
				System.out.println("데이터가 없음");
				System.out.println("학생이름을 다시 입력");
				continue;
			}else {
				for (ScoreVO scoreVO : scoreList) {
					System.out.println(scoreVO);
				}
			}
			
		}
		System.out.println("종료");
		
	}

}
