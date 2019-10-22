package com.biz.addr.persistence;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class AddrDTO {

	private long id;
	private String name;
	private String tel;
	private String addr;
	private String chain;
	
}
