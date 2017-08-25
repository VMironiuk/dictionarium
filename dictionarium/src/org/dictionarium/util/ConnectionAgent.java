package org.dictionarium.util;

import java.sql.Connection;
import javax.servlet.ServletRequest;

public class ConnectionAgent {

	private static final String CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	
	public static void storeConnection(ServletRequest request,
			Connection connection) {
		request.setAttribute(CONNECTION, connection);
	}
	
	public static Connection getStoredConnection(ServletRequest request) {
		return (Connection) request.getAttribute(CONNECTION);
	}
}
