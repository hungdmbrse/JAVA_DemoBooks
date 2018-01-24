package com.demobookstore.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demobookstore.beans.Book;
import com.demobookstore.beans.Impression;

public class DBUtils {

	public static Book findBook(Connection conn, int id) throws SQLException {

		String sql = "Select a.name, a.publisher, a.page from `books` a " //
				+ " where a.id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			Book book = new Book();
			book.setId(id);
			book.setName(rs.getString("name"));
			book.setPublisher(rs.getString("publisher"));
			book.setPage(rs.getInt("page"));
			return book;
		}
		return null;
	}

	public static Impression findImpression(Connection conn, int id) throws SQLException {

		String sql = "Select a.id, a.name, a.book_id from `impression` a "//
				+ " where a.id = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			Impression impr = new Impression();
			impr.setId(id);
			impr.setBookid(rs.getInt("book_id"));
			impr.setName(rs.getString("name"));
			return impr;
		}
		return null;
	}

	public static List<Book> queryBook(Connection conn) throws SQLException {
		String sql = "Select a.id, a.name, a.publisher, a.page from `books` a ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<Book> list = new ArrayList<Book>();
		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setName(rs.getString("name"));
			book.setPublisher(rs.getString("publisher"));
			book.setPage(rs.getInt("page"));
			list.add(book);
		}
		return list;
	}

	public static void updateBook(Connection conn, Book book) throws SQLException {
		String sql = "Update `books` set name =?, publisher=?, page=? where id=? ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, book.getName());
		pstm.setString(2, book.getPublisher());
		pstm.setInt(3, book.getPage());
		pstm.setInt(4, book.getId());
		pstm.executeUpdate();
	}

	public static void insertBook(Connection conn, Book book) throws SQLException {

		String sql1 = "select max(a.id) from `books` a";

		PreparedStatement pstm1 = conn.prepareStatement(sql1);
		ResultSet rs = pstm1.executeQuery();
		rs.next();
		int maxID = rs.getInt(1) + 1;
		pstm1.close();

		String sql = "Insert into `books`(id, name, publisher, page) values (?,?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, maxID);
		pstm.setString(2, book.getName());
		pstm.setString(3, book.getPublisher());
		pstm.setInt(4, book.getPage());
		pstm.executeUpdate();
	}

	public static void deleteBook(Connection conn, int id) throws SQLException {
		
		deleteImpressionByBookID(conn, id);
		
		String sql = "Delete From `books` where id= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}

	public static List<Impression> queryImpressionByBookID(Connection conn, int book_id) throws SQLException {
		String sql = "Select a.id, a.name, a.book_id from `impression` a where a.book_id = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, book_id);
		ResultSet rs = pstm.executeQuery();
		List<Impression> list = new ArrayList<Impression>();
		while (rs.next()) {
			Impression impr = new Impression();
			impr.setId(rs.getInt("id"));
			impr.setName(rs.getString("name"));
			impr.setBookid(book_id);
			list.add(impr);
		}
		return list;
	}

	public static void insertImpression(Connection conn, Impression impr) throws SQLException {

		String sql1 = "select max(a.id) from `impression` a";

		PreparedStatement pstm1 = conn.prepareStatement(sql1);
		ResultSet rs = pstm1.executeQuery();
		rs.next();
		int maxID = rs.getInt(1) + 1;
		pstm1.close();

		String sql = "Insert into `impression` (id, name, book_id) values (?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, maxID);
		pstm.setString(2, impr.getName());
		pstm.setInt(3, impr.getBookid());
		pstm.executeUpdate();
	}

	public static void updateImpression(Connection conn, Impression impr) throws SQLException {
		String sql = "Update `impression` set name =? where id=? ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, impr.getName());
		pstm.setInt(2, impr.getId());
		pstm.executeUpdate();
	}

	public static void deleteImpression(Connection conn, int id) throws SQLException {
		String sql = "Delete From `impression` where id= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}
	
	public static void deleteImpressionByBookID(Connection conn, int bookid) throws SQLException {
		String sql = "Delete From `impression` where book_id= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, bookid);

		pstm.executeUpdate();
	}

}
