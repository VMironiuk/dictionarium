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

import org.dictionarium.bean.DictionaryRow;
import org.dictionarium.bean.User;
import org.dictionarium.util.ConnectionAgent;
import org.dictionarium.util.DictionaryAgent;
import org.dictionarium.util.SessionAgent;

@WebServlet(urlPatterns = {"/editDictionaryRow"})

public class EditDictionaryRow extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public EditDictionaryRow() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = SessionAgent.getLoginedUser(session);
		
		String wordIdStr = request.getParameter("wordId");
		int wordId = Integer.parseInt(wordIdStr);
		String dictionaryName = user.getDictionaryName();
		Connection connection = ConnectionAgent.getStoredConnection(request);
		DictionaryRow row = null;
		try {
			row = DictionaryAgent.findDictionaryRow(connection, wordId,
					dictionaryName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("user", user);
		request.setAttribute("dictionaryRow", row);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/edit_dictionary_row.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
}
