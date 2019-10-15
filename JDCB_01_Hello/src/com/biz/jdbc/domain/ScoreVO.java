package com.biz.jdbc.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class ScoreVO {
	
	private long s_id;
	private String s_std;
	private int s_score;
	private String s_remark;
	
	public ScoreVO(long s_id, String n_std, int s_score, String s_remark) {
		super();
		this.s_id = s_id;
		this.s_std = n_std;
		this.s_score = s_score;
		this.s_remark = s_remark;
	}
	
	public ScoreVO() {
		super();
	}
	
	
}
