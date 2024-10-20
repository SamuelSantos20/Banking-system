package enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Banks {

	SAMBANK("SamBank");

	public String getBank() {
		return bank;
	}

	private Banks(String bank) {
		this.bank = bank;
	}

	@Enumerated(EnumType.STRING)
	private String bank;

}
