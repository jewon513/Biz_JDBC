package com.biz.dbms.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder

public class BBsDTO {

	/*
	 * DTO, VO를 생성할때 필드(멤버변수)이름은 TABLE은 COLUMN 이름과 같게 설정한다.
	 */
	private long bs_id;
	private String bs_date;
	private String bs_time;
	private String bs_writer;
	private String bs_subject;
	private String bs_text;
	private int bs_count;
	
}
