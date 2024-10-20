package dao;

import java.util.List;

import model.Account;
import model.Operation;

public interface OperationDao {


	public void save(Operation  operation); 
    
    public void update(Operation  operation); 
    
    public void delete(Long id); 

    public Operation findById(Long id);
    
    public List<Operation> findAll();
	
	
    public List<Operation> ListOperationAccount(Account account);
}
