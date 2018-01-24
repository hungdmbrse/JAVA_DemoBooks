package com.demobookstore.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demobookstore.beans.Book;
import com.demobookstore.utils.DBUtils;
import com.demobookstore.utils.MyUtils;

@WebServlet(urlPatterns = { "/book/add" })
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateBookServlet() {
		super();
	}

	// Hiển thị trang tạo sản phẩm.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
		dispatcher.forward(request, response);
	}

	// Khi người dùng nhập các thông tin sản phẩm, và nhấn Submit.
	// Phương thức này sẽ được gọi.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String pageStr = (String) request.getParameter("page");
		String name = (String) request.getParameter("name");
		String publisher = (String) request.getParameter("publisher");
		int page = 0;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		Book book = new Book(name,publisher,page);

		String errorString = null;

		
		if (errorString == null) {
			try {
				DBUtils.insertBook(conn, book);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("book", book);

		// Nếu có lỗi forward (chuyển tiếp) sang trang 'edit'.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/createBookView.jsp");
			dispatcher.forward(request, response);
		}
		// Nếu mọi thứ tốt đẹp.
		// Redirect (chuyển hướng) sang trang danh sách sản phẩm.
		else {
			response.sendRedirect(request.getContextPath() + "/book");
		}
	}

}