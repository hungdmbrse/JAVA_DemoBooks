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

@WebServlet(urlPatterns = { "/book/edit" })
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditBookServlet() {
		super();
	}

	// Hiển thị trang sửa sản phẩm.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Book book = null;

		String errorString = null;

		try {
			book = DBUtils.findBook(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Không có lỗi.
		// Sản phẩm không tồn tại để edit.
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && book == null) {
			response.sendRedirect(request.getServletPath() + "/book");
			return;
		}

		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("book", book);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editBookView.jsp");
		dispatcher.forward(request, response);

	}

	// Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
	// Phương thức này sẽ được thực thi.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");
		
		String pageStr = (String) request.getParameter("page");
		String name = (String) request.getParameter("name");
		String publisher = (String) request.getParameter("publisher");
		int id = 0;
		int page = 0;
		try {
			id = Integer.parseInt(idStr);
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		Book product = new Book(id, name, publisher, page);

		String errorString = null;

		try {
			DBUtils.updateBook(conn, product);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("product", product);

		// Nếu có lỗi forward sang trang edit.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editBookView.jsp");
			dispatcher.forward(request, response);
		}
		// Nếu mọi thứ tốt đẹp.
		// Redirect sang trang danh sách sản phẩm.
		else {
			response.sendRedirect(request.getContextPath() + "/book");
		}
	}

}