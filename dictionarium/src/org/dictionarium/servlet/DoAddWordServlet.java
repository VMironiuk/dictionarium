package org.dictionarium.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
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

@WebServlet(urlPatterns = {"/doAddWord"})

public class DoAddWordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DoAddWordServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String word = request.getParameter("word");
		String transcription = request.getParameter("transcription");
		String translation = request.getParameter("translation");
		DictionaryRow row = new DictionaryRow(word, transcription, translation);
		
		Connection connection = ConnectionAgent.getStoredConnection(request);
		
		HttpSession session = request.getSession();
		User loginedUser = SessionAgent.getLoginedUser(session);
		String dictionaryName = loginedUser.getDictionaryName();
		
		try {
			DictionaryAgent.insertDictionaryRow(connection, row,
					dictionaryName);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		response.sendRedirect(request.getContextPath() + "/dictionary");
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
}
