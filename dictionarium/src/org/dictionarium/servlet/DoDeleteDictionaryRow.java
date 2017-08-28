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
import org.dictionarium.util.ConnectionAgent;
import org.dictionarium.util.DictionaryAgent;
import org.dictionarium.util.SessionAgent;

@WebServlet(urlPatterns = {"/doDeleteDictionaryRow"})

public class DoDeleteDictionaryRow extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DoDeleteDictionaryRow() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String wordIdStr = request.getParameter("wordId");
		int wordId = 0;
		try {
			wordId = Integer.parseInt(wordIdStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection connection = ConnectionAgent.getStoredConnection(request);
		
		HttpSession session = request.getSession();
		User user = SessionAgent.getLoginedUser(session);
		String dictionaryName = user.getDictionaryName();
		String errorString = null;
		try {
			DictionaryAgent.deleteDictionaryRow(connection, dictionaryName,
					wordId);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/dictionary");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/dictionary");			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
}
