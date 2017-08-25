package org.dictionarium.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.dictionarium.bean.User;

public class DatabaseAgent {
	
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
}
