package com.yedam.member.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class CalendarControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {

		return "admin/calendar.tiles";
	}

}
