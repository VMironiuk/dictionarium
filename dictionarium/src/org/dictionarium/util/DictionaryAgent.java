package org.dictionarium.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dictionarium.bean.DictionaryRow;

public class DictionaryAgent {
	
	public static void createDictionary(Connection connection,
			String dictionaryName) throws SQLException {
		String sql = "CREATE TABLE " + dictionaryName + " ("
				+ "word_id INT NOT NULL AUTO_INCREMENT,"
				+ "word VARCHAR(30) NOT NULL,"
				+ "transcription VARCHAR(30) NOT NULL,"
				+ "translation VARCHAR(30) NOT NULL,"
				+ "PRIMARY KEY (word_id)"
				+ ")";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
	}
	
	public static DictionaryRow findDictionaryRow(Connection connection,
			int wordId,	String dictionaryName) throws SQLException {
		String sql = "SELECT d.word_id, d.word, d.transcription, d.translation "
				+ "FROM " + dictionaryName + " d WHERE d.word_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, wordId);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String word = resultSet.getString("word");
			String transcription = resultSet.getString("transcription");
			String translation = resultSet.getString("translation");
			
			return new DictionaryRow(wordId, word, transcription, translation);
		}
		return null;
	}
	
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
	
	public static void updateDictionaryRow(Connection connection,
			String dictionaryName, DictionaryRow row) throws SQLException {
		String sql = "UPDATE " + dictionaryName
				+ " SET word = ?, transcription = ?, translation = ? "
				+ "WHERE word_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, row.getWord());
		statement.setString(2, row.getTranscription());
		statement.setString(3, row.getTranslation());
		statement.setInt(4, row.getWordId());
		statement.executeUpdate();
	}
	
	public static List<DictionaryRow> queryDictionary(Connection connection,
			String dictionaryName) throws SQLException {
		String sql = "SELECT d.word_id, d.word, d.transcription, d.translation "
				+ "FROM " + dictionaryName + " d";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		List<DictionaryRow> dictionary = new ArrayList<DictionaryRow>();
		while (resultSet.next()) {
			int word_id = resultSet.getInt("word_id");
			String word = resultSet.getString("word");
			String transcription = resultSet.getString("transcription");
			String translation = resultSet.getString("translation");
			DictionaryRow row = new DictionaryRow(word_id, word, transcription,
					translation);
			dictionary.add(row);
		}
		return dictionary;
	}
}
