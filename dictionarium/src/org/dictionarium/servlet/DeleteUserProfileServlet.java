package org.dictionarium.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dictionarium.bean.User;
import org.dictionarium.connection.ConnectionManager;
import org.dictionarium.util.ConnectionAgent;
import org.dictionarium.util.CookieAgent;
import org.dictionarium.util.DictionaryAgent;
import org.dictionarium.util.SessionAgent;
import org.dictionarium.util.UserAgent;

@WebServlet(urlPatterns = {"/deleteUserProfile"})

public class DeleteUserProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DeleteUserProfileServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = SessionAgent.getLoginedUser(session);
		
		Connection connection = ConnectionAgent.getStoredConnection(request);
		
		String dictionaryName = user.getDictionaryName();
		String errorString = null;
		try {
			connection.setAutoCommit(false);
			DictionaryAgent.deleteDictionary(connection, dictionaryName);
			UserAgent.deleteUser(connection, user);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
			
			ConnectionManager.rollbackQuietly(connection);
			
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/userProfile");
			dispatcher.forward(request, response);
		}
		
		if (errorString == null) {
			CookieAgent.deleteUserCookie(response);
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
}
