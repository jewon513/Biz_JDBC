package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCService;

public class JdbcEx_03 {

	public static void main(String[] args) {

		ScoreJDBCService sc = new ScoreJDBCService();
		
		sc.selectAll();
		List<ScoreVO> scoreList = sc.getScoreList();
		
		for(ScoreVO vo : scoreList) {
			System.out.println(vo.toString());
		}
		
	}

}
