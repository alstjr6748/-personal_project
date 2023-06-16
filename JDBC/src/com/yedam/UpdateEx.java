package com.yedam;

import java.sql.*;
import java.util.*;

public class UpdateEx {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("id>");
		String id = sc.nextLine();
		System.out.println("pw>");
		String pw = sc.nextLine();
		System.out.println("addr>");
		String addr = sc.nextLine();

		Connection conn = null;
		PreparedStatement psmt = null;
//		String sql = "update tb1_users set user_pw = '3333', user_addr = 'Seoul 100' where user_id = 'user3'";
		String sql = "update tb1_users set user_pw = ?, user_addr = ? where user_id = ?";
		
		try {
			conn = Dao.getConnect();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, addr);
			psmt.setString(3, id);
			
			int r = psmt.executeUpdate();
			if(r > 0) {
				System.out.println("처리성공");
			} else {
				System.out.println("처리실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
