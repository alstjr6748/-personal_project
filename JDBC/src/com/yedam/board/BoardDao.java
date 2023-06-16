package com.yedam.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;
import com.yedam.user.UserVO;

public class BoardDao {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	public boolean insert(BoardVO board) {
		sql = "insert into tbl_board (brd_no,brd_title,brd_content,brd_writer)" + "values(board_seq.nextval, ?, ?, ?)";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, board.getBoardId());
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContent());
			psmt.setString(3, board.getBoardWriter());

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

	public boolean delete(int boardId) {
		sql = "delete from tbl_board where brd_no = ?";
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardId);

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
	public boolean modify(BoardVO board,int boardId) {
		sql = "update tbl_board set brd_title = nvl(?, brd_title),"
				+ "                 brd_writer = nvl(?, brd_writer),"
				+ "                 brd_content = nvl(?, brd_content),"
				+ "                 create_date = nvl(?, create_date)"
				+ "where brd_no = ?";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardWriter());
			psmt.setString(3, board.getBoardContent());
			psmt.setString(4, board.getDate());
			psmt.setString(5, String.valueOf(boardId));
			
			int r = psmt.executeUpdate();
			if(r > 0) {
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
	public List<BoardVO> list(){
		List<BoardVO> list = new ArrayList<>();
		
		sql = "select * from tbl_board";
		conn = Dao.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {	//조회된 건수만큼 반복
				BoardVO board = new BoardVO();
				board.setBoardId(rs.getInt("brd_no"));
				board.setBoardTitle(rs.getString("brd_title"));
				board.setBoardWriter(rs.getString("brd_writer"));
				board.setBoardContent(rs.getString("brd_content"));
				board.setDate(rs.getString("create_date"));
				board.setCnt(rs.getInt("click_cnt"));
				
				list.add(board);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
