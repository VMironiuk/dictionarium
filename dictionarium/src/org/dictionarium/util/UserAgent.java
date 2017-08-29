package org.dictionarium.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.dictionarium.bean.User;

public class UserAgent {
	
	public static void insertUser(Connection connection, User user)
			throws SQLException {
		String sql = "INSERT INTO "
				+ "user_tbl(user_name, password, email, dictionary_name)"
				+ " VALUES(?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getDictionaryName());
		statement.executeUpdate();
	}
	
	public static User findUser(Connection connection, String userName,
			String password) throws SQLException {
		String sql = "SELECT "
				+ "u.user_name, u.password, u.email, u.dictionary_name "
				+ "FROM user_tbl u WHERE u.user_name = ? AND u.password = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userName);
		statement.setString(2, password);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String n = resultSet.getString("user_name");
			String p = resultSet.getString("password");
			String e = resultSet.getString("email");
			String d = resultSet.getString("dictionary_name");
			
			return new User(n, p, e, d);
		}
		return null;
	}
	
	public static User findUser(Connection connection, String userName)
			throws SQLException {
		String sql = "SELECT "
				+ "u.user_name, u.password, u.email, u.dictionary_name "
				+ "FROM user_tbl u WHERE u.user_name = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userName);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String n = resultSet.getString("user_name");
			String p = resultSet.getString("password");
			String e = resultSet.getString("email");
			String d = resultSet.getString("dictionary_name");
			
			return new User(n, p, e, d);
		}
		return null;
	}
	
	public static void deleteUser(Connection connection, User user)
			throws SQLException {
		String sql = "DELETE FROM user_tbl WHERE user_name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.executeUpdate();
	}
}
