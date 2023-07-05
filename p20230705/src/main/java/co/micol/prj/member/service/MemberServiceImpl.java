package co.micol.prj.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.micol.prj.common.DataSource;
import co.micol.prj.member.mapper.MemberMapper;

public class MemberServiceImpl implements MemberService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

	@Override
	public List<MemberVO> MemberSelectList() {
		return mapper.MemberSelectList();
	}

	@Override
	public List<MemberVO> MemberSelectList(String key, String val) {
		return mapper.MemberSelectList(key, val);
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		return mapper.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		return mapper.memberInsert(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		return mapper.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		return mapper.memberDelete(vo);
	}

	@Override
	public boolean isMemberIdCheck(String id) {
		return mapper.isMemberIdCheck(id);
	}

}
