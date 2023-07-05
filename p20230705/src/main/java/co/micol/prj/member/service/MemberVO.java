package co.micol.prj.member.service;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberTel;
	private String memberAddress;
	private String memberAuthor;
	private Date memberEdate;
	private Date memberDdate;
}
