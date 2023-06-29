package com.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.calendar.dao.CalendarMapper;
import com.yedam.calendar.vo.CalendarVO;

public class TestMain {

	public static void main(String[] args) {
		SqlSession session = DataSource.getInstance().openSession();
		CalendarMapper mapper = session.getMapper(CalendarMapper.class);

		// title: "해외여행가기", 2023-06-30, 2023-07-05
		CalendarVO vo = new CalendarVO();
		vo.setTitle("해외여행가기");

//		vo.setStartDate("2023-06-30");
//		vo.setEndDate("2023-07-05");

//		mapper.insert(vo);
		mapper.delete(vo);

		List<CalendarVO> list = mapper.getList();
		for(CalendarVO cal : list) {
			System.out.println(cal);
		}

	}

}
