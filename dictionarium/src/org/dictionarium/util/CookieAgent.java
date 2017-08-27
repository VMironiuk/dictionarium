package org.dictionarium.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.dictionarium.bean.User;

public class CookieAgent {
	
	private static final String USER_NAME = "USER_NAME_IN_COOKIE";

	public static void storeUserName(HttpServletResponse response, User user) {
		Cookie userNameCookie = new Cookie(USER_NAME, user.getName());
		userNameCookie.setMaxAge(24 * 60 * 60);
		response.addCookie(userNameCookie);
	}
	
	public static String getUserName(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (USER_NAME.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie userNameCookie = new Cookie(USER_NAME, null);
		userNameCookie.setMaxAge(0);
		response.addCookie(userNameCookie);
	}
}
