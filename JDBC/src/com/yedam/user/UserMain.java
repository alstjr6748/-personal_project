package com.yedam.user;

import java.util.List;
import java.util.Scanner;

public class UserMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		UserDao dao = new UserDao();
		
		
		while (true) {
			System.out.println("1.추가 2.조회 3.수정 4.삭제 5.목록 6.종료");
			System.out.println("선택 >");
			menu = sc.nextInt();
			sc.nextLine();
			if (menu == 1) {
				System.out.print("id > ");
				String id = sc.nextLine();
				System.out.print("pw > ");
				String pw = sc.nextLine();
				System.out.print("name > ");
				String name = sc.nextLine();

				UserVO user = new UserVO();

				user.setUserId(id);
				user.setUserPw(pw);
				user.setUserName(name);

				if (dao.add(user)) {
					System.out.println("처리성공");
				} else {
					System.out.println("처리실패");
				}

			} else if (menu == 2) {
				System.out.print("id > ");
				String id = sc.nextLine();

				UserVO user = dao.search(id);
				if (user == null) {
					System.out.println("조회된 정보가 없습니다.");
				} else {
					System.out.println(user.toString());
				}
			} else if (menu == 3) {
				System.out.print("id > ");
				String id = sc.nextLine();
				System.out.print("addr > ");
				String addr = sc.nextLine();
				System.out.print("phone > ");
				String phone = sc.nextLine();

				UserVO user = new UserVO();
				user.setUserId(id);
				user.setUserAddr(addr);
				user.setUserPhone(phone);
				if (dao.modify(user)) {
					System.out.println("정보 수정.");
				} else {
					System.out.println("수정할 대상이 없습니다.");
				}
			} else if (menu == 4) {
				System.out.print("id > ");
				String id = sc.nextLine();

				UserVO user = new UserVO();
				user.setUserId(id);
				if (dao.remove(id)) {
					System.out.println("삭제완료");
				} else {
					System.out.println("삭제할 대상이 없습니다.");
				}
			} else if (menu == 5) {
				List<UserVO> list = dao.list();
				if (list.size() == 0) {
					System.out.println("조회된 데이터가 없습니다.");
				} else {
					for (UserVO user : list) {
						System.out.println(user.toString());
					}
				}
			} else if (menu == 6) {
				break;
			}
		}
		System.out.println("end of prog.");
	}

}
