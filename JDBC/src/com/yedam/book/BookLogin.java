package com.yedam.book;

import java.util.Scanner;

import com.yedam.user.UserDao;

import lombok.Data;

@Data
public class BookLogin {
	private BookDao Dao = new BookDao();
	private BookVO book;
	private Scanner sc = new Scanner(System.in);
	private String loginId;
	
	public void loginCheck() {
		while (true) {
			String id = promptString("아이디를 입력하세요");
			String pw = promptString("비밀번호를 입력하세요");

			if (Dao.login(id, pw)) {
				loginId = id;
				return;
			}
			System.out.println("아이디 비밀번호를 다시 확인하세요.");
		}
	}
	private String promptString(String msg) {
		System.out.print(msg + "=> ");
		return sc.nextLine();
	}
}
