package com.biz.oracle.persistence;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class BookDTO {

	private String b_code;//	varchar2(5 byte)
	private String b_name;//	nvarchar2(50 char)
	private String b_comp;//	nvarchar2(50 char)
	private String b_writer;//	nvarchar2(20 char)
	private int b_price;//	number
	
}
