package daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import dao.OperationDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Account;
import model.Operation;

@Repository
public class OperationDaoImpl extends AbstractDao<Operation, Long>  implements OperationDao{

	@PersistenceContext 
	private EntityManager entityManager;
	
	public List<Operation> ListOperationAccount(Account account) {
	    try {
	        String jpql = "select p from Operation p where :accounts member of p.accounts";
	        
	        TypedQuery<Operation> query = entityManager.createQuery(jpql, Operation.class);
	        query.setParameter("accounts", account);
	        
	        return query.getResultList();
	        
	    } catch (Exception e) {

	    	System.err.println("Erro ao listar despesas: " + e.getMessage());
	        return new ArrayList<>();
	    }
	

	}

	
}
