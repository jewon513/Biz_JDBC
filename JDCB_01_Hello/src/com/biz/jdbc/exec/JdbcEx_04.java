package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCService2;

public class JdbcEx_04 {

	public static void main(String[] args) {

		ScoreJDBCService2 sc = new ScoreJDBCService2();
		
		List<ScoreVO> scoreList = sc.getScoreList();
		
		scoreList = sc.findById(30);
		for(ScoreVO vo : scoreList) {
			System.out.println(vo.toString());
		}
	
		
	}

}
