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

@WebServlet("/UserCtl")
public class UserCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		String id = request.getParameter("id");

		if (id != null) {
			try {
				bean = model.findByPk(Integer.parseInt(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("UserView.jsp");
		rd.forward(request, response);

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
			bean.setPassword(request.getParameter("passwrod"));
			bean.setDob(sdf.parse(request.getParameter("dob")));

			try {
				model.add(bean);
				request.setAttribute("successMsg", "user saved successfull");
			} catch (RuntimeException e) {
				request.setAttribute("errorMsg", e.getMessage());
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("UserView.jsp");
		rd.forward(request, response);

	}

}
