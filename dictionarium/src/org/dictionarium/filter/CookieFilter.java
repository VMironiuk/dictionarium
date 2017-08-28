package org.dictionarium.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dictionarium.bean.User;
import org.dictionarium.util.ConnectionAgent;
import org.dictionarium.util.CookieAgent;
import org.dictionarium.util.SessionAgent;
import org.dictionarium.util.UserAgent;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})

public class CookieFilter extends BaseFilter {

	public CookieFilter() {
		super();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User userInSession = SessionAgent.getLoginedUser(session);
		
		if (userInSession != null) {
			chain.doFilter(request, response);
			return;
		}
		
		String userNameInCookie = CookieAgent.getUserName(httpRequest);
		if (userNameInCookie != null) {
			Connection connection = ConnectionAgent
					.getStoredConnection(request);
			try {
				userInSession = UserAgent
						.findUser(connection, userNameInCookie);
				// rest of the filters will check if there is a user in
				// session, if not, they will be redirect to the login page
				SessionAgent.storeLoginedUser(session, userInSession);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		chain.doFilter(request, response);
	}
}
