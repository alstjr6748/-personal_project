package com.yedam;
import java.sql.*;
import java.util.*;
public class InsertEx {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("id>");
		String id = sc.nextLine();
		System.out.println("pw>");
		String pw = sc.nextLine();
		System.out.println("name>");
		String name = sc.nextLine();
		
		Connection conn = null;
		Statement stmt = null;
		String sql = "insert into tb1_users (user_id,user_pw,user_name) values('" + id + "','" + pw + "','" + name +"')";
		try {
			conn = Dao.getConnect();
			stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);		//insert, update, delete 는 executeUpdate로 한다.
			if(r > 0) {
				System.out.println("처리성공");
			} else {
				System.out.println("처리실패");
			}
		} catch(Exception e) {
			System.out.println("처리중 에러발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
