package com.biz.iolist.persistence;

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
public class IolistVO {

	private String io_date;
	private String io_inout;
	private String io_dcode;
	private String d_name;
	private String d_ceo;
	private String d_tel;
	private String d_addr;
	private String io_pcode;
	private String p_name;
	private int p_iprice;
	private int p_oprice;
	private String p_vat;
	private int io_qty;
	private int io_price;
	private int io_total;
	private long io_seq;
	
}
