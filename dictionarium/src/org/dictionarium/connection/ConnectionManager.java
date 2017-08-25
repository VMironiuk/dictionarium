package org.dictionarium.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection getConnection()
			throws SQLException, ClassNotFoundException {
		return MariaDBConnection.getConnection();
	}
	
	public static void closeQuietly(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
		}
	}
	
	public static void rollbackQuietly(Connection connection) {
		try {
			connection.rollback();
		} catch (Exception e) {
		}
	}
}
