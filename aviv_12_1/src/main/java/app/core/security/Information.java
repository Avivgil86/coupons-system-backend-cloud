package app.core.security;

import java.time.LocalDateTime;
import java.util.Objects;

public class Information {
	private int id;
	private String emailString;
	private LocalDateTime expertionTime;
	private ClientType clientType;

	public Information() {

	}

	public Information(int id, String emailString, LocalDateTime expertionTime, ClientType clientType) {
		super();
		this.id = id;
		this.emailString = emailString;
		this.expertionTime = expertionTime;
		this.clientType = clientType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailString() {
		return emailString;
	}

	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}

	public LocalDateTime getExpertionTime() {
		return expertionTime;
	}

	public void setExpertionTime(LocalDateTime expertionTime) {
		this.expertionTime = expertionTime;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Information other = (Information) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Information [id=" + id + ", emailString=" + emailString + ", expertionTime=" + expertionTime
				+ ", clientType=" + clientType + "]";
	}

}
