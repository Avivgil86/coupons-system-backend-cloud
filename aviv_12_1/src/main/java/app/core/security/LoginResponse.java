package app.core.security;

import java.util.Objects;
import java.util.UUID;

public class LoginResponse {
	private UUID token;
	private String email;
	private int id;
	private String name;
	private ClientType clientType;

	public LoginResponse() {

	}

	public LoginResponse(UUID token, String email, int id, String name, ClientType clientType) {
		super();
		this.token = token;
		this.email = email;
		this.id = id;
		this.name = name;
		this.clientType = clientType;
	}

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientType, email, id, name, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginResponse other = (LoginResponse) obj;
		return clientType == other.clientType && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(token, other.token);
	}

	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", email=" + email + ", id=" + id + ", name=" + name + ", clientType="
				+ clientType + "]";
	}

}
