package com.yedam.calendar.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.calendar.service.CalendarService;
import com.yedam.calendar.service.CalendarServiceImpl;
import com.yedam.calendar.vo.CalendarVO;
import com.yedam.common.Control;

public class AddEventControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		CalendarService service = new CalendarServiceImpl();

		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end   = req.getParameter("end");

		CalendarVO vo = new CalendarVO();
		vo.setTitle(title);
		vo.setStartDate(start);
		vo.setEndDate(end);

		String json = "";
		// {"retCode":"Success"} / {"retCode":"Fail"}
		if(service.addEvent(vo)) {
			json = "{\"retCode\":\"Success\"}";
		} else {
			json = "{\"retCode\":\"Fail\"}";
		}
		return json + ".json";
	}

}
