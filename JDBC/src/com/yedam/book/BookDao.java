package com.yedam.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;
import com.yedam.user.UserVO;

import lombok.Data;
@Data
public class BookDao {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	private int page = 1;
	
	public boolean insertDate(BookVO book) {			//등록
		sql = "insert into tbl_books(book_num, book_title, book_author, book_content, book_date) values( ? , ? , ? , ? , nvl(?, sysdate))";
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, book.getBookNum());
			psmt.setString(2, book.getBookTitle());
			psmt.setString(3, book.getBookAuthor());
			psmt.setString(4, book.getBookContent());
			psmt.setString(5, book.getBookDate());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean search(int bookNum) {			//조회
		sql = "select * from tbl_books where book_num = ?";					//tbl_books 테이블의 데이터를 검색
		String sql1 = "select user_id from tbl_users where book_num = ?";	//입력한 book_num을 tbl_usres 테이블에서 찾아서 같은 book_num을 가지고있는 user_id를 검색
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bookNum);
			rs = psmt.executeQuery();

			BookVO book = new BookVO();
			if (rs.next()) {
				book.setBookNum(rs.getInt("book_num"));
				book.setBookTitle(rs.getString("book_title"));
				book.setBookAuthor(rs.getString("book_author"));
				book.setBookContent(rs.getString("book_content"));
				book.setBookDate(rs.getString("book_date"));
				book.setBookCnt(rs.getInt("book_cnt"));
			}

			PreparedStatement psmt2;
			ResultSet rs2;
			psmt2 = conn.prepareStatement(sql1);
			psmt2.setInt(1, bookNum);

			rs2 = psmt2.executeQuery();
			String id = "";
			StringBuilder sb = new StringBuilder();
			while (rs2.next()) {
				String tmp = rs2.getString(1);
				sb.append(tmp + " ");				//대여중인 사람이 여러 명이면 옆에 나오게 표현.
			}
			id = sb.toString();
			if (bookNum == book.getBookNum()) {
				System.out.printf("책번호 : %d | 책제목 : %s | 책저자 : %s | 책소개 : %s | 출간일 : %s | 대여중인 사람 : %s | 재고 : %d\n",
						book.getBookNum(), book.getBookTitle(), book.getBookAuthor(), book.getBookContent(),
						book.getBookDate(), id, book.getBookCnt());
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean modify(BookVO book) {				//수정
		sql = "update tbl_books set book_title = nvl(?, book_title),"			
				+ "                 book_author = nvl(?, book_author),"
				+ "                 book_content = nvl(?, book_content),"
				+ "                 book_date  = nvl(?, book_date) where book_num = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, book.getBookTitle());
			psmt.setString(2, book.getBookAuthor());
			psmt.setString(3, book.getBookContent());
			psmt.setString(4, book.getBookDate());
			psmt.setInt(5, book.getBookNum());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean delete(int bookNum) {		//삭제
		sql = "delete from tbl_books where book_num = ?";
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bookNum);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<BookVO> list() {			//목록
		List<BookVO> list = new ArrayList<>();
		sql = "select * from tbl_books";
		
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BookVO book = new BookVO();
				book.setBookNum(rs.getInt("book_num"));
				book.setBookTitle(rs.getString("book_title"));
				book.setBookCnt(rs.getInt("book_cnt"));

				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public boolean loan(BookVO book, String user_id) {
		sql = "update tbl_books set book_cnt = book_cnt-1 \r\n" + "where book_num = nvl2(( select book_num from  tbl_users where user_id = ?),null,?)";
				//도서대출을 하면 nvl2로 로그인한 사람의 num이 null이면 null로 아니면 입력한 num으로 값을 바꿔주고 재고에서 수량을 1개 줄여준다
		String sql2 = "select book_cnt from tbl_books where book_num = ?";		//입력한 num값을 tbl_books테이블에서 찾아서 cnt컬럼을 가져온다.
		String uSql = "update tbl_users set book_num = ? where user_id = ?";	//대출을 하면 tbl_users에 book_num값을 대입해준다. 
		String tSql = "update tbl_users set book_loan = sysdate where user_id = ?";
		conn = Dao.getConnect();

		PreparedStatement psmt2;
		ResultSet rs2;

		PreparedStatement psmt3;
		PreparedStatement psmt4;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);
			psmt.setInt(2, book.getBookNum());

			psmt2 = conn.prepareStatement(sql2);
			psmt2.setInt(1, book.getBookNum());
			rs2 = psmt2.executeQuery();				//ResultSet에 쿼리실행 값들을 넣는다.

			psmt3 = conn.prepareStatement(uSql);
			psmt3.setInt(1, book.getBookNum());
			psmt3.setString(2, user_id);
			
			psmt4 = conn.prepareStatement(tSql);
			psmt4.setString(1, user_id);

			while (rs2.next()) {
				int a = rs2.getInt(1);

				if (a > 0) { // cnt 가 0보다 클때 밑으로 내려간다
					int r = psmt.executeUpdate();
					if (r > 0) {
						psmt3.executeUpdate();
						psmt4.executeUpdate();
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean returnbook(BookVO book, String user_id) {
		sql = "update tbl_books set book_cnt = book_cnt+1 where book_num  = ( select book_num from  tbl_users where user_id = ? and book_num = ?)";
				//반납을하면 로그인한 id에서 num을 서브쿼리로 가져와서 일치하는 num값을 재고에서 수량을 늘려준다
		String sql2 = "select book_cnt from tbl_books where book_num = ?";		//입력한 num값을 tbl_books테이블에서 찾아서 cnt컬럼을 가져온다.
		String uSql = "update tbl_users set book_num = null where user_id = ?";	//로그인한 유저의 num값을 null로 바꿔준다.
		String tSql = "update tbl_users set book_loan = null where user_id = ?";
		conn = Dao.getConnect();

		PreparedStatement psmt2;
		ResultSet rs2;

		PreparedStatement psmt3;
		PreparedStatement psmt4;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);
			psmt.setInt(2, book.getBookNum());

			psmt2 = conn.prepareStatement(sql2);
			psmt2.setInt(1, book.getBookNum());
			rs2 = psmt2.executeQuery();

			psmt3 = conn.prepareStatement(uSql);
			psmt3.setString(1, user_id);
			
			psmt4 = conn.prepareStatement(tSql);
			psmt4.setString(1, user_id);
			while (rs2.next()) {
				int a = rs2.getInt(1);
				if (a < 3) {
					int r = psmt.executeUpdate();
					if (r > 0) {
						psmt3.executeUpdate();
						psmt4.executeUpdate();
						return true;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	public List<UserVO> userList(){
		List<UserVO> list = new ArrayList<>();
		
		sql = "select * from tbl_users where user_id <> 'admin' ";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {	//조회된 건수만큼 반복
				UserVO user = new UserVO();
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setBook_num(rs.getInt("book_num"));
				user.setLoan(rs.getString("book_loan"));
				
				list.add(user);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean login(String id, String pw) {
		sql = "select * from tbl_users where user_id = ? and user_pw = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			rs = psmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}



}
