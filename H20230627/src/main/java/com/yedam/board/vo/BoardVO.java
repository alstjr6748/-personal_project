package com.yedam.board.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	private long brdNo;
	private String brdTitle;
	private String brdWriter;
	private String brdContent;
	private Date createDate;
	private int clickCnt;
}
