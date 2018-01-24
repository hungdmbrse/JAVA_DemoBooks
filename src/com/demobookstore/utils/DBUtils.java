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

	public static Book findBook(Connection conn,  int id) throws SQLException {

		String sql = "Select a.name, a.publisher, a.page from `書籍` a " //
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

		String sql = "Select a.id, a.name, a.book_id from `書籍感想` a "//
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
		String sql = "Select a.id, a.name, a.publisher, a.page from `書籍` a ";

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
		String sql = "Update `書籍` set name =?, publisher=?, page=? where id=? ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, book.getName());
		pstm.setString(2, book.getPublisher());
		pstm.setInt(3, book.getPage());
		pstm.setInt(4, book.getId());
		pstm.executeUpdate();
	}

	
	public static void insertBook(Connection conn, Book book) throws SQLException {
		
		String sql1 = "select max(a.id) from `書籍` a";

		PreparedStatement pstm1 = conn.prepareStatement(sql1);
		ResultSet rs = pstm1.executeQuery();
		int maxID = rs.getInt(1)+1;
		pstm1.close();
		
		String sql = "Insert into `書籍`(id, name, publisher, page) values (?,?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, maxID);
		pstm.setString(2, book.getName());
		pstm.setString(3, book.getPublisher());
		pstm.setInt(4, book.getPage());
		pstm.executeUpdate();
	}

	public static void deleteBook(Connection conn, int id) throws SQLException {
		String sql = "Delete From `書籍` where id= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}

}
