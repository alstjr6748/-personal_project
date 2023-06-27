package com.yedam.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.member.dao.MemberMapper;

public class TestMain {

	public static void main(String[] args) {
		SqlSession session = DataSource.getInstance().openSession();
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		List<Map<String, Object>> list = mapper.chartData();
		for(Map<String, Object> map : list) {
			System.out.println("부서 : " + map.get("DEPARTMENT_NAME") + " , 인원 : " + map.get("CNT"));
		}
	}

}
