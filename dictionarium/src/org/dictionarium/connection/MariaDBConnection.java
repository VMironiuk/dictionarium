package org.dictionarium.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConnection {

	protected static Connection getConnection()
			throws SQLException, ClassNotFoundException {
		String hostName = "localhost";
		String userName = "root";
		String password = "yN3jtd86rvsZ";
		String databaseName = "dictionarium";
		
		return getConnection(hostName, userName, password, databaseName);
	}
	
	protected static Connection getConnection(String hostName, String userName,
			String password, String databaseName)
					throws SQLException, ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");
		String connectionURL = "jdbc:mysql://" + hostName
				+ ":3306/" + databaseName;
		
		return DriverManager.getConnection(connectionURL, userName, password);
	}
}
