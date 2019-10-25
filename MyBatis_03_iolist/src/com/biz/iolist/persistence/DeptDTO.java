package com.biz.iolist.persistence;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString


public class DeptDTO {

	private String d_code;//	varchar2(5 byte)
	private String d_name;//	nvarchar2(50 char)
	private String d_ceo;//	nvarchar2(50 char)
	private String d_tel;//	varchar2(50 byte)
	private String d_addr;//	nvarchar2(125 char)
	
	
}
