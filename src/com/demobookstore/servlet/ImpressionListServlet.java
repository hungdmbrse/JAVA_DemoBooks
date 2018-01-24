package com.demobookstore.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demobookstore.beans.Book;
import com.demobookstore.beans.Impression;
import com.demobookstore.utils.DBUtils;
import com.demobookstore.utils.MyUtils;

@WebServlet(urlPatterns = { "/impression" })
public class ImpressionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImpressionListServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		String bookidStr = request.getParameter("bookid");
		String errorString = null;
		String bookname = "";
		List<Impression> list = null;

		try {
			int book_id = Integer.parseInt(bookidStr);
			list = DBUtils.queryImpressionByBookID(conn, book_id);
			bookname = DBUtils.findBook(conn, book_id).getName();
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("impressionList", list);
		request.setAttribute("bookid", bookidStr);
		request.setAttribute("bookname", bookname);
		// Forward sang /WEB-INF/views/productListView.jsp
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/impression.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}