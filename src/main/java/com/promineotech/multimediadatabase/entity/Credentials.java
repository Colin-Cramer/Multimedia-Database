package com.promineotech.multimediadatabase.entity;

import com.promineotech.multimediadatabase.util.AccountLevel;

public class Credentials {

	private String username;
	private String password;
	private AccountLevel level;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountLevel getLevel() {
		return level;
	}

	public void setLevel(AccountLevel level) {
		this.level = level;
	}
}
