package org.dictionarium.util;

import javax.servlet.http.HttpSession;

import org.dictionarium.bean.User;

public class SessionAgent {
	
	private static final String LOGINED_USER = "LOGINED_USER";

	public static void storeLoginedUser(HttpSession session, User user) {
		session.setAttribute(LOGINED_USER, user);
	}
	
	public static User getLoginedUser(HttpSession session) {
		return (User) session.getAttribute(LOGINED_USER);
	}
}
