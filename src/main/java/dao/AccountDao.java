package dao;

import java.util.List;
import java.util.Optional;

import model.Account;
import model.Customer;


public interface AccountDao {

	
public void save(Account account); 
    
    public void update(Account account); 
    
    public void delete(Long id); 

    public Account  findById(Long id);
    
    public List<Account> findAll();
    
    public Optional<Account> ListAccountUser(Customer customer);
	
}
