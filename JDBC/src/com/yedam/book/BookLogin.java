package com.yedam.book;

import java.util.Scanner;

import com.yedam.user.UserDao;

public class BookLogin {
	private UserDao uDao = new UserDao();
	private BookVO book;
	private Scanner sc = new Scanner(System.in);
	private String loginId;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public void loginCheck() {
		while (true) {
			String id = promptString("아이디를 입력하세요");
			String pw = promptString("비밀번호를 입력하세요");

			if (uDao.login(id, pw)) {
				loginId = id;
				return;
			}
			System.out.println("입력정보를 확인하세요.");
		}
	}
	private String promptString(String msg) {
		System.out.print(msg + "=> ");
		return sc.nextLine();
	}
}
