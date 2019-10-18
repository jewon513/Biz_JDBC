package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.Service.ScoreServiceV2;
import com.biz.grade.Service.extend.ScoreServiceV2Ext;
import com.biz.grade.persistence.domain.ScoreVO;

public class ScoreEX_01 {

	public static void main(String[] args) {

		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		
		List <ScoreVO> scoreList = sc.selectAll();
		if(scoreList == null || scoreList.size()<1){
			System.out.println("데이터가 없습니다.");
			return;
		}
		for(ScoreVO vo : scoreList) {
			System.out.println(vo);
		}
	}

}
