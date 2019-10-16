package com.biz.grade.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * DBMS Table과 연관된 Class
 * VO(value object class) DTO(Data Transfer Object)
 * 
 * - 공통기능
 * 		Table과 연관되어서 CRUD를 수행할 때
 * 		데이터를 담아서 method간에 이동할 때 사용
 * - DTO
 * 		물리적 Table과 연관(매핑)되어 완전한 CRUD를 수행할 때
 * - VO
 *		VIEW Table, Join 된 SQL과 연관되어
 *		주로 READ용으로 사용
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ScoreDTO {

	private long s_id;
	private String s_std; //학번
	private String s_subject; //과목코드
	private int s_score; //과목점수
	private String s_remark; //비고
	
}