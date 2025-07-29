package com.rays.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

@WebServlet("/UserRegistrationCtl")
public class UserRegistrationCtl extends HttpServlet {

	// service method run on every request and service method run first before any
	// get, post ,delete put methods
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in service method");

		String op = request.getParameter("operation");

		System.out.println("op = " + op);

		if (op != null) {

			if (!validate(request)) {
				RequestDispatcher rd = request.getRequestDispatcher("UserRegistrationView.jsp");
				rd.forward(request, response);
				return;
			}
		}
		super.service(request, response);
	}

	public boolean validate(HttpServletRequest request) {

		boolean isValid = true;
		String op = request.getParameter("operation");
		System.out.println("op: " + op);

		if (op != null) {
			if (request.getParameter("firstName") == "") {
				request.setAttribute("firstName", "first name is required");
				isValid = false;
			}
			if (request.getParameter("lastName") == "") {
				request.setAttribute("lastName", "last name is required");
				isValid = false;
			}
			if (request.getParameter("login") == "") {
				request.setAttribute("login", "login is required");
				isValid = false;
			}
			if (request.getParameter("password") == "") {
				request.setAttribute("password", "passwrod is required");
				isValid = false;
			}
			if (request.getParameter("dob") == "") {
				request.setAttribute("dob", "dob is required");
				isValid = false;
			}
		}
		return isValid;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("UserRegistrationView.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		try {
			bean.setFirstName(request.getParameter("firstName"));
			bean.setLastName(request.getParameter("lastName"));
			bean.setLogin(request.getParameter("login"));
			bean.setPassword(request.getParameter("password"));
			bean.setDob(sdf.parse(request.getParameter("dob")));

			try {
				model.add(bean);
				request.setAttribute("successMsg", "user registration successfull");
			} catch (RuntimeException e) {
				request.setAttribute("errorMsg", e.getMessage());
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("UserRegistrationView.jsp");
		rd.forward(request, response);

	}

}
