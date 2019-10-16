package com.biz.grade.persistence;
/*
 * DB와 관련된 용어
 * Table과 연관된 Class들의 묶음(package)
 * vo(Value Object)
 * domain
 * command
 * entity
 * persistence : 영속성, 지속성(메모리가 아닌 물리적 공간에 존재해서)
 * 
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ScoreVO {

	private String s_std;		//	varchar2(5)
	private String st_name;		//	nvarchar2(50)
	private int st_grade;		//	number(1)
	private String st_dept;		//	varchar2(5)
	private String d_name;		//	nvarchar2(30)
	private String d_tel;		//	nvarchar2(20)
	private String s_subject;	//	varchar2(4)
	private String sb_name;		//	nvarchar2(20)
	private int s_score;		//	number(3)
	private int s_id;			//	number
	
}
