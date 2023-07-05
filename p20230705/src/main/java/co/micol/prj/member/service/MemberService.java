package co.micol.prj.member.service;

import java.util.List;

public interface MemberService {
	List<MemberVO> MemberSelectList();	// 목록
	List<MemberVO> MemberSelectList(String key, String val);
	MemberVO memberSelect(MemberVO vo);	// 상세보기
	int memberInsert(MemberVO vo);		// 추가
	int memberUpdate(MemberVO vo);		// 수정
	int memberDelete(MemberVO vo);		// 삭제

	boolean isMemberIdCheck(String id);
}
