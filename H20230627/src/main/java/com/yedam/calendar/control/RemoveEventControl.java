package com.yedam.calendar.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.calendar.service.CalendarService;
import com.yedam.calendar.service.CalendarServiceImpl;
import com.yedam.calendar.vo.CalendarVO;
import com.yedam.common.Control;

public class RemoveEventControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String> map = new HashMap<>();
		String title = req.getParameter("title");
		CalendarVO vo = new CalendarVO();
		vo.setTitle(title);

		CalendarService service = new CalendarServiceImpl();
		if(service.removeEvent(vo)) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}

		Gson gson = new GsonBuilder().create();

		return gson.toJson(map) + ".json";
	}

}
