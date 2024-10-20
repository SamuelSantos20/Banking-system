package service;

import java.util.List;
import java.util.Optional;

import model.Account;
import model.Customer;

public interface AccountService {

	
void saveAccount(Account account) ;
	
	void updateAccount(Account account);
	
	void deleteAccount(Long id);
	
	Account  ListAccountId(Long id);
	
	List<Account> ListAccount();
	
	public Optional<Account> ListAccountUser(Customer customer);
}
