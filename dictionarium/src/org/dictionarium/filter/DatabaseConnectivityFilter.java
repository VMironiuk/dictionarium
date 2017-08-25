package org.dictionarium.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Collection;

import java.sql.Connection;

import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;

import org.dictionarium.connection.ConnectionManager;
import org.dictionarium.util.ConnectionAgent;

@WebFilter(filterName = "databaseConnectivityFilter", urlPatterns = {"/*"})

public class DatabaseConnectivityFilter implements Filter {

	public DatabaseConnectivityFilter() {
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
		/*
		 * Only open connections for the special requests which need connection.
		 * (For example, the path of the Servlet, JSP...)
		 * 
		 * Avoid open connection for common requests
		 * (For example: image, css, javascript...)
		 */
		if (isNeedDatabaseConnectivity((HttpServletRequest) request)) {
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
				connection.setAutoCommit(false);

				ConnectionAgent.storeConnection(request, connection);
				
				chain.doFilter(request, response);
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
				ConnectionManager.rollbackQuietly(connection);
				throw new ServletException();
			} finally {
				ConnectionManager.closeQuietly(connection);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/*
	 * This method checks if the target of the request is a servlet 
	 */
	private boolean isNeedDatabaseConnectivity(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String urlPattern = servletPath;
		if (pathInfo != null) {
			urlPattern = "/*";
		}
		
		Map<String, ? extends ServletRegistration> servletRegistrations = 
				request.getServletContext().getServletRegistrations();
		
		// Iteration over collection of servlets in the app
		Collection<? extends ServletRegistration> values = servletRegistrations
				.values();
		for (ServletRegistration sr : values) {
			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern))
				return true;
		}
		return false;
	}
}
