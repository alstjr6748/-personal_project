package com.yedam.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.board.vo.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> selectList(@Param("brdNo") long brdNo, @Param("page") int page);	//댓글목록
	public int selectCount(long brdNo);
	public ReplyVO selectOne(long replyNo);			//한건조회
	public int insertReply(ReplyVO vo);				//댓글등록
	public int updateReply(ReplyVO vo);				//댓글수정
	public int deleteReply(long replyNo);			//댓글삭제
}

