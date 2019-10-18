package com.biz.grade.exec;

import java.util.Random;

import com.biz.grade.Service.ScoreServiceV3;
import com.biz.grade.persistence.domain.ScoreDTO;

public class ScoreInput_02 {

	public static void main(String[] args) {

		ScoreServiceV3 sc = new ScoreServiceV3();
		
		String strStNum = sc.inputStudent();
//		if(strStNum != null) {
//			String strSubject = sc.inputSubject();
//			if(strSubject !=null) {
//				String strScore = sc.inputScore();
//				if(strScore !=null)
//					//input 처리
//			}
//		}
		if(strStNum == null) {
			System.out.println("성적입력 종료!!");
			return; // 프로젝트 종료
		}
		String strSubject = sc.inputSubject();
		if(strSubject == null) {
			System.out.println("성적입력 종료!!");
			return; // 프로젝트 종료
		}
		/*
		 * metohd에서 숫자값을 return 할때 만약 값을 입력하지 않는경우
		 * int 형 같으면 보통 0을 리턴 하는데 이게 0점인지 입력하지 않은 것인지
		 * 알 수가 없기 때문에 Integer로 선언하여 null값인지 아닌지 체크하는 것이다.
		 */
		Integer intScore = sc.inputScore();
		if(intScore == null) {
			System.out.println("성적입력 종료!!");
			return; // 프로젝트 종료
		}
		
		Random rnd = new Random();
		ScoreDTO scoreDTO = ScoreDTO.builder()
				.s_id(rnd.nextLong())
				.s_std(strStNum.toUpperCase())
				.s_subject(strSubject.toUpperCase())
				.s_score(intScore)
				.build();
		
		int ret = sc.insert(scoreDTO);
		if(ret > 0) {
			System.out.println("데이터 추가 성공");
		}else {
			System.out.println("데이터 추가 실패");
		}
		
	}

}
