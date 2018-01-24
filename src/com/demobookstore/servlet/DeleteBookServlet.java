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

import com.demobookstore.utils.DBUtils;
import com.demobookstore.utils.MyUtils;

@WebServlet(urlPatterns = { "/deleteBook" })
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteBookServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
		}
		String errorString = null;

		try {
			DBUtils.deleteBook(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Nếu có lỗi, forward (chuyển tiếp) sang trang thông báo lỗi.
		if (errorString != null) {
			// Lưu thông tin vào request attribute trước khi forward sang views.
			request.setAttribute("errorString", errorString);
			//
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/deleteBookErrorView.jsp");
			dispatcher.forward(request, response);
		}
		// Nếu mọi thứ tốt đẹp.
		// Redirect (chuyển hướng) sang trang danh sách sản phẩm.
		else {
			response.sendRedirect(request.getContextPath() + "/book");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}