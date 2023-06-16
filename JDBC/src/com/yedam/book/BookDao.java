package com.yedam.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;

public class BookDao {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	int check = 0;

	public boolean insertDate(BookVO book) {
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

	public boolean search(int bookNum) {
		sql = "select * from tbl_books where book_num = ?";
		String sql1 = "select user_id from tbl_users where book_num = ?";
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

				sb.append(tmp + " ");
			}
			id = sb.toString();

			System.out.printf("책번호 : %d | 책제목 : %s | 책저자 : %s | 책소개 : %s | 출간일 : %s | 대여중인 사람 : %s | 재고 : %d\n",
					book.getBookNum(), book.getBookTitle(), book.getBookAuthor(), book.getBookContent(),
					book.getBookDate(), id, book.getBookCnt());
			// id = "user2 user3 ";
			return true;
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

	public boolean modify(BookVO book) {
		sql = "update tbl_books set book_title = nvl(?, book_title),"
				+ "                 book_author = nvl(?, book_author),"
				+ "                 book_content = nvl(?, book_content),"
				+ "                 book_date  = nvl(?, book_date)" + "where book_num = ?";
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

	public boolean delete(int bookNum) {
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

	public List<BookVO> list() {
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
		sql = "update tbl_books set book_cnt = book_cnt-1 \r\n"
				+ "where book_num = nvl2(( select book_num  \r\n"
				+ "                  from  tbl_users\r\n"
				+ "                  where user_id = ?),null,?)";
		String sql2 = "select book_cnt from tbl_books where book_num = ?";
		String uSql = "update tbl_users set book_num = ? where user_id = ?";
		conn = Dao.getConnect();

		PreparedStatement psmt2;
		ResultSet rs2;

		PreparedStatement psmt3;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);
			psmt.setInt(2, book.getBookNum());

			psmt2 = conn.prepareStatement(sql2);
			psmt2.setInt(1, book.getBookNum());
			rs2 = psmt2.executeQuery();

			psmt3 = conn.prepareStatement(uSql);
			psmt3.setInt(1, book.getBookNum());
			psmt3.setString(2, user_id);

			while (rs2.next()) {
				int a = rs2.getInt(1);

				if (a > 0) {						//cnt 가 0보다 클때 밑으로 내려간다
					int r = psmt.executeUpdate();
						if (r > 0) {				
							psmt3.executeUpdate();
							return true;
						}
					}
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean returnbook(BookVO book, String user_id) {
		sql = "update tbl_books set book_cnt = book_cnt+1 where book_num  = ( select book_num from  tbl_users where user_id = ?)";
		String sql2 = "select book_cnt from tbl_books where book_num = ?";
		String uSql = "update tbl_users set book_num = null where user_id = ?";
		conn = Dao.getConnect();

		PreparedStatement psmt2;
		ResultSet rs2;

		PreparedStatement psmt3;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);

			psmt2 = conn.prepareStatement(sql2);
			psmt2.setInt(1, book.getBookNum());
			rs2 = psmt2.executeQuery();

			psmt3 = conn.prepareStatement(uSql);
			psmt3.setString(1, user_id);
			while (rs2.next()) {
				int a = rs2.getInt(1);
				if (a < 3) {
					int r = psmt.executeUpdate();
					if (r > 0) {
						int k = psmt3.executeUpdate();
						return true;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

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
