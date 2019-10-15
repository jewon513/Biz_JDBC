package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCService3;

public class JdbcEx_07 {

	public static void main(String[] args) {

		ScoreJDBCService3 sc = new ScoreJDBCService3();
		
		ScoreVO scoreVO = ScoreVO.builder().s_id(601).s_std("이몽룡").s_score(100).s_remark("연습").build();
		
		int ret = sc.insert(scoreVO);
		System.out.println(ret);
		
		List<ScoreVO> scoreList = sc.findById(601);
		
		for(ScoreVO s : scoreList) {
			System.out.println(s.toString());
		}
		
		
		
		
		
	}

}
