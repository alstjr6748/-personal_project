package com.yedam.book;

import java.util.List;
import java.util.Scanner;

public class BookMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int selectNo = 0;
		BookDao dao = new BookDao();
		BookLogin login = new BookLogin();

		login.loginCheck();

		if (login.getLoginId().equals("admin")) {
			while (true) {
				System.out.println("==============도서관리시스템==============");
				System.out.print("1.등록 2.조회 3.수정 4.삭제 5.목록 6.종료 =>");
				selectNo = sc.nextInt();
				if (selectNo == 1) {
					System.out.println("책번호 입력(3자리만 허용) *필수");
					System.out.print("ex) 100 200 300 ==>");
					int num = sc.nextInt();
					sc.nextLine();
					if (num >= 1000 || num <= 99) {
						System.out.println("입력한 숫자는 3자리가 아닙니다.");
						System.out.println("다시 입력하세요.");
						continue;
					}

					System.out.print("책이름 입력 *필수 =>");
					String title = sc.nextLine();
					System.out.print("책저자 입력 *필수 =>");
					String author = sc.nextLine();
					System.out.print("책소개 입력 =>");
					String content = sc.nextLine();
					System.out.print("출간일 입력 =>");
					String date = sc.nextLine();

					BookVO book = new BookVO();

					book.setBookNum(num);
					book.setBookTitle(title);
					book.setBookAuthor(author);
					book.setBookContent(content);
					book.setBookDate(date);

					if (dao.insertDate(book)) {
						System.out.println("등록완료");
					} else {
						System.out.println("등록실패");
					}
				} else if (selectNo == 2) {
					System.out.print("조회할 책 번호입력 =>");
					int num = sc.nextInt();
					if (dao.search(num)) {

					} else {
						System.out.println("조회된 책이 없습니다.");
					}
				} else if (selectNo == 3) {
					BookVO book = new BookVO();

					System.out.print("수정할 책번호 =>");
					int num = sc.nextInt();
					sc.nextLine();
					
					System.out.print("책이름 수정 =>");
					String title = sc.nextLine();
					System.out.print("책저자 수정 =>");
					String author = sc.nextLine();
					System.out.print("책소개 수정 =>");
					String content = sc.nextLine();
					System.out.print("출간일 수정(ex:20010505) =>");
					String date = sc.nextLine();

					book.setBookNum(num);
					book.setBookTitle(title);
					book.setBookAuthor(author);
					book.setBookContent(content);
					book.setBookDate(date);

					if (dao.modify(book)) {
						System.out.println("수정완료");
					} else {
						System.out.println("수정실패");
					}

				} else if (selectNo == 4) {
					System.out.print("삭제할 책번호 =>");
					int num = sc.nextInt();
					sc.nextLine();

					BookVO book = new BookVO();
					book.setBookNum(num);

					if (dao.delete(num)) {
						System.out.println("삭제완료");
					} else {
						System.out.println("삭제실패");
					}
				} else if (selectNo == 5) {
					List<BookVO> list = dao.list();
					if (list.size() == 0) {
						System.out.println("목록이 없습니다.");
					} else {
						for (BookVO book : list) {
							System.out.printf("책번호 : %d | 책제목 : %s | 재고 : %d\n", book.getBookNum(), book.getBookTitle(),
									book.getBookCnt());
						}
					}
				} else if (selectNo == 6) {
					System.out.println("프로그램이 종료됩니다.");
					break;
				}
			}
		} else {
			while (true) {
				System.out.println("==============도서관리시스템==============");
				System.out.print("1.조회 2.목록 3.대출 4.반납 5.종료 =>");
				selectNo = sc.nextInt();
				if (selectNo == 1) {
					System.out.print("조회할 책 번호입력 =>");
					int num = sc.nextInt();

					if (!dao.search(num)) {
						System.out.println("조회된 책이 없습니다.");
					}
				} else if (selectNo == 2) {
					List<BookVO> list = dao.list();
					if (list.size() == 0) {
						System.out.println("목록이 없습니다.");
					} else {
						for (BookVO book : list) {
							System.out.printf("책번호 : %d | 책제목 : %s | 재고 : %d\n", book.getBookNum(), book.getBookTitle(),
									book.getBookCnt());
						}
					}
				} else if (selectNo == 3) {
					System.out.print("대출할 책번호 =>");
					int num = sc.nextInt();
					sc.nextLine();

					String userid = login.getLoginId();

					BookVO book = new BookVO();
					book.setBookNum(num);

					if (dao.loan(book, userid)) {
						System.out.println("대출완료");
					} else {
						System.out.println("대출실패");
					}
				} else if (selectNo == 4) {
					System.out.print("반납할 책번호 =>");
					int num = sc.nextInt();
					sc.nextLine();

					BookVO book = new BookVO();
					book.setBookNum(num);

					String userid = login.getLoginId();

					if (dao.returnbook(book, userid)) {
						System.out.println("반납완료");
					} else {
						System.out.println("반납실패");
					}
				} else if (selectNo == 5) {
					System.out.println("프로그램이 종료됩니다.");
					break;
				}
			}
		}
	}
}
