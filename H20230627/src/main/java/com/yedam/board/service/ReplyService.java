package com.yedam.board.service;

import java.util.List;

import com.yedam.board.vo.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> replyList(long brdNo, int page);
	public int replyCount(long brdNo);
	public ReplyVO getReply(long replyNo);
	public boolean addReply(ReplyVO vo);
	public boolean modifyReply(ReplyVO vo);
	public boolean removeReply(long replyNo);
}
