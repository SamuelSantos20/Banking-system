package enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum AuthenticationUser {

	USER("USER");
	
	
	
	public String getAuthentication() {
		return authentication;
	}

	private AuthenticationUser(String authentication) {
		this.authentication = authentication;
	}

	@Enumerated(EnumType.STRING)
	private String authentication;
	
	
	
	
	
}
