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

@WebServlet(urlPatterns = {"/doEditDictionaryRow"})

public class DoEditDictionaryRow extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DoEditDictionaryRow() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String wordIdStr = request.getParameter("wordId");
		int wordId = Integer.parseInt(wordIdStr);
		String word = request.getParameter("word");
		String transcription = request.getParameter("transcription");
		String translation = request.getParameter("translation");
		
		DictionaryRow row = new DictionaryRow(wordId, word, transcription,
				translation);
		
		Connection connection = ConnectionAgent.getStoredConnection(request);
		
		HttpSession session = request.getSession();
		User user = SessionAgent.getLoginedUser(session);
		String dictionaryName = user.getDictionaryName();
		try {
			DictionaryAgent.updateDictionaryRow(connection, dictionaryName,
					row);
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
