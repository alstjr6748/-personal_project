package com.yedam.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//글등록.삭제.글내용수정.글목록보기.상세보기

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private int    boardId;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String date;
	private int    cnt;
}
