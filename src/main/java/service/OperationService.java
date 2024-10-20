package service;

import java.util.List;

import dto.OperationDto;
import model.Account;
import model.Operation;

public interface OperationService {

	
	void saveOperation(Operation operation);
	
	void updateOperation(Operation operation);
	
	void deleteOperation(Long id);
	
	Operation ListId(Long id);
	
	List<Operation> operationsList() ;
	
    public List<OperationDto> ListOperationAccount(Account account);
	
}
