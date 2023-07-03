package com.yedam.board.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.board.service.ReplyService;
import com.yedam.board.service.ReplyServiceImpl;
import com.yedam.board.vo.ReplyVO;
import com.yedam.common.Control;

public class addReplyControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 원본글, 작성자, 댓글내용
		String bno = req.getParameter("bno");
		String replyer = req.getParameter("replyer");
		String reply = req.getParameter("reply");

		ReplyVO vo = new ReplyVO();
		vo.setBrdNo(Long.parseLong(bno));
		vo.setReplyer(replyer);
		vo.setReply(reply);
		vo.setReplyDate(new Date());

		ReplyService service = new ReplyServiceImpl();
		service.addReply(vo);

		Gson gson = new GsonBuilder().create();

		return gson.toJson(vo) + ".json";
	}

}
