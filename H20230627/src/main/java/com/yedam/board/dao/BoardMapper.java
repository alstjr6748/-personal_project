package com.yedam.board.dao;

import java.util.List;

import com.yedam.board.vo.BoardVO;

public interface BoardMapper {
	public List<BoardVO> getList(int page);
	public int selectCount();
	public BoardVO selectBoard(long brdNo);
}
