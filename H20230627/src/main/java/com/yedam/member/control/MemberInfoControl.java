package com.yedam.member.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;
import com.yedam.member.vo.MemberVO;

public class MemberInfoControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// getmember(id), select(id) => MemberVO
		// admin/memberInfo.jsp
		String id = req.getParameter("uid");

		MemberService service = new MemberServiceImpl();
		MemberVO vo = service.select(id);

		req.setAttribute("member", vo);

		return "admin/memberInfo.tiles";
	}

}
