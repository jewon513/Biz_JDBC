package com.biz.grade.persistence.domain;

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

public class ScoreDTO {

	private long s_id;//		number
	private int s_score;//		number(3,0)
	private String s_remark;//	nvarchar2(50 char)
	private String s_subject;//	varchar2(4 byte)
	private String s_std;//		varchar2(5 byte)
	
}
