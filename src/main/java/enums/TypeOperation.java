package enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum TypeOperation {

	
	DEPOSIT("Deposit"),
	SAKE("Sake");
	
	@Enumerated(EnumType.STRING)
	private String  typeOperation;

	private TypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

}
