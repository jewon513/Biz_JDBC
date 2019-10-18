package com.biz.grade.exec;

import java.util.List;
import java.util.Random;

import com.biz.grade.Service.ScoreServiceV3;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;

/*
 * 1. 학생이름을 입력받아서
 * 2. 학생정보 리스트를 보여주고
 * 3. 학번을 입력받고
 * 4. 학번에 해당하는 성적리스트를 보여주고
 * 5. 리스트를 보고 ID를 입력하면
 * 6. 각 칼럼별로
 * 		값을 보여주고
 * 		그냥 Enter를 입력하면 원래 값을 유지하고
 * 		새로운 값을 입력하고 Enter를 입력하면 새로운 값으로 변경
 *
 *  ID 100을 선택했다
 *  학번 (T0020) >> Enter  			이경우는 T0020으로 그냥 유지
 *  과목 (S003) >> S004 Enter 		이경우는 S004로 변경
 *  점수 (33) >> 90 Enter			이경우는 90으로 변경
 *  
 */
public class ScoreUpdateEx_01 {

	public static void main(String[] args) {

		ScoreServiceV3 sc = new ScoreServiceV3();
		ScoreDTO scoreDTO= null;

		List<ScoreVO> scList = sc.updateStudent();

		if ( scList == null || scList.size()<1) {
			System.out.println("성적입력 종료!!");
			return; // 프로젝트 종료
		}
		
		for (ScoreVO scoreVO : scList) {
			System.out.print(scoreVO.getS_id() + "\t");
			System.out.print(scoreVO.getSt_name() +"\t");
			System.out.print(scoreVO.getS_score() + "\n");
		}
		
		ScoreVO scoreVO = sc.selectScore();
		if (scoreVO == null) {
			System.out.println("성적입력 종료!!");
			return; // 프로젝트 종료
		}
		
		System.out.println(scoreVO);
		
		int ret = sc.updateScore(scoreVO);
		if(ret > 0) {
			System.out.println("데이터 변경 완료");
		}else {
			System.out.println("데이터 변경 실패");
		}
		
		


	}

}
