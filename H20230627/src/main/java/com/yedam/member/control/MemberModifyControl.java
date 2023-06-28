package com.yedam.member.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;
import com.yedam.member.vo.MemberVO;

public class MemberModifyControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("uid");
		String pw = req.getParameter("upw");
		String phone = req.getParameter("uph");
		String addr = req.getParameter("uad");
		System.out.println(id+pw+phone+addr);
		MemberVO vo = new MemberVO();
		vo.setUserId(id);
		vo.setUserPw(pw);
		vo.setUserPhone(phone);
		vo.setUserAddr(addr);

		MemberService service = new MemberServiceImpl();
		String json = "";

		if(service.modifyMember(vo)) {
			json = "{\"retCode\": \"Success\"}";
		} else {
			json = "{\"retCode\": \"Fail\"}";
		}

		// {"retCode": "Success"} // {"retCode": "Fail"}

		return json + ".json";
	}

}
