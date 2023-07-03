package com.yedam.board.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.board.service.ReplyService;
import com.yedam.board.service.ReplyServiceImpl;
import com.yedam.board.vo.ReplyVO;
import com.yedam.common.Control;

public class editReplyControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String reply = req.getParameter("reply");
		String rno = req.getParameter("rno");

		ReplyVO vo = new ReplyVO();
		vo.setReply(reply);
		vo.setReplyNo(Long.parseLong(rno));

		ReplyService service = new ReplyServiceImpl();
		if(service.modifyReply(vo)) {
			vo = service.getReply(Long.parseLong(rno));
		}

		Gson gson = new GsonBuilder().create();

		return gson.toJson(vo) + ".json";
	}

}
