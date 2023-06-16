package com.yedam;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEx {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		String pass = "proj";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, pass);

			// db 쿼리실행 <-> 결과.
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select user_id, user_name, user_birth from tb1_users");
			while (rs.next()) {
				System.out.println(rs.getString("user_id") + "," + rs.getString("user_name") + "," + rs.getDate("user_birth"));
			}
			System.out.println("end of data.");
			conn.close();
			rs.close();
			stmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}
