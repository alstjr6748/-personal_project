package co.micol.prj.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.prj.member.service.MemberVO;

public interface MemberMapper {
	List<MemberVO> MemberSelectList();	// 목록
	// 파라미터가 2개이상이면 Mapper에서는 @Param을 설정해줘야한다.
	List<MemberVO> MemberSelectList(@Param("key") String key, @Param("val") String val);
	MemberVO memberSelect(MemberVO vo);	// 상세보기
	int memberInsert(MemberVO vo);		// 추가
	int memberUpdate(MemberVO vo);		// 수정
	int memberDelete(MemberVO vo);		// 삭제

	boolean isMemberIdCheck(String id);
}
