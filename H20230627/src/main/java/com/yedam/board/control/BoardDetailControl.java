package com.yedam.board.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class BoardDetailControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		return "board/boardDetail.tiles";
	}

}
