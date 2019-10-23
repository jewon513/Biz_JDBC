package com.biz.mybatis.persistence;

import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.Getter;

@Getter
@Setter
@ToString
@Builder

public class BookDTO {

	private String b_code;	//	varchar2(5 byte)
	private String b_name;	//	nvarchar2(50 char)
	private String b_comp;	//	nvarchar2(50 char)
	private String b_writer;//	nvarchar2(20 char)
	private int b_price;	//	number
	
}
