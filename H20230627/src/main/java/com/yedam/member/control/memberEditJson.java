package com.yedam.member.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;
import com.yedam.member.vo.MemberVO;

public class memberEditJson implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// id, passwd, phone, addr
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String phone = req.getParameter("ph");
		String addr = req.getParameter("ad");

		MemberVO vo = new MemberVO();

		vo.setUserId(id);
		vo.setUserPw(pw);
		vo.setUserPhone(phone);
		vo.setUserAddr(addr);

		MemberService service = new MemberServiceImpl();

		service.modifyMember(vo);

		Gson gson = new GsonBuilder().create();
		return gson.toJson(vo) + ".json";
	}

}
