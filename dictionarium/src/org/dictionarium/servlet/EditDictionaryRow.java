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
		int wordId = 0;
		try {
			wordId = Integer.parseInt(wordIdStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dictionaryName = user.getDictionaryName();
		Connection connection = ConnectionAgent.getStoredConnection(request);
		DictionaryRow row = null;
		String errorString = null;
		try {
			row = DictionaryAgent.findDictionaryRow(connection, wordId,
					dictionaryName);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		request.setAttribute("user", user);
		request.setAttribute("dictionaryRow", row);
		request.setAttribute("errorString", errorString);

		String target = null;
		if (errorString != null && row == null) {
			target = "/WEB-INF/views/dictionary.jsp";
		} else {
			target = "/WEB-INF/views/edit_dictionary_row.jsp"; 
		}
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
}
