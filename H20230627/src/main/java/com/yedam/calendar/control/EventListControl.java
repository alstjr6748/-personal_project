package com.yedam.calendar.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.calendar.service.CalendarService;
import com.yedam.calendar.service.CalendarServiceImpl;
import com.yedam.calendar.vo.CalendarVO;
import com.yedam.common.Control;

public class EventListControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		CalendarService service = new CalendarServiceImpl();
		List<CalendarVO> list = service.events();
//		req.setAttribute(null, list);
		//
		Gson gson = new GsonBuilder().create(); //json 객체를 만들어준다. (gson 은 라이브러리)
		String json = gson.toJson(list);

		return json + ".json";
	}

}
