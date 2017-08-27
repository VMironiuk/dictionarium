package org.dictionarium.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dictionarium.bean.DictionaryRow;

public class DictionaryAgent {
	
	public static void insertDictionaryRow(Connection connection,
			DictionaryRow row, String dictionaryName) throws SQLException {
		String sql = "INSERT INTO " + dictionaryName
				+ "(word, transcription, translation) "
				+ "VALUES(?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, row.getWord());
		statement.setString(2, row.getTranscription());
		statement.setString(3, row.getTranslation());
		statement.executeUpdate();
	}
	
	public static List<DictionaryRow> queryDictionary(Connection connection,
			String dictionaryName) throws SQLException {
		String sql = "SELECT d.word, d.transcription, d.translation FROM "
				+ dictionaryName + " d";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		List<DictionaryRow> dictionary = new ArrayList<DictionaryRow>();
		while (resultSet.next()) {
			String word = resultSet.getString("word");
			String transcription = resultSet.getString("transcription");
			String translation = resultSet.getString("translation");
			DictionaryRow row = new DictionaryRow(word, transcription,
					translation);
			dictionary.add(row);
		}
		return dictionary;
	}
}
