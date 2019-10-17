package com.biz.grade.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * ScoreVO는 테이블을 JOIN해서 얻어진 VIEW 결과를 DBMS로부터 받아서
 * 다른 class에 전달 하기 위한 용도로 사용된다.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ScoreVO {

	private String s_std;//		varchar2(5)
	private String st_name;//	nvarchar2(50)
	private int st_grade;//		number(1)
	private String st_dept;//	varchar2(5)
	private String d_name;//	nvarchar2(30)
	private String d_tel;//		nvarchar2(20)
	private String s_subject;//	varchar2(4)
	private String sb_name;//	nvarchar2(20)
	private int s_score;//		number(3)
	private long s_id;//			number
	
}
