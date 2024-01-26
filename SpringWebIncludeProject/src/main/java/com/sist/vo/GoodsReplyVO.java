package com.sist.vo;

import java.util.Date;

import lombok.Data;

/*
		RNO     NOT NULL NUMBER       
		NO               NUMBER       
		ID               VARCHAR2(20) 
		NAME    NOT NULL VARCHAR2(51) 
		MSG     NOT NULL CLOB         
		REGDATE          DATE      
 */
@Data
public class GoodsReplyVO {
	private int rno, no;
	private String id, name, msg, dbday;
	private Date regdate;
}
