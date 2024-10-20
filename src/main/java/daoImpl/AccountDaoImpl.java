package daoImpl;


import java.util.Optional;

import org.springframework.stereotype.Repository;

import dao.AccountDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Account;
import model.Customer;

@Repository
public class AccountDaoImpl extends AbstractDao<Account, Long> implements AccountDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Optional<Account> ListAccountUser(Customer customer) {
		try {
			String jpql = "select a from Account a where a.customer = :customer";

			TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
			query.setParameter("customer", customer);

			Optional<Account>  optional = Optional.empty();
			
			for(Account account : query.getResultList()){
				
				optional = Optional.ofNullable(account);
				
			}
			
			return optional;

		} catch (Exception e) {

			System.err.println("Erro ao listar despesas: " + e.getMessage());
			return Optional.empty();
		}

	}

}
