package com.example.app.client;

import java.time.LocalDateTime;
import java.util.Objects;

public class CodeToken {

	private String email;
	private LocalDateTime date;
	private String token;
	
	public CodeToken()
	{
		super();
	}

	public CodeToken(String email, LocalDateTime date, String token) {
		super();
		this.email = email;
		this.date = date;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, email, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeToken other = (CodeToken) obj;
		return Objects.equals(date, other.date) && Objects.equals(email, other.email)
				&& Objects.equals(token, other.token);
	}
	
	
	
	
	
	
	
	
}
