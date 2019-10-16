package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV1;

public class ScoreEx_01 {

	public static void main(String[] args) {

		// 추상클래스로 선언하고 구현클래스로 생성, 초기화 하였다.
		ScoreService sc = new ScoreServiceV1();
		
		List<ScoreDTO> scoreList = sc.selectAll();
		
		if(scoreList == null) {
			System.out.println("성적데이터를 읽을 수 없습니다.!!");
			//main() method에서 return을 수행하면 프로젝트가 종료됨
			return; 
		}
		
		for(ScoreDTO vo : scoreList) {
			System.out.println(vo);
		}
		
	}

}
