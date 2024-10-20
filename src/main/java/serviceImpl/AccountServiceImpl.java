package serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import daoImpl.AccountDaoImpl;
import lombok.RequiredArgsConstructor;
import model.Account;
import model.Customer;
import service.AccountService;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService   {

	private final AccountDaoImpl accountdaoImpl;

	@Override
	public void saveAccount(Account account) {
		accountdaoImpl.save(account);
	}

	@Override
	public void updateAccount(Account account) {
		accountdaoImpl.update(account);
		
	}

	@Override
	public void deleteAccount(Long id) {
		accountdaoImpl.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Account ListAccountId(Long id) {
		
		return accountdaoImpl.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> ListAccount() {
		
		return accountdaoImpl.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Account> ListAccountUser(Customer customer) {

		return accountdaoImpl.ListAccountUser(customer);
	}
	
	
	
	
	
	
}
