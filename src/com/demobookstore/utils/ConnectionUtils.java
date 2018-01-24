package com.demobookstore.utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// Sử dụng Oracle.
		// Bạn có thể thay thế bởi Database nào đó.

		// MySQL
		Connection conn = MySQLConnUtils.getMySQLConnection("35.202.231.58", "demo_books", "root", "YeXw.Z2u");

		return conn;
	}

	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean hasConnection(Connection conn) {

		try {
			return !conn.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean doQuery(String sql) throws Exception {
		Connection conn = null;
		try {
			// Tạo đối tượng Connection kết nối database.
			conn = ConnectionUtils.getConnection();
			// Sét tự động commit = false, để chủ động đi�?u khiển.
			conn.setAutoCommit(false);

			Statement statement = conn.createStatement();

			// String sql = "Insert into Salary_Grade (Grade, High_Salary, Low_Salary) "
			// + "values (2, 20000, 10000) ";

			// Thực thi câu lệnh.
			// executeUpdate(String) sử dụng cho các loại lệnh Insert,Update,Delete.
			int rowCount = statement.executeUpdate(sql);

			// In ra số dòng được trèn vào bởi câu lệnh trên.
			System.out.println("Row Count affected = " + rowCount);

			// G�?i commit() để hoàn thành giao dịch (transaction) với DB.
			conn.commit();

		} catch (Exception e) {
			ConnectionUtils.rollbackQuietly(conn);
			throw new ServletException();
		} finally {
			ConnectionUtils.closeQuietly(conn);
		}
		return true;
	}

	//
	// Test Connection ...
	//
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		System.out.println("Get connection ... ");

		// Lấy ra đối tượng Connection kết nối vào database.
		Connection conn = ConnectionUtils.getConnection();

		System.out.println("Get connection " + conn);

		System.out.println("Done!");
	}

}