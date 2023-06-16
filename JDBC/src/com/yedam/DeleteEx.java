package com.yedam;

import java.sql.*;

public class DeleteEx {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "delete from tb1_users where user_id = 'user5'";
		
		try {
			conn = Dao.getConnect();
			
			psmt = conn.prepareStatement(sql);
			
			int r = psmt.executeUpdate();
			if(r > 0) {
				System.out.println("처리성공");
			} else {
				System.out.println("처리실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
