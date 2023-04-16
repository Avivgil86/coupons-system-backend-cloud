package app.core.security;

import java.util.Objects;

public class LoginRequest {
	private String email;
	private String password;
	private ClientType clientType;

	public LoginRequest() {

	}

	public LoginRequest(String email, String password, ClientType clientType) {
		super();
		this.email = email;
		this.password = password;
		this.clientType = clientType;
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

	@Override
	public int hashCode() {
		return Objects.hash(clientType, email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginRequest other = (LoginRequest) obj;
		return clientType == other.clientType && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + ", clientType=" + clientType + "]";
	}

}
