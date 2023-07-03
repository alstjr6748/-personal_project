package com.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.board.dao.ReplyMapper;
import com.yedam.board.vo.ReplyVO;

public class TestMain {

	public static void main(String[] args) {
		SqlSession session = DataSource.getInstance().openSession(true);
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);

		ReplyVO reply = new ReplyVO();
		reply.setBrdNo(790);
//		reply.setReply("댓글댓글대슥ㄹffff수정");
//		reply.setReplyer("user0000");
//		reply.setReplyNo(6);

		//mapper.insertReply(reply);
		//mapper.updateReply(reply);
		mapper.deleteReply(6);
		//List<ReplyVO> list = mapper.selectList(790);

		/*
		 * for(ReplyVO vo : list) { System.out.println(vo.toString()); }
		 */
	}

}
