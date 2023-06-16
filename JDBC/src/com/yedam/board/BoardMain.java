package com.yedam.board;

import java.util.List;
import java.util.Scanner;

import com.yedam.user.UserVO;

//등록수정삭제 구현.. 
public class BoardMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int selectNo = 0;
		BoardDao dao = new BoardDao();

		while (true) {
			System.out.print("1.글등록 2.삭제 3.글내용수정 4.글목록보기 5.상세보기 6.종료");
			selectNo = sc.nextInt();
			sc.nextLine();
			if (selectNo == 1) {
				System.out.print("title > ");
				String title = sc.nextLine();
				System.out.print("writer > ");
				String writer = sc.nextLine();
				System.out.print("content > ");
				String content = sc.nextLine();

				BoardVO board = new BoardVO();

				board.setBoardTitle(title);
				board.setBoardWriter(writer);
				board.setBoardContent(content);

				if (dao.insert(board)) {
					System.out.println("처리성공");
				} else {
					System.out.println("처리실패");
				}
			} else if (selectNo == 2) {
				System.out.print("삭제할 boardNo > ");
				int no = sc.nextInt();

				if (dao.delete(no)) {
					System.out.println("삭제성공");
				} else {
					System.out.println("삭제실패");
				}
			} else if (selectNo == 3) {
				System.out.print("수정할 boardNo > ");
				int no = sc.nextInt();
				sc.nextLine();
				System.out.print("title > ");
				String title = sc.nextLine();
				System.out.print("writer > ");
				String writer = sc.nextLine();
				System.out.print("content > ");
				String content = sc.nextLine();
				
				BoardVO board = new BoardVO();

				board.setBoardTitle(title);
				board.setBoardWriter(writer);
				board.setBoardContent(content);

				if (dao.modify(board,no)) {
					System.out.println("처리성공");
				} else {
					System.out.println("처리실패");
				}
			} else if (selectNo == 4) {
				List<BoardVO> list = dao.list();
				if (list.size() == 0) {
					System.out.println("조회된 데이터가 없습니다.");
				} else {
					for (BoardVO board : list) {
						System.out.println(board.toString());
					}
				}
			} else if (selectNo == 5) {
				
			} else if (selectNo == 6) {
				System.out.println("프로그램종료.");
				break;
			}
		}
	}

}
