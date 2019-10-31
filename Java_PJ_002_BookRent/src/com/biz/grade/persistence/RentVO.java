package com.biz.grade.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class RentVO {

	private String rent_seq; 
	private String u_code;//	VARCHAR2(6)
	private String u_name;//	NVARCHAR2(125)
	private String b_code;//	VARCHAR2(6)
	private String b_name;//	NVARCHAR2(125)
	private String rent_date;//	VARCHAR2(10)
	private String rent_return_date;//	VARCHAR2(10)
	private String rent_retur_yn;//	VARCHAR2(1)
	
}
