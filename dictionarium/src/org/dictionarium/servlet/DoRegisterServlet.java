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

import org.dictionarium.bean.User;
import org.dictionarium.util.ConnectionAgent;
import org.dictionarium.util.DictionaryAgent;
import org.dictionarium.util.UserAgent;

@WebServlet(urlPatterns = {"/doRegister"})

public class DoRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DoRegisterServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		String email = request.getParameter("email");

		boolean hasError = false;
		String errorString = null;
		if (hasEmptyLine(userName, password, email)) {
			hasError = true;
			errorString = "Required user name, password and email";
		} else if (!isUsernameCorrect(userName)) {
			hasError = true;
			errorString = "Invalid user name (template: user_name123)";
		} else if (!isPasswordsMatches(password, repeatPassword)) {
			hasError = true;
			errorString = "Passwords are mismatched during sign up";
		} else if (!isEmailCorrect(email)) {
			hasError = true;
			errorString = "Wrong email line during sign up";
		}
		
		if (!hasError) {
			String dictionaryName = userName + "_dict";
			User user = new User(userName, password, email, dictionaryName);
			Connection connection = ConnectionAgent.getStoredConnection(request);

			try {
				UserAgent.insertUser(connection, user);
				DictionaryAgent.createDictionary(connection,
						user.getDictionaryName());
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
				hasError = true;
			}
		}
		
		if (hasError) {
			request.setAttribute("registerContext", "yes");
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/login.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/welcome");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	private static boolean hasEmptyLine(String userName, String password,
			String email) {
		return userName == null || password == null || email == null
				|| userName.isEmpty() || password.isEmpty() || email.isEmpty();
	}
	
	private static boolean isUsernameCorrect(String userName) {
		String pattern = "^[A-Za-z_][A-Za-z0-9_]{3,28}$";
		return userName.matches(pattern);
	}
	
	private static boolean isPasswordsMatches(String password,
			String repeatPassword) {
		return password.equals(repeatPassword);
	}
	
	private static boolean isEmailCorrect(String email) {
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
				+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return email.matches(pattern);
	}
}
