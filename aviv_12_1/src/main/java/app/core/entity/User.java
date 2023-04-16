package app.core.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import app.core.security.ClientType;

public class User {
	private int id;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private ClientType clientType;

	public User() {

	}

	public User(int id, String email, String password, ClientType clientType) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.clientType = clientType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

}
