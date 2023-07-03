package com.yedam.board.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.board.service.ReplyService;
import com.yedam.board.service.ReplyServiceImpl;
import com.yedam.common.Control;

public class delReplyControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String> map = new HashMap<>();
		String rno = req.getParameter("rno");

		ReplyService service = new ReplyServiceImpl();
		if(service.removeReply(Long.parseLong(rno))) {
			map.put("retCode", "Sussess");
		} else {
			map.put("retCode", "Fail");
		}

		Gson gson = new GsonBuilder().create();

		return gson.toJson(map) + ".json";
	}

}
