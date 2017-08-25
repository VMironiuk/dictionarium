package org.dictionarium.bean;

public class User {

	private String name;
	private String password;
	private String email;
	private String dictionaryName;
	
	public User() {
	}
	
	public User(String name, String password, String email,
			String dictionaryName) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.dictionaryName = dictionaryName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDictionaryName() {
		return this.dictionaryName;
	}
	
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
}
