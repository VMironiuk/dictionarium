package org.dictionarium.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dictionarium.bean.User;
import org.dictionarium.util.SessionAgent;

@WebServlet(urlPatterns = {"/userProfile"})

public class UserProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public UserProfileServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = SessionAgent.getLoginedUser(session);
		
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/user_profile.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
}