package org.dictionarium.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dictionarium.bean.User;
import org.dictionarium.util.SessionAgent;

@WebFilter(filterName = "loginFilter", urlPatterns = {"/login/*"})

public class LoginFilter implements Filter {

	public LoginFilter() {
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User loginedUser = SessionAgent.getLoginedUser(session);
		
		if (loginedUser == null) {
			chain.doFilter(request, response);
			return;
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(httpRequest.getContextPath()
					+ "/dictionary");
		}
	}

}
