package com.biz.grade.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookDTO {

	private String b_code;//	varchar2(6 byte)
	private String b_name;//	nvarchar2(125 char)
	private String b_auther;//	nvarchar2(125 char)
	private int b_rprice;//	number
	private int b_year;//	number
	private int b_iprice;//	number
	private String b_comp;//	nvarchar2(125 char)
	
}
