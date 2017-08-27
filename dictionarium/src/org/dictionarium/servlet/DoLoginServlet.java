package org.dictionarium.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dictionarium.util.ConnectionAgent;
import org.dictionarium.util.UserAgent;
import org.dictionarium.util.SessionAgent;
import org.dictionarium.util.CookieAgent;
import org.dictionarium.bean.User;

@WebServlet(urlPatterns = {"/doLogin"})

public class DoLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DoLoginServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean needRemember = rememberMeStr != null;

		boolean hasError = false;
		String errorString = null;
		User user = null;
		if (hasEmptyLine(userName, password)) {
			hasError = true;
			errorString = "Required user name and password";
		} else {
			Connection connection = ConnectionAgent.getStoredConnection(request);
			try {
				user = UserAgent.findUser(connection, userName, password);
				if (user == null) {
					hasError = true;
					errorString = "User name or password is invalid";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
				hasError = true;
			}
		}
		
		if (hasError) {
			user = new User();
			user.setName(userName);
			user.setPassword(password);
			
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);
			
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/login.jsp");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			SessionAgent.storeLoginedUser(session, user);

			if (needRemember) {
				CookieAgent.storeUserName(response, user);
			} else {
				CookieAgent.deleteUserCookie(response);
			}

			response.sendRedirect(request.getContextPath() + "/dictionary");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	private static boolean hasEmptyLine(String userName, String password) {
		return userName == null || password == null 
				|| userName.isEmpty() || password.isEmpty();
	}
}
