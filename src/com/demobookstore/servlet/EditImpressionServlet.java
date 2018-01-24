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
import com.demobookstore.beans.Impression;
import com.demobookstore.utils.DBUtils;
import com.demobookstore.utils.MyUtils;

@WebServlet(urlPatterns = { "/impression/edit" })
public class EditImpressionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditImpressionServlet() {
		super();
	}

	// Hiển thị trang sửa sản phẩm.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Impression impr = null;

		String errorString = null;

		try {
			impr = DBUtils.findImpression(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Không có lỗi.
		// Sản phẩm không tồn tại để edit.
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && impr == null) {
			response.sendRedirect(request.getServletPath() +  "/impression?bookid="+impr.getBookid());
			return;
		}

		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("impression", impr);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editImpressionView.jsp");
		dispatcher.forward(request, response);

	}

	// Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
	// Phương thức này sẽ được thực thi.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");
		String bookid = (String) request.getParameter("bookid");
		String name = (String) request.getParameter("name");
		int id = 0;
		
		String errorString = null;

		try {
			id = Integer.parseInt(idStr);
			Impression impr = DBUtils.findImpression(conn, id);
			impr.setName(name);
			DBUtils.updateImpression(conn, impr);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);

		// Nếu có lỗi forward sang trang edit.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editBookView.jsp");
			dispatcher.forward(request, response);
		}
		// Nếu mọi thứ tốt đẹp.
		// Redirect sang trang danh sách sản phẩm.
		else {
			response.sendRedirect(request.getContextPath() +  "/impression?bookid="+bookid);
		}
	}

}