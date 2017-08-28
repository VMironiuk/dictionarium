package org.dictionarium.bean;

public class DictionaryRow {

	private int wordId;
	private String word;
	private String transcription;
	private String translation;
	
	public DictionaryRow() {
	}
	
	public DictionaryRow(String word, String transcription,
			String translation) {
		this.word = word;
		this.transcription = transcription;
		this.translation = translation;
	}
	
	public DictionaryRow(int wordId, String word, String transcription,
			String translation) {
		this.wordId = wordId;
		this.word = word;
		this.transcription = transcription;
		this.translation = translation;
	}
	
	public int getWordId() {
		return this.wordId;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getTranscription() {
		return this.transcription;
	}
	
	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}
	
	public String getTranslation() {
		return this.translation;
	}
	
	public void setTranslation(String translation) {
		this.translation = translation;
	}
}
