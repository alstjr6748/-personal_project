package com.yedam.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
	private int BookNum;
	private String BookTitle;
	private String BookAuthor;
	private String BookContent;
	private String BookDate;
	private int BookCnt;
	private int maxSize;
}
